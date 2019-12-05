package com.lvmama.pdfGenerator;

import com.lvmama.util.ExceptionUtils;
import com.lvmama.util.HttpUtils;
import net.sf.json.JSONObject;
import org.xhtmlrenderer.util.XRLog;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Generate {
    public final static String templatePath = "D:/LvmamaPdfGenerator/lvMama/template/GateWayPdf.html";
    public final static String imagePath = "D:/LvmamaPdfGenerator/lvMama/image/";
    public final static String excelPath = "D:/LvmamaPdfGenerator/lvMama/data/datas.xlsx";
    public final static String pdfPath = "D:/LvmamaPdfGenerator/lvMama/pdf/";
    public final static String logPath = "http://codemagic.tk/pdfGenerator/logResult";
    public final static int BARA_WIDTH = 228; // 条码宽
    public final static int BARA_HEIGHT = 57; // 条码高
    public final static int QR_WIDTH = 114; // 条码宽
    public final static int QR_HEIGHT = 114; // 条码高

    public static AtomicInteger pdfCount = new AtomicInteger(0);


    public static void main(String[] args) throws UnknownHostException {
        Map<String,String> result = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.put("generateTime",sdf.format(new Date()));
        java.net.InetAddress   ip   =   java.net.InetAddress.getByName("localhost");
        StringBuilder sb = new StringBuilder();
        sb.append("ip:"+ip.getLocalHost().getHostAddress()+" ");
        try {
            //先判断入参
            //如果入参是128a 或者pdf417继续执行，否则提示输入正确的参数，并结束执行。
            if (validateParam(args)) {
                System.out.println("----------start to read excel----------");
                List<BaseData> datas = ExcelReader.readData(excelPath);
                System.out.println("---------- read "+datas.size()+" rows ----------");
                System.out.println("----------start to generate pdf ----------");
                int coreNum = Runtime.getRuntime().availableProcessors();
                int theadNum = coreNum + 1;
                System.out.println("----------you computer has " + coreNum + " cpu core, program will start " + theadNum + " threads----------");
                XRLog.render("initail log");
                ExecutorService executorService = Executors.newFixedThreadPool(theadNum);
                for (BaseData data : datas) {
                    executorService.execute(new PdfTask(data,args[0]));
                }
                executorService.shutdown();
                while(true){
                    if(executorService.isTerminated()){
                        System.out.println("----------"+pdfCount.get()+" pdf generated ----------");
                        break;
                    }
                    Thread.sleep(1000);
                }
                result.put("dataCount",datas.size()+"");
                result.put("pdfCount",pdfCount.toString());
                result.put("content",sb.toString());
                try{
                    HttpUtils.requestPostJsonResponse(logPath, JSONObject.fromObject(result).toString());
                }catch (Exception e){
                    System.out.println("Generate pdf success, upload log fail. You can ignore blow error.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("please choose right image code:");
                ImageCode.printAll();
            }
            System.out.println("----------END----------");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static boolean validateParam(String[] args) {
        if (args != null && args.length == 1 && (ImageCode.QR.getCode().equals(args[0].toUpperCase()) || ImageCode.BARA.getCode().equals(args[0].toUpperCase())))
            return true;
        return false;
    }
}
