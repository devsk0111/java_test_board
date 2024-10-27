package com.sbs.java.board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== Start Dashboard ==");

        while(true) {
            String exit = "exit";
            System.out.print("명령: ");
            String cmd = sc.nextLine();
            System.out.printf("입력받은 명령어: %s\n", cmd);

            if (cmd.equalsIgnoreCase(exit)) {
                break;
            }
        }
        System.out.println("== Exit Dashboard ==");
        sc.close();
    }
}