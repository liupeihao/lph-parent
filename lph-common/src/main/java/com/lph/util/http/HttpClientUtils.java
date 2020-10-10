package com.lph.util.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lph.util.convert.BeanConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


/**
 * HttpClient工具类
 *
 * @author LPH
 * @date 2020年9月14日11:09:47
 */
@Slf4j
public class HttpClientUtils {


    /**
     * HttpClient客户端
     */
    private static final CloseableHttpClient HTTP_CLIENT;

    /**
     * 指客户端和服务器建立连接的timeout，
     * 就是http请求的三个阶段，一：建立连接；二：数据传送；三，断开连接。超时后会ConnectionTimeOutException
     */
    final static int connectTimeout = 30000;

    /**
     * 客户端从服务器读取数据的timeout，超出后会抛出SocketTimeOutException
     */
    final static int socketTimeout = 30000;

    /**
     * 从连接池获取链接超时时间
     */
    final static int connectionRequestTimeout = 3000;

    /**
     * 字符集
     */
    public static final String UTF_8 = "utf-8";

    /**
     * 请求头
     */
    public static final String contentType = "content-Type";

    /**
     * application/json
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * application/x-www-form-urlencoded
     */
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    /**
     *  生成默认httpClient对象
     */
    static {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * HTTP Get
     * 获取内容
     *
     * @param url           请求的url地址 ?之前的地址
     * @param requestEntity 请求的参数实体对象
     * @param charset       编码格式
     * @return 页面内容
     */

    public static String doGet(String url, HttpBaseRequest requestEntity, String charset) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("HttpClient error,request url must not be null");
        }
        if (Objects.nonNull(requestEntity)) {
            Map<String, String> paramsMap = BeanConvertUtils.beanToMap(requestEntity);
            StringBuilder sb = new StringBuilder();
            paramsMap.forEach((k, v) -> {
                sb.append("&")
                        .append(k)
                        .append("=")
                        .append(v);
            });
            url += ("?" + sb.replace(0, 1, ""));
        }
        HttpGet httpGet = new HttpGet(url);
        return execute(charset, httpGet);
    }


    public static String doPost(String url, HttpBaseRequest requestEntity) {
        return doPost(url,requestEntity,null,1, UTF_8);
    }

    /**
     * HTTP Post 获取内容
     * contentType : application/x-www-form-urlencoded
     *
     * @param url           请求的url地址
     * @param requestEntity 请求的参数实体
     * @param headerEntity  请求的header头实体
     * @param charset       编码格式
     * @param contentType   0:application/x-www-form-urlencoded 1:application/json
     * @return 页面内容
     */
    @SneakyThrows
    public static String doPost(String url,
                                HttpBaseRequest requestEntity,
                                HttpBaseHeader headerEntity,
                                int contentType,
                                final String charset) {

        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("HttpClient error,request url must not be null");
        }
        HttpPost httpPost = new HttpPost(url);
        if (Objects.nonNull(requestEntity)) {
            Map<String, String> paramsMap = BeanConvertUtils.beanToMap(requestEntity);
            switch (contentType) {
                case 0:
                    List<NameValuePair> pairs = new ArrayList<>();
                    paramsMap.forEach((k, v) -> {
                        if (Objects.nonNull(v)) {
                            pairs.add(new BasicNameValuePair(k, v));
                        }
                    });
                    perfectHttpPost(httpPost, new UrlEncodedFormEntity(pairs, charset), HttpClientUtils.APPLICATION_FORM_URLENCODED_VALUE);
                    break;
                case 1:
                    perfectHttpPost(httpPost, new StringEntity(new ObjectMapper().writeValueAsString(paramsMap), charset), HttpClientUtils.APPLICATION_JSON);
                    break;
            }
        }
        if (Objects.nonNull(headerEntity)) {
            Map<String, String> headerMap = BeanConvertUtils.beanToMap(headerEntity);
            headerMap.forEach(httpPost::addHeader);
        }
        return execute(charset, httpPost);
    }


    private static String execute(String charset, HttpRequestBase httpRequest) {
        try {
            CloseableHttpResponse response = HTTP_CLIENT.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            log.error("HttpClient,error:{}", e.getMessage());
            return new String();
        }
    }

    private static void perfectHttpPost(HttpPost httpPost, StringEntity entity, String contentType) {
        httpPost.setEntity(entity);
        httpPost.addHeader(HttpClientUtils.contentType, contentType);
    }
}
