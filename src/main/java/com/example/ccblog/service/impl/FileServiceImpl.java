package com.example.ccblog.service.impl;

import com.example.ccblog.dao.FileMapper;
import com.example.ccblog.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public String getHTMLWithId(Integer id) {
        return fileMapper.getHTMLWithId(id);
    }

    @Override
    public String getMarkdownWithId(Integer id) {
        return fileMapper.getMarkdownWithId(id);
    }

    @Override
    public Boolean updateHTMLWithId(Integer id, String content) {
        return fileMapper.updateHTMLWithId(id, content);
    }

    @Override
    public Boolean updateMarkdownWithId(Integer id, String content) {
        return fileMapper.updateMarkdownWithId(id, content);
    }

    @Override
    public Boolean updateArticleWithId(Integer id, String c_html, String c_md) {
        Boolean result = true;
        result &= fileMapper.updateMarkdownWithId(id, c_md);
        result &= fileMapper.updateHTMLWithId(id, c_html);
        return result;
    }

    @Override
    public Boolean deleteHTMLWithId(Integer id) {
        return fileMapper.deleteHTMLWithId(id);
    }

    @Override
    public Boolean deleteMarkdownWithId(Integer id) {
        return fileMapper.deleteMarkdownWithId(id);
    }

    @Override
    public Boolean deleteArticleWithId(Integer id) {
        Boolean result = true;
        result &= fileMapper.deleteHTMLWithId(id);
        result &= fileMapper.deleteMarkdownWithId(id);
        return result;
    }
}
