package com.sbs.java.board.article;

import com.sbs.java.board.Rq;
import com.sbs.java.board.Util;
import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = Container.articleService;

        articleService.testData();
    }

    public void doWrite() {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목: ");
        String title = Container.scanner.nextLine();

        if(title.trim().isEmpty()) {
            System.out.println("제목을 입력해주세요.");
            return;
        }

        System.out.println("내용: ");
        String content = Container.scanner.nextLine();

        if(content.trim().isEmpty()) {
            System.out.println("최소한 3글자 이상의 내용을 입력해주세요.");
            return;
        }

        int id = articleService.write(title, content);

        System.out.printf("%d번째 계시물, \"%s\"의 내용이 저장 되었습니다\n", id, title);
    }

    public void showDetail(Rq rq) {
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID를 올바르게 입력해주십시오.");
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== 게시물 상세보기 ==");

        System.out.printf("게시물 id : %d\n", article.getId());
        System.out.printf("게시물 title : %s\n", article.getTitle());
        System.out.printf("게시물 content : %s\n", article.getContent());
    }

    public void showList(Rq rq) {

        String orderBy = rq.getParam("orderBy", "idDesc");
        String searchKeyword = rq.getParam("searchKeyword", "");

       List<Article> articles = articleService.getArticles(orderBy, searchKeyword);

        System.out.println("== 게시물 리스트 ==");
        System.out.println("---------------------");
        System.out.println("|  번호  |   제목     |");


        for(Article article : articles) {
            System.out.printf("|   %d   |   %s   |\n", article.getId(), article.getTitle());
        }

        System.out.println("---------------------");


    }

    public void doModify(Rq rq) {
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID 올바르게 입력해주십시오.");
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== %d번째 게시물 수정 ==");
        System.out.print("새 제목: ");
        String title = Container.scanner.nextLine();

        if(title.trim().isEmpty()) {
            System.out.println("제목을 입력해주세요.");
            return;
        }

        System.out.println("새 내용: ");
        String content = Container.scanner.nextLine();

        if(content.trim().isEmpty()) {
            System.out.println("최소한 3글자 이상의 내용을 입력해주세요.");
            return;
        }

        articleService.modify(id, title, content);

        System.out.printf("%d번째 계시물, \"%s\"의 내용이 수정 되었습니다\n", article.getId(), article.getTitle());

    }

    public void doDelete(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("ID를 올바르게 입력해주십시오.");
            return;
        }

        Article article = articleService.findById(id);

        if(article == null) {
            System.out.printf("%d번째 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        articleService.delete(id);
        System.out.printf("%d번째 게시물이 삭제되었습니다.\n", id);
    }

}
