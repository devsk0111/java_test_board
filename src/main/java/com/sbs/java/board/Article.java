package com.sbs.java.board;

class Article { //extends Object
    int id;
    String title;
    String content;

    Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{id : %d, title: \"%s\"}".formatted(id, title);
    }
}