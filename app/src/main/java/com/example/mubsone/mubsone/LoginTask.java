package com.example.mubsone.mubsone;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mikel on 11/1/15.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {

    protected Void doInBackground(Void... params) {
        try {
            String requestUrl = "http://10.0.2.2:8000/accounts/login/";
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.i("GET Test", "ok I guess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void onPreExecute() {
        //display progress dialog.

    }

    protected void onPostExecute(Void result) {
        // dismiss progress dialog and update ui
    }

}