package com.sbs.java.board;

import com.sbs.java.board.container.Container;
import com.sbs.java.board.session.Session;
import lombok.Getter;

import java.util.Map;

public class Rq {
    private String url;
    @Getter
    private Map<String, String> params;
    @Getter
    private String urlPath;
    private Session session;

    Rq(String url) {
        this.url = url;
        this.params = Util.getParamsFromUrl(url);
        this.urlPath = Util.getUrlPathFromUrl(url);

        session = Container.session;
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

    //가져 오는 것, 저장하는 것 session
    public Object getSessionAttr(String attrName) {
        return session.getAttribute(attrName);
    }

    public void setSessionAttr(String attrName, Object value) {

        session.setAttribute(attrName, value);
    }
}
