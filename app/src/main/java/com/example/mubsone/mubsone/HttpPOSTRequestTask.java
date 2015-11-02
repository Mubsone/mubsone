package com.example.mubsone.mubsone;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mikel on 11/1/15.
 */
public class HttpPOSTRequestTask extends AsyncTask<HttpRequestParams, String, String> {

    protected String doInBackground(HttpRequestParams... paramSet) {
        for (HttpRequestParams params : paramSet)
            try {
                String response = "";
                String requestUrl = params.getUrl();

                CookieManager manager = new CookieManager();
                manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                CookieHandler.setDefault(manager);

                URL getUrl = new URL(requestUrl);
                HttpURLConnection getConn = (HttpURLConnection) getUrl.openConnection();
                getConn.setRequestProperty("Connection", "Keep-Alive");
                getConn.getContent();

                CookieStore cookieJar = manager.getCookieStore();
                List<HttpCookie> cookies = cookieJar.getCookies();
                String csrf = null;
                for (HttpCookie cookie : cookies) {
                    if (cookie.getName().equals("csrftoken")) {
                        csrf = cookie.getValue();
                        break;
                    }
                }


                URL postUrl = new URL(requestUrl);
                HttpURLConnection postConn = (HttpURLConnection) postUrl.openConnection();
                postConn.setRequestMethod(params.getMethod());
                postConn.setReadTimeout(15000);
                postConn.setConnectTimeout(15000);
                postConn.setDoInput(true);
                postConn.setDoOutput(true);

                params.getParams().put("csrfmiddlewaretoken", csrf);
                OutputStream os = postConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(getPostDataString(params.getParams()));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = postConn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(postConn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(postConn.getErrorStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                }
                Log.i("Response", response);
                return response;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            Log.i("key and value", entry.getKey() + " " + entry.getValue());
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}