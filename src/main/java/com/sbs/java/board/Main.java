package com.sbs.java.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void testData(List<Article> articles) {
        articles.add(new Article(1, "제목 1", "내용 1"));
        articles.add(new Article(1, "제목 2", "내용 2"));
        articles.add(new Article(1, "제목 3", "내용 3"));
    }

    public static void main(String[] args) {
        int lastArticleId = 0;
        Article lastArticle = null;


        List<Article> articles = new ArrayList<>();

        testData(articles);

        Scanner sc = new Scanner(System.in);
        System.out.println("== Start Dashboard ==");

        while(true) {
            System.out.print("명령: ");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("write")) {
                System.out.println("== 게시물 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();

                System.out.println("내용 : ");
                String content = sc.nextLine();

                int id = ++lastArticleId;

                Article article = new Article(id, title, content);// 게시물 객체 생성
                lastArticle = article;

                System.out.printf("%d번째 계시물, \"%s\"의 내용이 저장 되었습니다\n", article.id, article.title);
                System.out.println("생성된 게시물 객체 : " + article);

            }

//            else if (cmd.equalsIgnoreCase("detail")) {
//              Article article = articles;
//
//                if (article == null) { //유효성 검사
//                    System.out.println("게시물 존재하지 않네요..?");
//                    continue; // 밑에 내용 스킵
//                }
//                System.out.println("== 게시물 상세보기 ==");
//
//                System.out.printf("게시물 id : %d\n", article.id);
//                System.out.printf("게시물 title : %s\n", article.title);
//                System.out.printf("게시물 content : %s\n", article.content);
//            }

            else if (cmd.equalsIgnoreCase("list")) {

                if (articles.isEmpty()) {
                    System.out.println("게시물 업슈");
                    continue;
                }

                System.out.println("== 게시물 리스트 ==");
                System.out.println("---------------------");
                System.out.println("|  번호  |   제목     |");
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    System.out.printf("|   %d   |   %s   |\n", article.id, article.title);
                }
                System.out.println("---------------------");


            }

            else if (cmd.equalsIgnoreCase("exit")) {

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