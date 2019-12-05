//package demo;
//
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//
///**
// * Created by songjian on 9/20/2018.
// */
//public class PdfGenNIO {
//    private static final int WIDTH = 270; // 二维码宽
//    private static final int HEIGHT = 50; // 二维码高
//
//    public static void main(String[] args) throws IOException {
////        System.out.println("Default Charset=" + Charset.defaultCharset());
////        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
////        System.out.println("Default Charset=" + Charset.defaultCharset());
////        System.out.println("Default Charset in Use=" + getDefaultCharSet());
//        System.out.println("-----------------开始处理------------------");
//        String[] datas = new String[1000];
//        for (int i = 0; i < 1000; i++) {
//            datas[i] = i + "10000";
//        }
//        long startTime = System.currentTimeMillis();
//        for (String data : datas) {
//            /**
//             * 生成文字图片
//             */
//            BufferedImage tempimg = ImageUtil.createPdf417(data, WIDTH, HEIGHT);
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            ImageIO.write(tempimg, "png", out);
//            byte[] bytes = out.toByteArray();
//            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
//            FileChannel fileChannel = new FileOutputStream(new File("D:/LvmamaPdfGenerator/demo/image/" + data + ".png")).getChannel();
//            byteBuffer.flip();
//            fileChannel.write(byteBuffer);
//            fileChannel.close();
//            out.flush();
//            out.close();
//            System.out.println(data + "生成图片成功");
//
//            File file = new File("D:/LvmamaPdfGenerator/demo/template/GateWayPdf.html");
//            FileChannel inputChannel = new FileInputStream(file).getChannel();
//            ByteBuffer inBuffer = ByteBuffer.allocate(10240);
//            byte[] template = null;
//            inputChannel.read(inBuffer);
//            inBuffer.flip();
//            template = new byte[(inBuffer.limit())];
//            inBuffer.get(template);
//            String str = new String(template);
//            String imagePath = "file:/D:/LvmamaPdfGenerator/demo/image/" + data + ".png";
//            str = str.replaceAll("$image$", imagePath);
//            str = str.replaceAll("$data$", data);
//            PdfUtil.createPdfFileNIO(PdfUtil.createPdfStream(str),"D:/LvmamaPdfGenerator/demo/pdf/"+data+".pdf");
//            System.out.println(data + "生成PDF成功");
//            System.out.println("-------------------------------------");
//        }
//        System.out.println("---------处理结束-----------");
//        long end = System.currentTimeMillis();
//        System.out.println("总耗时：" + ((end - startTime) / 1000));
//
////        /**
////         * 生成文字图片
////          */
////        BufferedImage words = ImageUtil.convertWordsToImg("959502059900227800959502059900227800959502059900227800");
////        FileOutputStream fout = new FileOutputStream(new File("D:/desney.png"));
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////        boolean flag = ImageIO.write(words, "png", out);
////        out.writeTo(fout);
////        /**
////         * 生成
////         */
//
////        BufferedImage tempimg = ImageUtil.createPdf417("959502059900227800",WIDTH,HEIGHT);
////        tempimg=ImageUtil.rotateImage(tempimg,90);
////        FileOutputStream fout = new FileOutputStream(new File("D:/desney.png"));
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////        boolean flag = ImageIO.write(tempimg, "png", out);
////        out.writeTo(fout);
//
//
//        // saveToPNG(imageNew1, "imageNew1.png");
//    }
//
//    private static String getDefaultCharSet() {
//        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
//        String enc = writer.getEncoding();
//        return enc;
//    }
//
//}
