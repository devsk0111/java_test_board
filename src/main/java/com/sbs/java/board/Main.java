package com.sbs.java.board;

import com.sbs.java.board.container.Container;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        new App().run();
    }

}

class Article { //extends Object
    int id;
    String title;
    String content;

    Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{id : %d, title: \"%s\"}".formatted(id, title);
    }
}

class Rq {
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
}

class Util {
    static Map<String, String> getParamsFromUrl(String queryString) {
        Map<String, String> params = new LinkedHashMap<>();

        // Split the URL into path and query parts
        String[] split = queryString.split("\\?", 2); // Limit to 2 splits
        if (split.length < 2) {
            // No query string present, return empty map
            return params;
        }

        String query = split[1];
        String[] pairs = query.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2); // Limit to 2 splits to handle missing values
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            } else if (keyValue.length == 1) {
                params.put(keyValue[0], "");
            }
        }

        return params;
    }

    static String getUrlPathFromUrl(String url) {
        return url.split("\\?", 2)[0];
    }

    //이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만든다.
    // 즉 정렬이 반대인 '복사'리스트를 만들어서 반환한다.

    public static<T> List<T> reverseList(List<T> list) {
        List<T> reverse = new ArrayList<>(list.size());

        for (int i = list.size() - 1; i >= 0; i--) {
            reverse.add(list.get(i));
        }

        return reverse;
    }
}

