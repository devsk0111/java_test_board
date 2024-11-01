package com.sbs.java.board;

import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App {
     int lastArticleId; // 인턴스에 직접적으로 변수 놓는 것은 좋지 못하다
     List<Article> articles;

     public App(){ //생성자 매서드로 만들기
         lastArticleId = 0;
         articles = new ArrayList<>();
     }

    void testData() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
    }
    void run() {
        testData();

        if(!articles.isEmpty()) {
            lastArticleId = articles.get(articles.size() - 1).id;
        }

        System.out.println("== Start Dashboard ==");

        while(true) {
            System.out.print("명령: ");
            String cmd = Container.scanner.nextLine();

            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("/usr/article/write")) {
                actionUsrArticleWrite();
            }
            else if (rq.getUrlPath().equals("/usr/article/detail")) {
                actionUsrArticleDetail(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/list")) {

                actionUsrArticleList(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/modify")) {
                actionUsrArticleModify(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/delete")) {
                actionUsrArticleDelete(rq);
            }
            else if (rq.getUrlPath().equalsIgnoreCase("exit")) {
                break;
            }
            else {
                System.out.printf("명령어: %s 존재하지 않습니다.\n", cmd);
            }
        }
        System.out.println("== Exit Dashboard ==");
        Container.scanner.close();
    }
    void actionUsrArticleWrite() { //lastArticleId는 지역변수
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목: ");
        String title = Container.scanner.nextLine();

        System.out.println("내용: ");
        String content = Container.scanner.nextLine();

        int id = ++lastArticleId;

        Article article = new Article(id, title, content);// 게시물 객체 생성

        articles.add(article);

        System.out.printf("%d번째 계시물, \"%s\"의 내용이 저장 되었습니다\n", article.id, article.title);
        System.out.println("생성된 게시물 객체 : " + article);
    }

    void actionUsrArticleList(Rq rq) {
        Map<String, String> params = rq.getParams();
        boolean orderByIdDesc = true;

        if (articles.isEmpty()) {
            System.out.println("게시물 업슈");
            return; // continue 대신, main 밖으로
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

    void actionUsrArticleDetail(Rq rq) {
        Map<String, String> params = rq.getParams();

        int id = 0;
        try { // 유효성 검사하기
            id = Integer.parseInt(params.get("id")); // int id는 가져오고 싶은 숫자를 가져온다
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요");
            return;
        }

        if (articles.isEmpty()) { //유효성 검사
            System.out.println("게시물 존재하지 않습니다?");
            return; // 밑에 내용 스킵
        }

        Article article = articleFindById(id);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== 게시물 상세보기 ==");

        System.out.printf("게시물 id : %d\n", article.id);
        System.out.printf("게시물 title : %s\n", article.title);
        System.out.printf("게시물 content : %s\n", article.content);
    }

    void actionUsrArticleModify(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id = 0;

        try { // 유효성 검사하기
            id = Integer.parseInt(params.get("id")); // int id는 가져오고 싶은 숫자를 가져온다
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요");
            return;
        }

        if (articles.isEmpty()) { //유효성 검사
            System.out.println("게시물 존재하지 않습니다?");
            return; // 밑에 내용 스킵
        }

        Article article = articleFindById(id);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== %d번째 게시물 수정 ==");
        System.out.print("제목: ");
        String title = Container.scanner.nextLine();

        System.out.println("내용:");
        String content = Container.scanner.nextLine();

        article.title = title;
        article.content = content;

        System.out.printf("%d번째 계시물, \"%s\"의 내용이 수정 되었습니다\n", article.id, article.title);

    }

    void actionUsrArticleDelete(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id = 0;

        try { // 유효성 검사하기
            id = Integer.parseInt(params.get("id")); // int id는 가져오고 싶은 숫자를 가져온다
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요");
            return;
        }

        if (articles.isEmpty()) { //유효성 검사
            System.out.println("게시물 존재하지 않습니다");
            return; // 밑에 내용 스킵
        }

        if(id > articles.size()) {
            System.out.println("해당 게시물 존재하지 않습니다");
            return;
        }

        Article article = articleFindById(id);

        if(article == null) {
            System.out.printf("%d번째 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        articles.remove(article);
        System.out.printf("%d번째 게시물이 삭제되었습니다.\n", id);
    }

    Article articleFindById(int id) {
        for(Article article : articles) {
            if(article.id == id) {
                return article;
            }
        }
        return null;
    }
}
