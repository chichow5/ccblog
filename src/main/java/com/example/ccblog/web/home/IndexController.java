package com.example.ccblog.web.home;

import com.example.ccblog.config.BlogConfig;
import com.example.ccblog.module.domian.Article;
import com.example.ccblog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private BlogConfig blogConfig;

    @GetMapping("/")
    public String toIndex(HttpServletRequest request){
        return this.index(request,1,5);
    }

    @GetMapping("/page/{p}")
    public String index(
            HttpServletRequest request,
            @PathVariable("p") int page,
            @RequestParam(value="count",defaultValue="5") int count){
        List<Article> articles = articleService.selectArticleWithPage(page,count);
        request.setAttribute("articles",articles);
        request.setAttribute("currentpage", page);
        request.setAttribute("pagecount",(articleService.countArticle()/count)+1);
        /*request.setAttribute("pagecount",3);*/
        return "home/index";
    }

    @GetMapping("/article/{id}")
    public String viewArticle(HttpServletRequest request,@PathVariable("id") int id) throws IOException {
        Path path = Path.of(blogConfig.html_folder + "/" + id +".html");
        request.setAttribute("content", Files.readString(path));
        request.setAttribute("article", articleService.selectArticleWithId(id));
        return "home/article";
    }
}
