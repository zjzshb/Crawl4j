package com.hb.crawl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


/**
 * @author xiangpeng
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public HttpUtils() {

    }

    /**
     * 发送post请求(流)
     *
     */
    public static String sendHttpPost(String url, String param) {
        HttpURLConnection conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String responseStr = null;
        try {
            // 建立连接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //设置基本属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            //设置超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            //输出流传参
            out = new PrintWriter(conn.getOutputStream());
            out.print(new String(param.getBytes(), "UTF-8"));
            out.flush();
            // 定义输入流读取响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            // 读取响应数据
            String line = "";
            StringBuffer result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            responseStr = result.toString();
        } catch (MalformedURLException e) {
            logger.info("=====connection time out=====");
        } catch (Exception e) {
            logger.info("=====Excetpion=====");
            
        } finally {

            try {
                //释放资源
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return responseStr;
    }

    /**
     * 发送post请求(流)
     *
     * @param url
     * @param param
     * @return xml
     * @throws HttpException
     * @throws IOException
     */
    public static String sendHttpPostGBK(String url, String param) {
        HttpURLConnection conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String responseStr = null;
        try {
            // 建立连接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //设置基本属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            //设置超时时间
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            //输出流传参
            out = new PrintWriter(conn.getOutputStream());
            out.print(new String(param.getBytes(), "GBK"));
            out.flush();
            // 定义输入流读取响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "GBK"));
            // 读取响应数据
            String line = "";
            StringBuffer result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            responseStr = result.toString();
        } catch (MalformedURLException e) {
            logger.info("=====connection time out=====");
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("=====Excetpion=====");
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    /**
     * 无忧流量接口专用
     *
     * @param strUrl
     * @param param
     * @param method
     * @return String
     * @author 曾志香
     */
    public static String wyHttpRequestJson(String strUrl, String param, String method) {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader in = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.write(param.getBytes("UTF-8"));
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            while ((lines = in.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbf.append(lines);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != connection) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sbf.toString();
    }

    /**
     * 下面这个方法是http指定方式提交并对内容进行指定的编码
     *
     * @param strUrl
     * @param param
     * @param method
     * @return String
     */
    public static String sendHttpRequestJson(String strUrl, String param, String method) {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader in = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.write(param.getBytes("UTF-8"));
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            while ((lines = in.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbf.append(lines);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != connection) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sbf.toString();
    }

    /**
     * httppost请求(PostMethod)
     *
     * @param url
     * @param paramMap
     * @return String
     * @author 向蓬
     * @date 2015-9-16下午3:28:41
     */
    public static String sendPostMethod(String url, Map<String, String> paramMap, String encode) {
        String result = null;
        PostMethod method = null;
        try {
            //建立连接
            HttpClient client = new HttpClient();
            //设置超时时间
            HttpConnectionManagerParams cmp = client.getHttpConnectionManager().getParams();
            cmp.setConnectionTimeout(30000);
            cmp.setSoTimeout(30000);
            method = new PostMethod(url);
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
            //整理参数
            NameValuePair[] params = new NameValuePair[paramMap.size()];
            Object[] keys = paramMap.keySet().toArray();
            for (int i = 0; i < keys.length; i++) {
                NameValuePair nvp = new NameValuePair();
                String key = String.valueOf(keys[i]);
                nvp.setName(key);
                nvp.setValue(paramMap.get(key));
                params[i] = nvp;
            }
            method.addParameters(params);
            //发送请求
            int resultCode = client.executeMethod(method);
            if (resultCode == HttpStatus.SC_OK) {
                result = method.getResponseBodyAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != method) {
                method.releaseConnection();
            }
        }
        return result;
    }

    /**
     * httppost请求(PostMethod)
     *
     * @param url
     * @param jsonStr
     * @param encode
     * @return String
     * @author 向蓬
     * @date 2016-2-26下午4:17:12
     */
    public static String sendPostMethod(String url, String jsonStr, String encode) {
        String result = null;
        PostMethod method = null;
        try {
            //建立连接
            HttpClient client = new HttpClient();
            //设置超时时间
            HttpConnectionManagerParams cmp = client.getHttpConnectionManager().getParams();
            cmp.setConnectionTimeout(30000);
            cmp.setSoTimeout(30000);
            method = new PostMethod(url);
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
            //整理参数
            method.setRequestEntity(new StringRequestEntity(jsonStr));
            method.setRequestHeader("Content-type", "text/xml; charset=" + encode);
            //发送请求
            int resultCode = client.executeMethod(method);
            if (resultCode == HttpStatus.SC_OK) {
                result = method.getResponseBodyAsString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != method) {
                method.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    /**
     * @param url
     * @param param
     * @return String
     * @author 向蓬
     * @date 2016-5-20上午10:31:11
     */
    public static String sendGet(String url, String param) {
        StringBuffer result = null;
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * URLConnection发送get请求
     *
     * @param url
     * @param param
     * @return String
     * @author 向蓬
     * @date 2016-5-20上午10:39:25
     */
    public static String httpGetRequest(String url, String param) {
        StringBuffer result = null;
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println("请求url：" + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "application/json;charset=UTF-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 微信post提交
     *
     * @return
     * @author mars
     */
    public static String httpWeiXinPost(String inurl) {
        InputStream inputStream = null;
        InputStreamReader inputReader = null;
        BufferedReader bufferedReader = null;
        String result = null;
        try {
            URL url = new URL(inurl);
            //打开http连接
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.addRequestProperty("encoding", "GBK");// 指定编码
            huc.setDoInput(true);// 允许输入
            huc.setDoOutput(true);// 允许输出
            huc.setRequestMethod("POST");// 指定post请求
            //得到输入流
            inputStream = huc.getInputStream();
            inputReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputReader);
            //读取数据
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != inputReader) {
                    inputReader.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
