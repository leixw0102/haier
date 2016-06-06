package com.haier.common.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ehl on 2016/6/5.
 */
public abstract  class HEAbstractHttpClient {
    private static String defaultUTF="utf-8";
    private static Charset UTF_8=Charset.forName(defaultUTF);
    private static int socket_timeout=60000;
    private static int connection_timeout=60000;


    public static CloseableHttpClient getHttpClient(){
           return HttpClients.createDefault();
    }

    private static RequestConfig getRequestConfig(){
        return RequestConfig.custom()
                .setSocketTimeout(socket_timeout)
                .setConnectTimeout(connection_timeout)
                .build();
    }

    public static HttpGet getHttpGet(String url){
        return getHttpGet(url, null);
    }

    public static HttpGet getHttpGet(String url ,Map<String,String> paramas){
        HttpGet get =  new HttpGet(buildGetUrl(url,paramas));
        get.setConfig(getRequestConfig());
        return get;
    }

    public static HttpPost getHttpPost(String url){
        return getHttpPost(url,null);
    }

    public static HttpPost getHttpPost(String url,Map<String,String> params){
        HttpPost post = new HttpPost(url);
        HttpEntity entity = null;
        entity = buildPostEntity(params);

        if(null != entity){
            post.setEntity(entity);
        }
        post.setConfig(getRequestConfig());
        return post;
    }
    private static HttpEntity buildStringPostEntity(Map<String,String> params) throws UnsupportedEncodingException {
        if (params != null) {

            return  new StringEntity(JSONObject.toJSONString(params),UTF_8);
        }
        return null;
    }
    private static HttpEntity buildPostEntity(Map<String,String> params){
        if (params != null) {
            List<NameValuePair> formparams = Lists.newArrayList();
            for (String key : params.keySet()) {
                formparams.add(new BasicNameValuePair(key, params.get(key)));
            }
            return  new UrlEncodedFormEntity(formparams, UTF_8);
        }
        return null;
    }
    private static String buildGetUrl(String url,Map<String,String> params){
        StringBuffer uriStr = new StringBuffer(url);
        if (params != null) {
            List<NameValuePair> ps = Lists.newArrayList();
            for (String key : params.keySet()) {
                ps.add(new BasicNameValuePair(key, params.get(key)));
            }
            uriStr.append("?");
            uriStr.append(URLEncodedUtils.format(ps, UTF_8));
        }
        return uriStr.toString();
    }

    protected static void checkHeader(StatusLine line) throws Exception {
        if(line.getStatusCode()!=HttpStatus.SC_OK){
            throw new Exception(line.getReasonPhrase());
        }
    }

    protected static String toString(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity,UTF_8);
    }
}
