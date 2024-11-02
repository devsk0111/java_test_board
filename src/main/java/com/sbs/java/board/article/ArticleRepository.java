package com.sbs.java.board.article;

import com.sbs.java.board.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private int lastId;
    private List<Article> articles;

    public ArticleRepository() {
        lastId = 0;
        articles = new ArrayList<>();
    }

    public int write(String title, String content) {
        int id = ++lastId;
        Article article = new Article(id, title, content);
        articles.add(article);

        return id;
    }

    public Article findById(int id) {
        for(Article article : articles) {
            if(article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public List<Article> getSortedArticles(String orderBy) {
        List<Article> sortedArticles = articles;

        if(orderBy.equals("idAsc")) {
            return articles;
        }

        if(orderBy.equals("idDesc")) {
            sortedArticles = Util.reverseList(articles);
        }
        return sortedArticles;
    }

    public List<Article> getArticles(String orderBy, String searchKeyword) {
        List<Article> sortedArticles = getSortedArticles(orderBy);
        List<Article> filteredArticles = sortedArticles;

        if (!searchKeyword.isEmpty()) {
            for (Article article : sortedArticles) {
                boolean matched = article.getTitle().contains(searchKeyword) || article.getContent().contains(searchKeyword);

                filteredArticles = new ArrayList<>();

                if(matched) {
                    filteredArticles.add(article);
                }
            }
            return sortedArticles;
        }

    return filteredArticles;
    }

    public void modify(int id, String title, String content) {
        Article article = findById(id);

        if (article != null) {
            article.setTitle(title);
            article.setContent(content);
        }
  }

    public void delete(int id) {
        Article article = findById(id);
        if(article != null) {
            articles.remove(article);
        }
    }
}
