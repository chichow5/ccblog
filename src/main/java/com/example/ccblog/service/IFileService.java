package com.example.ccblog.service;

public interface IFileService {
    String getHTMLWithId(Integer id);
    String getMarkdownWithId(Integer id);
    Boolean updateHTMLWithId(Integer id, String content);
    Boolean updateMarkdownWithId(Integer id, String content);
    Boolean updateArticleWithId(Integer id, String c_html, String c_md);
    Boolean deleteHTMLWithId(Integer id);
    Boolean deleteMarkdownWithId(Integer id);
    Boolean deleteArticleWithId(Integer id);
}
