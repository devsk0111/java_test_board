package com.sbs.java.board;

import java.sql.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static void testData(List<Article> articles) {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
    }

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        testData(articles);
        int lastArticleId = 0;

        if(!articles.isEmpty()) {
            lastArticleId = articles.get(articles.size() - 1).id;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("== Start Dashboard ==");

        while(true) {
            System.out.print("명령: ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("usr/articles/write")) {
                System.out.println("== 게시물 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();

                System.out.println("내용 : ");
                String content = sc.nextLine();

                int id = ++lastArticleId;

                Article article = new Article(id, title, content);// 게시물 객체 생성

                articles.add(article);

                System.out.printf("%d번째 계시물, \"%s\"의 내용이 저장 되었습니다\n", article.id, article.title);
                System.out.println("생성된 게시물 객체 : " + article);

            }

            else if (rq.getUrlPath().equals("usr/articles/detail")) {
                Map<String, String> params = rq.getParams();

                int id = 0;
                try { // 유효성 검사하기
                    id = Integer.parseInt(params.get("id")); // int id는 가져오고 싶은 숫자를 가져온다
                } catch (NumberFormatException e) {
                    System.out.println("id를 정수 형태로 입력해주세요");
                    continue;
                }

                if (articles.isEmpty()) { //유효성 검사
                    System.out.println("게시물 존재하지 않네요..?");
                    continue; // 밑에 내용 스킵
                }

                if(id > articles.size() - 1) {
                    System.out.println("해당 게시물 존재하지 않습니다");
                    continue;
                }

                Article article = articles.get(id - 1);

                System.out.println("== 게시물 상세보기 ==");

                System.out.printf("게시물 id : %d\n", article.id);
                System.out.printf("게시물 title : %s\n", article.title);
                System.out.printf("게시물 content : %s\n", article.content);
            }

            else if (rq.getUrlPath().equals("usr/article/list")) {

                Map<String, String> params = rq.getParams();

                boolean orderByIdDesc = true;

                if (articles.isEmpty()) {
                    System.out.println("게시물 업슈");
                    continue;
                }

                if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
                    orderByIdDesc = false;
                }

                // 검색 시작
                List<Article> filteredArticles = articles;

                if(params.containsKey("searchKeyword")) {
                    String searchKeyword = params.get("searchKeyword");

                    filteredArticles = new ArrayList<>();

                    for (Article article : articles) {
                        if(article.title.contains(searchKeyword) || article.content.contains(searchKeyword)) {
                            filteredArticles.add(article);
                        }
                    }
                }
                // 검색 끝

                List<Article> sortedArticles = filteredArticles;
                // List<Article> sortedArticles = articles;

                System.out.println("== 게시물 리스트 ==");
                System.out.println("---------------------");
                System.out.println("|  번호  |   제목     |");

                if (orderByIdDesc) { // idAsc가 없으면 기본값인 idDesc(내림차순)
                    sortedArticles = Util.reverseList(sortedArticles);
                }

                for(Article article : sortedArticles) {
                    System.out.printf("|   %d   |   %s   |\n", article.id, article.title);
                }

                System.out.println("---------------------");


            }

            else if (rq.getUrlPath().equalsIgnoreCase("exit")) {

                break;
            }

            else {
                System.out.printf("명령어: %s\n그런 명령어 없어요 ㅡ_ㅡ\n", cmd);
            }
        }
        System.out.println("== Exit Dashboard ==");
        sc.close();
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

