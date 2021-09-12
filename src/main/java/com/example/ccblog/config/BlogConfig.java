package com.example.ccblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
public class BlogConfig {
    @Value("${blogconfig.tb-user}")
    public String tb_user;
    @Value("${blogconfig.tb-auth}")
    public String tb_auth;
    @Value("${blogconfig.tb-user-auth}")
    public String tb_user_auth;
    @Value("${blogconfig.token-valid-sec}")
    public Integer token_valid_sec;
    @Value("${blogconfig.markdown-folder}")
    public String makrdown_folder;
    @Value("${blogconfig.html-folder}")
    public String html_folder;

    @Override
    public String toString() {
        return "BlogConfig{" +
                "tb_user='" + tb_user + '\'' +
                ", tb_auth='" + tb_auth + '\'' +
                ", tb_user_auth='" + tb_user_auth + '\'' +
                '}';
    }
}
