package com.lvmama.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {


    public static void requestPostJsonResponse(String url, String json) throws IOException {
        HttpClient client = null;
        client = new DefaultHttpClient();
        String contentType = "application/json; charset=UTF-8";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", contentType);
        StringEntity se = new StringEntity(json, "UTF-8");
        httpPost.setEntity(se);
        int retryTimes = 3;
        HttpResponse httpResponse = null; //执行POST请求
        while (retryTimes > 0) {
            try {
                httpResponse = client.execute(httpPost);
                break;
            } catch (IOException e) {
                if (retryTimes <= 1) {
                    throw e;
                }
            } //执行POST请求
            retryTimes--;
        }
        HttpEntity entity = httpResponse.getEntity();
        String responseStr = EntityUtils.toString(entity, "UTF-8");
        if (entity.getContentType() == null) {
            responseStr = new String(responseStr.getBytes("iso-8859-1"), "UTF-8");
        }
        EntityUtils.consume(entity);
        System.out.println("----------generate result uploaded---------");
    }
}
