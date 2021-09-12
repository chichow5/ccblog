package com.example.ccblog.util;

import com.example.ccblog.service.IArticleService;
import com.example.ccblog.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Configuration
public class WebUtils {
    @Autowired
    private IArticleService articleService;

    public static String toArticle(Integer id){
        return "/article/" + id;
    }

    public Integer getPageCount(){
        return (articleService.countArticle() / 5) + 1;
    }

    public static String PagingPrevious(String prefix, int page){
        return prefix+( page==1 ? 1:page-1);
    }

    public static String PagingNext(String prefix, int page, int count){
        return prefix+(page == count ? page:page+1);
    }

    public static String[] splitBySpace(String in){
        int last = 0, next = 0;
        ArrayList<String> list = new ArrayList<>();
        while(true){
            next = in.indexOf(' ', last);
            if (next != -1){
                if (next != last){
                    list.add(in.substring(last, next));
                }
                last = next+1;
            }else{
                next = in.length();
                if (next != last) list.add(in.substring(last,next));
                break;
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String cutString(String in, int len){
        if (len >= in.length()) return in;
        else return in.substring(0, len);
    }
}
