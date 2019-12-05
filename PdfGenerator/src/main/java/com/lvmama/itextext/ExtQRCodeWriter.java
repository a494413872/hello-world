

package com.lvmama.itextext;

import com.itextpdf.text.pdf.qrcode.*;

import java.util.Map;

public final class ExtQRCodeWriter {

    public ExtQRCodeWriter() {
    }

    public ByteMatrix encode(String contents, int width, int height) throws WriterException {
        return this.encode(contents, width, height, (Map)null);
    }

    public ByteMatrix encode(String contents, int width, int height, Map<EncodeHintType, Object> hints) throws WriterException {
        if (contents != null && contents.length() != 0) {
            if (width >= 0 && height >= 0) {
                ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
                if (hints != null) {
                    ErrorCorrectionLevel requestedECLevel = (ErrorCorrectionLevel)hints.get(EncodeHintType.ERROR_CORRECTION);
                    if (requestedECLevel != null) {
                        errorCorrectionLevel = requestedECLevel;
                    }
                }

                QRCode code = new QRCode();
                Encoder.encode(contents, errorCorrectionLevel, hints, code);
                return renderResult(code, width, height);
            } else {
                throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
            }
        } else {
            throw new IllegalArgumentException("Found empty contents");
        }
    }

    private static ByteMatrix renderResult(QRCode code, int width, int height) {
        ByteMatrix input = code.getMatrix();
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        //下面是计算白边的逻辑，移除掉。
//        int qrWidth = inputWidth + 8;
//        int qrHeight = inputHeight + 8;
        int qrWidth = inputWidth;
        int qrHeight = inputHeight;
        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);
        //计算需要扩大的倍数
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = 0;
        int topPadding = 0;
        //真生生成二维码大小以计算后的为准,生成最接近的大小，到外面再缩放
        outputWidth = qrWidth*multiple;
        outputHeight = qrHeight*multiple;
        ByteMatrix output = new ByteMatrix(qrWidth*multiple, qrHeight*multiple);
        byte[][] outputArray = output.getArray();
        byte[] row = new byte[outputWidth];

        for(int y = 0; y < topPadding; ++y) {
            setRowColor(outputArray[y], (byte)-1);
        }

        byte[][] inputArray = input.getArray();

        int y;
        int offset;
        for(y = 0; y < inputHeight; ++y) {
            for(offset = 0; offset < leftPadding; ++offset) {
                row[offset] = -1;
            }

            offset = leftPadding;

            int z;
            for(z = 0; z < inputWidth; ++z) {
                byte value = (byte) (inputArray[y][z] == 1 ? 0 : -1);

                for(int s = 0; s < multiple; ++s) {
                    row[offset + s] = (byte)value;
                }

                offset += multiple;
            }

            offset = leftPadding + inputWidth * multiple;

            for(z = offset; z < outputWidth; ++z) {
                row[z] = -1;
            }

            offset = topPadding + y * multiple;

            for(z = 0; z < multiple; ++z) {
                System.arraycopy(row, 0, outputArray[offset + z], 0, outputWidth);
            }
        }

        y = topPadding + inputHeight * multiple;

        for(offset = y; offset < outputHeight; ++offset) {
            setRowColor(outputArray[offset], (byte)-1);
        }

        return output;
    }

    private static void setRowColor(byte[] row, byte value) {
        for(int x = 0; x < row.length; ++x) {
            row[x] = value;
        }

    }
}
