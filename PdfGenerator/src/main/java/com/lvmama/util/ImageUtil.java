package com.lvmama.util;

import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.lvmama.itextext.ExtBarCodeQrCode;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by songjian on 9/28/2018.
 */
public class ImageUtil {


    private static void setGraphics2D(Graphics2D graphics2D){
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        graphics2D.setStroke(s);
    }

    public static BufferedImage createPdf417(String codeString,int width,int height)throws IOException {
        BarcodePDF417 pdf = new BarcodePDF417();
        pdf.setText(codeString.getBytes("UTF-8"));
        Image pdfImg = pdf.createAwtImage(Color.black, Color.white);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D outg = img.createGraphics();
        setGraphics2D(outg);
        outg.drawImage(pdfImg, 0, 0,width,height, null);
        img.flush();
        return img;
    }
    public static BufferedImage createQRcode(String codeString,int width,int height)throws IOException {
        ExtBarCodeQrCode qrCode = new ExtBarCodeQrCode(codeString, width,height,null);
        Image pdfImg = qrCode.createAwtImage(Color.black, Color.white);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D outg = img.createGraphics();
        setGraphics2D(outg);
        outg.drawImage(pdfImg, 0, 0,width,height, null);
        img.flush();
        return img;
    }

    public static BufferedImage create128A(String codeString,int width,int height)throws IOException {
        Barcode128 barcode128 = new Barcode128();
        barcode128.setCodeSet(Barcode128.Barcode128CodeSet.A);
        barcode128.setCode(codeString);
        Image pdfImg = barcode128.createAwtImage(Color.black, Color.white);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D outg = img.createGraphics();
        setGraphics2D(outg);
        outg.drawImage(pdfImg, 0, 0,width,height, null);
        img.flush();
        return img;
    }

    /**
     * 把图片顺时针旋转90度
     * @param bufferedimage
     * @param degree
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bufferedimage,
                                            int degree){
        int w= bufferedimage.getWidth();// 得到图片宽度。
        int h= bufferedimage.getHeight();// 得到图片高度。
        BufferedImage img;// 空的图片。
        Graphics2D graphics2d;// 空的画笔。
        graphics2d= (img= new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB))
                .createGraphics();
        setGraphics2D(graphics2d);
        graphics2d.rotate(Math.toRadians(degree));// 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.drawImage(bufferedimage, 0, -h, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。
        graphics2d.dispose();
        return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
    }
    /**
     * 把对应的文字转化为图片
     * @param words
     * @return
     */
    public static BufferedImage convertWordsToImg(String words){
        if (StringUtil.isNotEmpty(words)) {
            Font font=new Font("微软雅黑", Font.BOLD, 14);
            Rectangle2D r = font.getStringBounds(words, new FontRenderContext(
                    AffineTransform.getScaleInstance(1, 1), false, false));
            int unitHeight = (int) Math.floor(r.getHeight());//
            // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
            int wordsWidth = (int) Math.round(r.getWidth()) ;
            // 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
            int wordsHeight = unitHeight;
            //创建一个带透明色的BufferedImage对象
            BufferedImage outImage = new BufferedImage(wordsHeight, wordsWidth, BufferedImage.TYPE_INT_ARGB);
            Graphics2D outg = outImage.createGraphics();
            setGraphics2D(outg);
            // 画文字到新的面板
            Color color=new Color(0,0,0);
            outg.setColor(color);
            // 字体、字型、字号
            outg.setFont(font);
            // 画文字
            outg.rotate(Math.toRadians(90d));
            outg.drawString(words, 0,-2);
            outg.dispose();
            outImage.flush();
            return outImage;
        }
        return null;
    }
}
