package com.example.ccblog.web.admin;

import com.example.ccblog.module.domian.Article;
import com.example.ccblog.service.IArticleService;
import com.example.ccblog.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;

@Controller
public class EditorController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private IFileService fileService;

    @GetMapping("/admin/edit")
    public String toEditArticle(HttpServletRequest request){
        Article article = new Article();
        request.setAttribute("article",article);
        request.setAttribute("categories",articleService.selectAllCategories());
        request.setAttribute("content","");
        request.setAttribute("category","");
        return "admin/editor";
    }



    @GetMapping("/admin/edit/{id}")
    public String toEditArticle(
            HttpServletRequest request,
            @PathVariable("id") int id){
        request.setAttribute("content",fileService.getMarkdownWithId(id));
        request.setAttribute("categories",articleService.selectAllCategories());
        Article article = articleService.selectArticleWithId(id);
        request.setAttribute("category",articleService.selectCategoryWithType(article.getCtg_type()));
        request.setAttribute("article",article);
        return "admin/editor";
    }
}
