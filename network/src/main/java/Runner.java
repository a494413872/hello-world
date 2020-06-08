import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public final static String inPath = "D:/name.txt";
    public final static String outPath = "D:/score.txt";
    public static List<String> result = new ArrayList<String>();
    // 创建Httpclient对象
    static CloseableHttpClient httpclient = HttpClients.createDefault();
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(inPath));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String name="宋青_"+str+"_公历";
            String date = "2020年5月16日15时_女";
            String url = "https://www.hmz.com/xmcs/"+ URLEncoder.encode(name,"gb2312")+"%20"+URLEncoder.encode(date,"gb2312")+"/";
            System.out.println(url);
            handle(url);
            Thread.sleep(10);
        }
        bufferedReader.close();
        inputStreamReader.close();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(outPath));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        for (String s : result) {
            System.out.println(s);
            bufferedWriter.write(s+"\n");
        }
        bufferedWriter.close();
        outputStreamWriter.close();

    }
    private static void handle(String url) throws Exception {

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                Document document = Jsoup.parse(content);
                Elements elements = document.select(".table1 .tr2");
                for (Element element : elements) {
                    result.add(element.text());
                }

                //内容写入文件
//                FileUtils.writeStringToFile(new File("E:\\devtest\\baidu.html"), content, "UTF-8");
//                System.out.println("内容长度："+content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
//            httpclient.close();
        }
    }
}
