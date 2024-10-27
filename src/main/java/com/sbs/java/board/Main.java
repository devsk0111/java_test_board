package com.sbs.java.board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== Start Dashboard ==");

        while(true) {
            System.out.print("명령: ");
            String cmd = sc.nextLine();

            int id = 1;

            if (cmd.equalsIgnoreCase("usr/article/write")) {
                System.out.println("== 게시물 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();

                System.out.println("내용 : ");
                String content = sc.nextLine();

                System.out.printf("%d 번째 계시물, '%s'의 내용이 저장 되었습니다\n\n", id, title);
                id++;

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