package com.example.mubsone.mubsone;

import android.util.Log;

/**
 * Created by mikel on 11/5/15.
 */
public class HttpRequestTask {
    public static final String SERVER_ADDRESS = "http://89.120.209.153:10246";
    private HttpAsyncResponse response;
    private HttpRequestParams params;

    public HttpRequestTask(HttpRequestParams params, HttpAsyncResponse response)
    {
        this.params = params;
        this.response = response;
        this.params.setUrl(this.SERVER_ADDRESS + this.params.getUrl());

    }

    public void execute()
    {
        if (params.getMethod().equals("GET"))
        {
            HttpGETRequestTask getRequestTask = new HttpGETRequestTask();
            getRequestTask.delegate = response;
            getRequestTask.execute(params);
        }
        else if (params.getMethod().equals("POST"))
        {
            HttpPOSTRequestTask postRequestTask = new HttpPOSTRequestTask();
            postRequestTask.delegate = response;
            postRequestTask.execute(params);
        }

    }

}
