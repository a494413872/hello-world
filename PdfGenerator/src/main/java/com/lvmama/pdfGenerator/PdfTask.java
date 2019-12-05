package com.lvmama.pdfGenerator;

import com.lvmama.util.ImageUtil;
import com.lvmama.util.PdfUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfTask implements Runnable {
    private  BaseData data;
    private  String imageCode;
    PdfTask(BaseData data,String imageCode){
        this.data=data;
        this.imageCode=imageCode;
    }
    public void run() {
        String passCode = data.getColumnFive();
        try {
            /**
             * 生成文字图片
             */
            String imagePath = Generate.imagePath+passCode+".png";
            BufferedImage tempimg =null;
            if(ImageCode.QR.getCode().equals(imageCode.toUpperCase())){
                tempimg = ImageUtil.createQRcode(passCode,Generate.QR_WIDTH,Generate.QR_HEIGHT);
            }else if(ImageCode.BARA.getCode().equals(imageCode.toUpperCase())) {
                tempimg =ImageUtil.create128A(passCode,Generate.BARA_WIDTH,Generate.BARA_HEIGHT);
            }
            FileOutputStream fout = new FileOutputStream(new File(imagePath));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(tempimg, "png", out);
            out.writeTo(fout);
            out.flush();
            out.close();
            StringBuilder sb = new StringBuilder();
            File file = new File(Generate.templatePath);
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);

            String s = "";
            while ((s =bf.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            }
            bf.close();
            fr.close();
            sb = sb.replace(sb.indexOf("$1$"),sb.indexOf("$1$")+3, data.getColumnOne());
            sb = sb.replace(sb.indexOf("$2$"),sb.indexOf("$2$")+3, data.getColumnTwo());
            sb = sb.replace(sb.indexOf("$3$"),sb.indexOf("$3$")+3, data.getColumnThree());
            sb = sb.replace(sb.indexOf("$4$"),sb.indexOf("$4$")+3, data.getColumnFour());
            sb = sb.replace(sb.indexOf("$5$"),sb.indexOf("$5$")+3, imagePath);
            PdfUtil.createPdfFileNIO(PdfUtil.createPdfStream(sb.toString()),Generate .pdfPath+passCode+".pdf");
            Generate.pdfCount.incrementAndGet();
        }catch (Exception e){
            System.out.println(passCode+" generate pdf failed");
            e.printStackTrace();
        }
        System.out.println(passCode+" generate pdf success");
    }
}
