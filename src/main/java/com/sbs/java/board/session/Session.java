package com.sbs.java.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private Map<String, Object> store;

    public Session() {
        store = new HashMap<>();
    }

    public Object getAttribute(String attrName) {
        return store.get(attrName);
    }

    public void setAttribute(String attrName, Object value) {
        store.put(attrName, value);
    }

    public boolean hasAttribute(String attrName) {
        return store.containsKey(attrName);
    }

    public void removeAttribute(String attrName) {
        store.remove(attrName);
    }
}
