package com.sbs.java.board.member;

import com.sbs.java.board.article.Article;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private int lastId;
    private List<Member> members;

    public MemberRepository() {
        lastId = 0;
        members = new ArrayList<>();
    }
    public int join(String userId, String password, String name) {
        int id = ++lastId;
        Member member = new Member(id, userId, password, name);
        members.add(member);
        return id;
    }

    public Member findByUserId(String userId) {
        for (Member member : members) {
            if(member.getUserId().equals(userId)) {
                return member;
            }
        }

        return null;
    }
}
