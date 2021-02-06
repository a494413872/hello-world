package FILE;

import java.io.*;
import java.nio.channels.FileChannel;

public class IOStream {

    public static void main(String[] args) throws Exception {
        String path="F:\\idea_workspace\\For\\passport\\src\\main\\java\\com\\lvmama\\vst\\passport";
        File file = new File(path);
        filterFile(file);
    }

    public static void filterFile(File file) throws Exception {
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for (File realFile : files) {
                filterFile(realFile);
            }

        }else {
            String name = file.getName();
//            if(name.contains("ServiceImpl")){
//                FileReader fileReader = new FileReader(file);
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                String line =null;
//                while ((line = bufferedReader.readLine())!=null){
//                     if(line.contains("try")){
//                         System.out.println(name);
//                         break;
//                     }
//                }
//
//
//            }
            if(name.contains("ServiceImpl")) {
                System.out.println(name);
            }
        }
    }

}
