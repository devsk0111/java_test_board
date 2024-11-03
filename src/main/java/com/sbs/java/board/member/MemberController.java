package com.sbs.java.board.member;

import com.sbs.java.board.container.Container;
import java.util.List;

public class MemberController {

    private int lastId;
    private List<Member> members;

    private MemberService memberService;


    public MemberController() {
        memberService = Container.memberService;

        memberService.testData();
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

            Member member = memberService.findByUserId(userId);

            if (member != null) {
                System.out.printf("%s는 존재하는 아이디입니다. 다시 입력해주세요.", userId);
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

        int id = memberService.join(userId, password, name);


        System.out.printf("%d번 회원이 생성되었습니다.\n", id);
    }

    public void doLogin() {
        String userId;
        String password;
        Member member;

        System.out.println("== 로그인 ==");

        while (true) {
            System.out.print("로그인 아이디 :");
            userId = Container.scanner.nextLine();

            if (userId.trim().isEmpty()) {
                System.out.println("로그인 아이디를 입력해주세요.");
                continue;
            }

            member = memberService.findByUserId(userId);

            if (member == null) {
                System.out.printf("\"%s\"는 존재하지 않은 아이디입니다. 다시 입력해주세요.", userId);
                continue;
            }

            break;
        }

        int tryMaxCount = 3;
        int tryCount = 0;
        boolean exit = false;


        while (true) {

            if (tryCount == tryMaxCount) {
                System.out.println("모든 입력 기회를 소진했습니다. 다시 로그인 해주십시오.");
                exit = true;
                break;
            }

            System.out.print("로그인 비밀번호 :");
            password = Container.scanner.nextLine();

            if (password.trim().isEmpty()) {
                System.out.println("비밀번호 입력해주세요");
                continue;
            }
            if (!member.getUserPassword().equals(password)) {
                System.out.println("비밃번호가 일치 하지 않습니다");
                tryCount ++;
                System.out.printf("%d번의 기회 중 %d번 틀렸습니다\n",tryMaxCount, tryCount);
                continue;
            }
            break;

        }
        if (!exit) {
            System.out.printf("\"%s\"님 로그인 했습니다\n", member.getName());
        }
    }
}
