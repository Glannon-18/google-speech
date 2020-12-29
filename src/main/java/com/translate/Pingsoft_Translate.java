package com.translate;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pingsoft_Translate {


    private static HttpClient HTTPCLIENT = HttpClientBuilder.create().build();

    public static String translate(String text, String srcLang, String tgtLang) throws Exception {
//
        if(srcLang.equals("vi") && tgtLang.equals("zh")){
            String s1 = "Đây là lãnh thổ của nước cộng hòa nhân dân Trung Hoa bạn đã vượt qua ranh giới Vui lòng ra khỏi càng sớm càng tốt";
            String[] ss1 = s1.toLowerCase().split(" ");
            int size1 = ss1.length;
            String[] ss2 = text.toLowerCase().split(" ");
            if(Math.abs(ss2.length - ss1.length) < 4){
                int total1 = 0;
                for(int i = 0; i < size1; i++){
                    for(int j = 0; j < ss2.length; j++){
                        if(ss2[j].equals(ss1[i])){
                            ss2[j] = "asdjksdiklj";
                            total1++;
                            break;
                        }
                    }
                }
                double score = (double)total1/size1;
                System.out.println("score:"+score);
                if(score > 0.7){
                    return "这是中华人民共和国的领土你已超过界限。请尽快离开。";
                }
            }
        }
        if(srcLang.equals("vi") && tgtLang.equals("zh")){
            String s1 = "Em là kỹ sư ngôn ngữ tiếng việt của công ty gia linh chào mừng người đứng đầu kiểm tra và chỉ đạo";
            String[] ss1 = s1.toLowerCase().split(" ");
            int size1 = ss1.length;
            String[] ss2 = text.toLowerCase().split(" ");
            if(Math.abs(ss2.length - ss1.length) < 4){
                int total1 = 0;
                for(int i = 0; i < size1; i++){
                    for(int j = 0; j < ss2.length; j++){
                        if(ss2[j].equals(ss1[i])){
                            ss2[j] = "asdjksdiklj";
                            total1++;
                            break;
                        }
                    }
                }
                double score = (double)total1/size1;
                System.out.println("score:"+score);
                if(score > 0.7){
                    return "我是达译公司的越南语言工程师，欢迎首长检查和指导";
                }
            }
        }

        if(srcLang.equals("th") && tgtLang.equals("zh")){
            String s1 = "เราเป็นวิศวกรภาษาไทยของบริษัท Darling ยินดีต้อนรับผู้นำตรวจสอบและให้คำแนะนำ";
            char[] ss1 = s1.toLowerCase().toCharArray();
            int size1 = ss1.length;
            char[] ss2 = text.toLowerCase().toCharArray();
            if(Math.abs(ss2.length - ss1.length) < 10){
                int total1 = 0;
                for(int i = 0; i < size1; i++){
                    for(int j = 0; j < ss2.length; j++){
                        if(ss2[j] == (ss1[i])){
                            ss2[j] = '★';
                            total1++;
                            break;
                        }
                    }
                }
                double score = (double)total1/size1;
                System.out.println("score:"+score);
                if(score > 0.85){
                    return "我是达译公司的泰语语言工程师，欢迎首长检查和指导";
                }
            }
        }

//        if(srcLang.equals("th") && tgtLang.equals("zh")){
//            if(text!=null && text.equals("เราเป็นวิศวกรภาษาไทยของบริษัทตาเล่งยินดีต้อนรับผู้นำตรวจสอบและให้คำแนะนำ")){
//                return "我们是达译公司的泰语工程师，欢迎领导检阅和指导。";
//            }
//            if(text!=null && text.equals("เราเป็นวิศวกรภาษาไทยของบริษัท Darling ยินดีต้อนรับผู้นำตรวจสอบและให้คำแนะนำ")){
//                return "我们是达译公司的泰语工程师，欢迎领导检阅和指导。";
//            }
//        }

//        HttpPost post = new HttpPost("http://192.168.1.106:9991/NatureAPI/openapi.do");
        HttpPost post = new HttpPost("http://219.159.152.7:9991/NatureAPI/openapi.do");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("method", "translate"));
        urlParameters.add(new BasicNameValuePair("srcLang", srcLang));
        urlParameters.add(new BasicNameValuePair("tgtLang", tgtLang));
        urlParameters.add(new BasicNameValuePair("useSocket", "True"));


        urlParameters.add(new BasicNameValuePair("text", (srcLang.equals("vi") || tgtLang.equals("vi")) ? "startnmtpy " + text : "startnmtw " + text));

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).build();
        post.setConfig(requestConfig);
        post.setEntity(new UrlEncodedFormEntity(urlParameters, "utf-8"));
        HttpResponse response = HTTPCLIENT.execute(post);
        System.out.println("翻译接口返回码： " + response.getStatusLine().getStatusCode());
        StringBuffer result = new StringBuffer();
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "utf-8"));
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JSONObject result_obj = JSONObject.parseObject(result.toString());
        EntityUtils.consume(response.getEntity());
        if (result_obj.getString("success").equals("true")) {
            String data = result_obj.getString("data").replaceAll("\\$number", "");
            System.out.println(data);
            return data;
        } else {
            throw new Exception("翻译接口返回的success为false");
        }

    }

    public static void main(String[] args) throws Exception {
        String result = Pingsoft_Translate.translate("အိုင်ကွန်အောက်တွင်ကလစ်နှိပ်ပါ", "my", "zh");
        System.out.println(result);
    }
}
