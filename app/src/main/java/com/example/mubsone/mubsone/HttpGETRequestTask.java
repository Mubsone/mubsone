package com.example.mubsone.mubsone;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mikel on 11/3/15.
 */
public class HttpGETRequestTask extends AsyncTask<HttpRequestParams, Void, String>{
    public HttpAsyncResponse delegate = null;

    protected String doInBackground(HttpRequestParams... paramSet) {
        for (HttpRequestParams params : paramSet)
            try {
                String response = "";
                String requestUrl = params.getUrl();

                URL getUrl = new URL(requestUrl);
                HttpURLConnection getConn = (HttpURLConnection) getUrl.openConnection();
                getConn.setRequestMethod("GET");
                getConn.setRequestProperty("Accept", "application/json");
                if (params.getJwtManager() != null) {
                    if (params.getJwtManager()._hasToken()) {
                        Log.i("Bearer", params.getJwtManager().getToken());
                        getConn.setRequestProperty("Authorization", "JWT " + params.getJwtManager().getToken());
                    }
                }
                getConn.setDoInput(true);

                int responseCode = getConn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(getConn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(getConn.getErrorStream()));
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

    @Override
    protected void onPostExecute(String result) {
        delegate.httpRequestProcessFinish(result);
    }

}
