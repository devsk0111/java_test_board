package com.sbs.java.board;

import java.util.Map;

public class Rq {
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url) {
        this.url = url;
        this.params = Util.getParamsFromUrl(url);
        this.urlPath = Util.getUrlPathFromUrl(url);
    }


    public Map<String, String> getParams() {
        return params;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (!params.containsKey(paramName)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(params.get(paramName));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getParam(String paramName, String defaultValue) {
        if (!params.containsKey(paramName)) {
            return defaultValue;
        }
       return params.get(paramName);
    }
}
