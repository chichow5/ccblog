package com.example.ccblog.web.admin;

import com.example.ccblog.module.domian.Article;
import com.example.ccblog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/admin")
    public String adminPage(HttpServletRequest request){
        return this.adminPage(request, 1, 10);
    }
    @GetMapping("/admin/{p}")
    public String adminPage(
            HttpServletRequest request,
            @PathVariable("p") int page,
            @RequestParam(value="count",defaultValue="10") int count
    ){
        List<Article> articles = articleService.selectArticleWithPage(page,count);
        request.setAttribute("articles",articles);
        request.setAttribute("currentpage", page);
        request.setAttribute("pagecount",(articleService.countArticle()/count)+1);
        return "admin/admin";
    }
}
