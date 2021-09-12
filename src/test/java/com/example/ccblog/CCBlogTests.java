package com.example.ccblog;

import com.example.ccblog.config.BlogConfig;
import com.example.ccblog.util.MyUtils;
import com.example.ccblog.util.WebUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
class CCBlogTests {

    @Autowired
    private BlogConfig blogConfig;
    @Test
    void contextLoads() {
    }

    @Test
    public void encode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwd = "pl,okmijn";
        System.out.println(encoder.encode(passwd));
    }

    @Test
    public void SQLTests(){
        String authoritySQL = "select u.username,a.authority  from "
                + blogConfig.tb_user + " u,"
                + blogConfig.tb_auth + " a,"
                + blogConfig.tb_user_auth + " ua "
                //+ " where ua.user_id=u.id and ua.authority_type=a.id and u.username=?";
                + " where ua.user_id=u.id and ua.authority_type=a.id and u.username=?";
        System.out.println(authoritySQL);
    }


    @Test
    public void md2html() throws IOException {
        final Path p_md = Path.of("/home/chi/tmp/a.md");
        final Path p_h5 = Path.of("/home/chi/tmp/a.html");
        String mdcontent = Files.readString(p_md);
        Files.writeString(p_h5, MyUtils.md2h5(mdcontent));
        System.out.println("done");
    }

    @Test
    public void looptest(){
        String s = "a   b   c d";
        String[] ss = WebUtils.splitBySpace(s);
        for (String str : ss){
            System.out.println(str.length() + "'" + str + "'");
        }
    }

}
