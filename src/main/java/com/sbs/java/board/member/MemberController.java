package com.sbs.java.board.member;

import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {

    private int lastId;
    private List<Member> members;

    public MemberController() {
        lastId = 0;
        members = new ArrayList<>();
    }

    public void doJoin() {
        String userId;
        String password;
        String passwordConfirm;
        String name;

        System.out.println("== 회원 가입 ==");

        while (true) {
            System.out.print("로그인 아이디 :");
            userId = Container.scanner.nextLine();

            if(userId.trim().isEmpty()) {
                System.out.println("로그인 아이디를 입력해주세요.");
                continue;
            }

            break;

        }

        //로그인 비밀번호

        while (true) {
            System.out.print("로그인 비밀번호 :");
            password = Container.scanner.nextLine();

            if(password.trim().isEmpty()) {
                System.out.println("비밀번호 입력해주세요");
                continue;
            }

            //로그인 비밀번호 확인
            while(true) {

                System.out.print("로그인 비밀번호 확인 :");
                passwordConfirm = Container.scanner.nextLine();

                if(passwordConfirm.trim().isEmpty()) {
                    System.out.println("비밀번호 확인해주세요");
                    continue;
                }

                if(!passwordConfirm.equals(password)) {
                    System.out.println("입력하신 비밀번호 일치하지 않습니다");
                    continue;
                }
                break;
            }

            break;

        }

        //이름
        while (true) {
            System.out.print("이름 :");
            name = Container.scanner.nextLine();

            if(name.trim().isEmpty()) {
                System.out.println("이름 칸은 비면 안됩니다");
                continue;
            }
            break;
        }

        int id = ++lastId;

        Member member = new Member(id, userId, password, name);
        members.add(member);

        System.out.printf("%d번 회원이 생성되었습니다.\n", id);
    }
}