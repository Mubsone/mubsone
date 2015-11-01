package com.example.mubsone.mubsone;

import java.util.HashMap;

/**
 * Created by mikel on 11/1/15.
 */
public class HttpRequestParams {
    private String url;
    private HashMap<String, String> params;
    private String method;

    HttpRequestParams(String url, String method, HashMap<String, String> params)
    {
        this.method = method;
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void setUrl(String url) {
        this.url = url;

    }
}
