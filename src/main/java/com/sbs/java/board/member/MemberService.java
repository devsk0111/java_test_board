package com.sbs.java.board.member;

import com.sbs.java.board.container.Container;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = Container.memberRepository;
    }
    public int join(String userId, String password, String name) {
        return memberRepository.join(userId, password, name);
    }
}
