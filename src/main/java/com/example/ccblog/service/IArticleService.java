package com.example.ccblog.service;

import com.example.ccblog.module.domian.Article;

import java.util.List;

public interface IArticleService {
    List<Article> selectArticleWithPage(Integer page, Integer count);
    Article selectArticleWithId(Integer id);
    String selectTitleWithId(Integer id);
    Integer countArticle();
    String selectCategoryWithType(Integer ctg_type);
    List<String> selectAllCategories();
    Integer updateArticleWithId(Article article);
    Integer insertArticle(Article article);
    void deleteArticleWithId(Integer id);
}
