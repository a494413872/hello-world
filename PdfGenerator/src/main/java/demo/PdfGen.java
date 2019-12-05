//package demo;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
//public class PdfGen {
//    private static final int WIDTH = 270; // 二维码宽
//    private static final int HEIGHT = 50; // 二维码高
//    public static void main(String[] args) throws IOException {
////        System.out.println("Default Charset=" + Charset.defaultCharset());
////        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
////        System.out.println("Default Charset=" + Charset.defaultCharset());
////        System.out.println("Default Charset in Use=" + getDefaultCharSet());
//        System.out.println("-----------------开始处理------------------");
//        String[] datas=new String[1000];
//        for (int i = 0; i < 1000; i++) {
//            datas[i]=i+"10000";
//        }
//        long startTime = System.currentTimeMillis();
//        for (String data : datas) {
//            /**
//             * 生成文字图片
//             */
//            BufferedImage tempimg = ImageUtil.createPdf417(data,WIDTH,HEIGHT);
//            FileOutputStream fout = new FileOutputStream(new File("D:/LvmamaPdfGenerator/demo/image/"+data+".png"));
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            ImageIO.write(tempimg, "png", out);
//            out.writeTo(fout);
//            out.flush();
//            out.close();
//            System.out.println(data+"生成图片成功");
//
//            StringBuilder sb = new StringBuilder();
//            File file = new File("D:/LvmamaPdfGenerator/demo/template/GateWayPdf.html");
//            FileReader fr = new FileReader(file);
//            BufferedReader bf = new BufferedReader(fr);
//
//            String s = "";
//            while ((s =bf.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//            }
//            bf.close();
//            fr.close();
//            String imagePath = "file:/D:/LvmamaPdfGenerator/demo/image/"+data+".png";
//            sb = sb.replace(sb.indexOf("$image$"),sb.indexOf("$image$")+7, imagePath);
//            sb = sb.replace(sb.indexOf("$data$"),sb.indexOf("$data$")+6, data);
//            PdfUtil.createPdfFileNIO(PdfUtil.createPdfStream(sb.toString()),"D:/LvmamaPdfGenerator/demo/pdf/"+data+".pdf");
//            System.out.println(data+"生成PDF成功");
//            System.out.println("-------------------------------------");
//        }
//        System.out.println("---------处理结束-----------");
//        long end = System.currentTimeMillis();
//        System.out.println("总耗时："+((end-startTime)/1000));
//
//    }
//}
