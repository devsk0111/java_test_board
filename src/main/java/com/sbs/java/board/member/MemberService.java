package com.sbs.java.board.member;

import com.sbs.java.board.article.Article;
import com.sbs.java.board.container.Container;

import java.util.stream.IntStream;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = Container.memberRepository;
    }
    public int join(String userId, String password, String name) {
        return memberRepository.join(userId, password, name);
    }

    public void testData() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> join("user" + i, "user" + i, "user" + i));
    }
}
