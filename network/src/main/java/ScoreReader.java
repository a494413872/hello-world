import java.io.*;
import java.net.URLEncoder;

public class ScoreReader {
    public final static String outPath = "D:/score.txt";
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(outPath));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] strings = str.split(" ");

            if(Integer.parseInt(strings[1])>90&&Integer.parseInt(strings[2])>90){
                System.out.println(str);
            }

        }
        bufferedReader.close();
        inputStreamReader.close();
    }
}
