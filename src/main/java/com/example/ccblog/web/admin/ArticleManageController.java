package com.example.ccblog.web.admin;

import com.example.ccblog.module.domian.Article;
import com.example.ccblog.service.IArticleService;
import com.example.ccblog.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class ArticleManageController {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private IFileService fileService;

    @PostMapping("/admin/add")
    public void addArticle(
            HttpServletResponse response,
            @RequestParam Map<String,Object> posts
    ) throws IOException {

        System.out.println(posts);
        Article article = new Article(posts);
        article.setCreated(new Date());
        article.setModified(article.getCreated());
        articleService.insertArticle(article);
        String c_md = (String) posts.get("test-editormd-markdown-doc");
        String c_html = (String) posts.get("test-editormd-html-code");
        fileService.updateArticleWithId(article.getId(), c_html, c_md);
        response.sendRedirect("/article/"+article.getId());
    }

    @PostMapping("/admin/update/{id}")
    public void showPost(
            HttpServletResponse response,
            @RequestParam Map<String,Object> posts,
            @PathVariable("id") int id
    ) throws IOException {
        String c_md = (String) posts.get("test-editormd-markdown-doc");
        String c_html = (String) posts.get("test-editormd-html-code");
        fileService.updateArticleWithId(id, c_html, c_md);
        Article article = new Article(posts);
        article.setModified(new Date());
        article.setId(id);
        articleService.updateArticleWithId(article);
        response.sendRedirect("/article/"+id);
    }

    @GetMapping("/admin/delete/{id}")
    public void deleteArticle(
            @PathVariable("id") int id,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        articleService.deleteArticleWithId(id);
        String url = request.getHeader("referer");
       // System.out.println(url);
        if (url.contains("article")) response.sendRedirect("/");
        else response.sendRedirect("/admin");
    }
}
