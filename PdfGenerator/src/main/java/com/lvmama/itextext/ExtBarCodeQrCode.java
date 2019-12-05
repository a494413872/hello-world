

package com.lvmama.itextext;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.codec.CCITTG4Encoder;
import com.itextpdf.text.pdf.qrcode.ByteMatrix;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.WriterException;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.util.Map;

public class ExtBarCodeQrCode {
    ByteMatrix bm;

    public ExtBarCodeQrCode(String content, int width, int height, Map<EncodeHintType, Object> hints) {
        try {
            ExtQRCodeWriter qc = new ExtQRCodeWriter();
            this.bm = qc.encode(content, width, height, hints);
        } catch (WriterException var6) {
            throw new ExceptionConverter(var6);
        }
    }

    private byte[] getBitMatrix() {
        int width = this.bm.getWidth();
        int height = this.bm.getHeight();
        int stride = (width + 7) / 8;
        byte[] b = new byte[stride * height];
        byte[][] mt = this.bm.getArray();

        for(int y = 0; y < height; ++y) {
            byte[] line = mt[y];

            for(int x = 0; x < width; ++x) {
                if (line[x] != 0) {
                    int offset = stride * y + x / 8;
                    b[offset] |= (byte)(128 >> x % 8);
                }
            }
        }

        return b;
    }

    public Image getImage() throws BadElementException {
        byte[] b = this.getBitMatrix();
        byte[] g4 = CCITTG4Encoder.compress(b, this.bm.getWidth(), this.bm.getHeight());
        return Image.getInstance(this.bm.getWidth(), this.bm.getHeight(), false, 256, 1, g4, (int[])null);
    }

    public java.awt.Image createAwtImage(Color foreground, Color background) {
        int f = foreground.getRGB();
        int g = background.getRGB();
        Canvas canvas = new Canvas();
        int width = this.bm.getWidth();
        int height = this.bm.getHeight();
        int[] pix = new int[width * height];
        byte[][] mt = this.bm.getArray();

        for(int y = 0; y < height; ++y) {
            byte[] line = mt[y];

            for(int x = 0; x < width; ++x) {
                pix[y * width + x] = line[x] == 0 ? f : g;
            }
        }

        java.awt.Image img = canvas.createImage(new MemoryImageSource(width, height, pix, 0, width));
        return img;
    }

    public void placeBarcode(PdfContentByte cb, BaseColor foreground, float moduleSide) {
        int width = this.bm.getWidth();
        int height = this.bm.getHeight();
        byte[][] mt = this.bm.getArray();
        cb.setColorFill(foreground);

        for(int y = 0; y < height; ++y) {
            byte[] line = mt[y];

            for(int x = 0; x < width; ++x) {
                if (line[x] == 0) {
                    cb.rectangle((float)x * moduleSide, (float)(height - y - 1) * moduleSide, moduleSide, moduleSide);
                }
            }
        }

        cb.fill();
    }

    public Rectangle getBarcodeSize() {
        return new Rectangle(0.0F, 0.0F, (float)this.bm.getWidth(), (float)this.bm.getHeight());
    }
}
