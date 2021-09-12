package com.example.ccblog.service.impl;

import com.example.ccblog.dao.ArticleMapper;
import com.example.ccblog.module.domian.Article;
import com.example.ccblog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> selectArticleWithPage(Integer page, Integer count) {
        List<Article> all = articleMapper.selectAllArticles();
        List<Article> result = new ArrayList<>();
        for (int i=(page-1)*count,end=i+count; i<all.size() && i<end; i++){
            result.add(all.get(i));
        }
        return result;
    }

    @Override
    public Article selectArticleWithId(Integer id) {
        return articleMapper.selectArticleWithId(id);
    }

    @Override
    public String selectTitleWithId(Integer id) {
        return articleMapper.selectTitleWithId(id);
    }

    @Override
    public Integer countArticle() {
        return articleMapper.countArticle();
    }

    @Override
    public String selectCategoryWithType(Integer ctg_type) {
        return articleMapper.selectCategory(ctg_type);
    }

    @Override
    public List<String> selectAllCategories() {
        return articleMapper.selectAllCategories();
    }

    @Override
    public Integer updateArticleWithId(Article article) {
        return articleMapper.updateArticleWithId(article);
    }

    @Override
    public Integer insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public void deleteArticleWithId(Integer id) {
        articleMapper.deleteArticleWithId(id);
    }
}
