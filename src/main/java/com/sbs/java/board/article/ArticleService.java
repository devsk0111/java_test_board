package com.sbs.java.board.article;

import com.sbs.java.board.container.Container;

import java.util.List;
import java.util.stream.IntStream;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = Container.articleRepository;
    }

    public void testData() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> write("제목" + i, "내용" + i));
    }

    public int write(String title, String content) {
        return articleRepository.write(title, content);
    }
    public Article findById(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> getArticles(String orderBy, String searchKeyword) {
        return articleRepository.getArticles(orderBy, searchKeyword);
    }

    public void modify(int id, String title, String content) {
        articleRepository.modify(id, title, content);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }
}
