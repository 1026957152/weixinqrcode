package com.coalvalue.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {
        public static String getAjaxCotnent(String url) throws IOException {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("phantomjs.exe D:\\mei_0628\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\examples\\hello.js "+url);//这里我的codes.js是保存在c盘下面的phantomjs目录
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            while((tmp = br.readLine())!=null){
                sbf.append(tmp);
            }
            //System.out.println(sbf.toString());   
            return sbf.toString();
        }

        public static void main(String[] args) throws IOException {
         //   getAjaxCotnent("http://www.oicqzone.com");
            getAjaxCotnent(null);

        }
    }