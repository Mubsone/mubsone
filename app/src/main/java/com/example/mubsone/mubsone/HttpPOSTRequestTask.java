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

                URL getUrl = new URL(requestUrl);
                HttpURLConnection getConn = (HttpURLConnection) getUrl.openConnection();
                getConn.setUseCaches(false); // Don't use a Cached Copy
                getConn.setRequestMethod("GET");
                getConn.setRequestProperty("Connection", "Keep-Alive");
                getConn.getContent();
                getConn.disconnect();

                java.net.CookieManager cManager = new java.net.CookieManager();


                CookieStore cookieJar = cManager.getCookieStore();
                List<HttpCookie> cookies = cookieJar.getCookies();
                String csrf = null;
                for (HttpCookie cookie : cookies) {
                    Log.d("cookie", "" + cookie);
                    if (cookie.getName() == "csrftoken") {
                        csrf = cookie.getValue();
                        break;
                    }
                }

                //Log.i("CSRF", csrf);

                URL postUrl = new URL(requestUrl);
                HttpURLConnection postConn = (HttpURLConnection) postUrl.openConnection();
                postConn.setRequestMethod(params.getMethod());
                postConn.setReadTimeout(15000);
                postConn.setConnectTimeout(15000);
                postConn.setDoInput(true);
                postConn.setDoOutput(true);
                OutputStream os = postConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
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
                    response = "";

                }
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

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}