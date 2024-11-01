package com.sbs.java.board.container;

import com.sbs.java.board.article.ArticleController;

import java.util.Scanner;

public class Container {
    public static Scanner scanner;
    public static ArticleController articleController;

    static {
        scanner = new Scanner(System.in);
        articleController = new ArticleController();
    }
}
