package com.sbs.java.board;

import com.sbs.java.board.article.ArticleController;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.member.MemberController;

public class App {

    ArticleController articleController;
    MemberController memberController;

    public App() {
        articleController = Container.articleController;
        memberController = Container.memberController;
    }


    void run() {

        System.out.println("== Start Dashboard ==");

        while(true) {
            System.out.print("명령: ");
            String cmd = Container.scanner.nextLine();
            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("/usr/member/join")) {
                memberController.doJoin();
            }
            else if(rq.getUrlPath().equals("/usr/member/login")){
                memberController.doLogin();
            }

            //Article Logic

            else if (rq.getUrlPath().equals("/usr/article/write")) {
                articleController.doWrite();
            }
            else if (rq.getUrlPath().equals("/usr/article/detail")) {
                articleController.showDetail(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/list")) {
                articleController.showList(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/modify")) {
                articleController.doModify(rq);
            }
            else if (rq.getUrlPath().equals("/usr/article/delete")) {
                articleController.doDelete(rq);
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

}
