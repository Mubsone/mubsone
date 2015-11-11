package com.example.mubsone.mubsone;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
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
public class HttpPOSTRequestTask extends AsyncTask<HttpRequestParams, Void, String> {
    public HttpAsyncResponse delegate = null;

    protected String doInBackground(HttpRequestParams... paramSet) {
        for (HttpRequestParams params : paramSet)
            try {
                String response = "";
                String requestUrl = params.getUrl();

                URL postUrl = new URL(requestUrl);
                HttpURLConnection postConn = (HttpURLConnection) postUrl.openConnection();
                postConn.setRequestMethod("POST");
                postConn.setRequestProperty("Content-Type", "application/json");
                postConn.setRequestProperty("Accept", "application/json");
                if (params.getJwtManager() != null) {
                    if (params.getJwtManager()._hasToken()) {
                        postConn.setRequestProperty("Authorization", "JWT " + params.getJwtManager().getToken());
                    }
                }

//                postConn.setDoInput(true);
                postConn.setDoOutput(true);
//                postConn.connect();

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
                    return ("Failed : HTTP error code : " + responseCode);
                }
                Log.i("Response", response);
                return response;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.httpRequestProcessFinish(result);
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                jsonObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("JSON", jsonObject.toString());
        return jsonObject.toString();
    }
}