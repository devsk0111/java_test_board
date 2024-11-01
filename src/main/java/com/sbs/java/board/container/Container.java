package com.sbs.java.board.container;

import com.sbs.java.board.article.ArticleController;
import com.sbs.java.board.article.ArticleRepository;
import com.sbs.java.board.article.ArticleService;

import java.util.Scanner;

public class Container {
    public static Scanner scanner;
    public static ArticleRepository articleRepository;
    public static ArticleService articleService;
    public static ArticleController articleController;

    static {
        scanner = new Scanner(System.in);

        articleRepository= new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }
}
