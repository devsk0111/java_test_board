package com.sbs.java.board.container;

import com.sbs.java.board.article.ArticleController;
import com.sbs.java.board.article.ArticleRepository;
import com.sbs.java.board.article.ArticleService;
import com.sbs.java.board.member.MemberController;
import com.sbs.java.board.member.MemberRepository;
import com.sbs.java.board.member.MemberService;

import java.util.Scanner;

public class Container {
    public static Scanner scanner;

    public static MemberRepository memberRepository;
    public static ArticleRepository articleRepository;

    public static MemberService memberService;
    public static ArticleService articleService;

    public static MemberController memberController;
    public static ArticleController articleController;

    static {
        scanner = new Scanner(System.in);

        memberRepository = new MemberRepository();
        articleRepository = new ArticleRepository();

        memberService = new MemberService();
        articleService = new ArticleService();

        memberController = new MemberController();
        articleController = new ArticleController();
    }
}
