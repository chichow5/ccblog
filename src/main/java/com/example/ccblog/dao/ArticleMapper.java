package com.example.ccblog.dao;

import com.example.ccblog.module.domian.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("SELECT * FROM article WHERE id=#{id}")
    Article selectArticleWithId(Integer id);

    @Select("SELECT * FROM article ORDER BY id DESC")
    List<Article> selectAllArticles();

    @Select("SELECT COUNT(1) FROM article")
    Integer countArticle();

    @Select("SELECT title FROM article WHERE id=#{id}")
    String selectTitleWithId(Integer id);

    @Select("SELECT string FROM categories WHERE type=#{ctg_type}")
    String selectCategory(Integer ctg_type);

    @Select("SELECT string FROM categories")
    List<String> selectAllCategories();

    Integer updateArticleWithId(Article _article);

    @Insert("INSERT INTO article (title,content,created,modified,tags,ctg_type,allow_comment)" +
            "VALUES (#{title},#{content},#{created},#{modified},#{tags},#{ctg_type},#{allow_comment})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertArticle(Article article);

    @Delete("DELETE FROM article WHERE id=#{id}")
    public void deleteArticleWithId(Integer id);
}
