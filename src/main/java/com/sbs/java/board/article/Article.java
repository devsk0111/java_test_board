package com.sbs.java.board.article;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article { //extends Object
    private int id;
    private String title;
    private String content;

}