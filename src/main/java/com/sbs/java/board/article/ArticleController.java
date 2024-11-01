package com.sbs.java.board.article;

import com.sbs.java.board.Rq;
import com.sbs.java.board.Util;
import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {
    private int lastArticleId;
    private List<Article> articles;
    public ArticleController(){ //생성자 매서드로 만들기
        lastArticleId = 0;
        articles = new ArrayList<>();

        testData();

        if(!articles.isEmpty()) {
            lastArticleId = articles.get(articles.size() - 1).id;
        }
        
    }

    void testData() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
    }

    public void doWrite() {
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

    public void showDetail(Rq rq) {
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID를 올바르게 입력해주십시오.");
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

    public void showList(Rq rq) {
        Map<String, String> params = rq.getParams();

        if (articles.isEmpty()) {
            System.out.println("게시물 존재하지 않습니다");
            return; // continue 대신, main 밖으로
        }

        String orderBy = rq.getParam("orderBy", "idDesc");

        boolean orderByIdDesc = orderBy.equals("idDesc");

        if(orderBy.equals("idAsc")) {
            orderByIdDesc = false;
        }

        // 검색 시작

        String searchKeyword = rq.getParam("searchKeyword", "");
        List<Article> filteredArticles = articles;

        if(!searchKeyword.isEmpty()) {

            filteredArticles = new ArrayList<>();

            for (Article article : articles) {
                boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);
                if(matched) {
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

    public void doModify(Rq rq) {
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID를 올바르게 입력해주십시오.");
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

    public void doDelete(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID를 올바르게 입력해주십시오.");
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
