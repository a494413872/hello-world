package maku;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static List<String> getYearDoubleWeekend(int year){


        List<String> listDates = new ArrayList<String>();
        Calendar calendar=Calendar.getInstance();//当前日期
        calendar.set(year, 6, 1);
        Calendar nowyear=Calendar.getInstance();
        Calendar nexty=Calendar.getInstance();
        nowyear.set(year, 0, 1);//2010-1-1
        nexty.set(year+1, 0, 1);//2011-1-1
        calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_WEEK));//周六
        Calendar c=(Calendar) calendar.clone();
        for(;calendar.before(nexty)&&calendar.after(nowyear);calendar.add(Calendar.DAY_OF_YEAR, -7)){
            listDates.add(calendar.get(Calendar.YEAR)+"-"+(1+calendar.get(Calendar.MONTH))+"-"+(calendar.get(Calendar.DATE)-1));
            listDates.add(calendar.get(Calendar.YEAR)+"-"+(1+calendar.get(Calendar.MONTH))+"-"+(calendar.get(Calendar.DATE)));
        }
        for(;c.before(nexty)&&c.after(nowyear);c.add(Calendar.DAY_OF_YEAR, 7)){
            listDates.add(c.get(Calendar.YEAR)+"-"+(1+c.get(Calendar.MONTH))+"-"+(c.get(Calendar.DATE)-1));
            listDates.add(c.get(Calendar.YEAR)+"-"+(1+c.get(Calendar.MONTH))+"-"+(1+c.get(Calendar.DATE)));
        }
        for (int i = 0; i < listDates.size(); i++) {
            int amount = (int) (Math.random()*20);
            int minute = (int)(Math.random()*60);
            int second = (int)(Math.random()*30);
            if(i%2==0){
                System.out.println(listDates.get(i)+" 20:"+minute+":"+second+" 金额："+(120+amount));
            }else {
                System.out.println(listDates.get(i)+" 19:"+minute+":"+second+" 金额："+(120+amount));
            }
        }
        System.out.println(listDates.size());
        return listDates;
    };public static void main(String[] args) {
       getYearDoubleWeekend(2019);

    }

}
