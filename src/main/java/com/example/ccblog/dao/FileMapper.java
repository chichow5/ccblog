package com.example.ccblog.dao;

import com.example.ccblog.config.BlogConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class FileMapper {

    private final String markdown_folder_prefix;
    private final String html_folder_prefix;
    Logger logger;

    @Autowired
    public FileMapper(BlogConfig blogConfig) {
        this.markdown_folder_prefix = blogConfig.makrdown_folder + "/";
        this.html_folder_prefix = blogConfig.html_folder + "/";
        logger = Logger.getLogger("ccblog");
    }

    public String getHTMLWithId(Integer id) {
        Path path = Path.of(html_folder_prefix + id + ".html");
        String result;
        try{
            result = Files.readString(path);
        }catch (IOException e){
            e.printStackTrace();
            result = "failed to load file";
            logger.log(Level.WARNING, result +":"+ path);
        }
        return result;
    }

    public Boolean updateHTMLWithId(Integer id, String content){
        Path path = Path.of(html_folder_prefix + id + ".html");
        try{
            Files.writeString(path, content);
        }catch(IOException e){
            e.printStackTrace();
            logger.log(Level.WARNING,"failed to write file:"+path);
            return false;
        }
        return true;
    }

    public String getMarkdownWithId(Integer id) {
        Path path = Path.of(markdown_folder_prefix + id + ".md");
        String result;
        try{
            result = Files.readString(path);
        }catch(IOException e){
            e.printStackTrace();
            result = "failed to load file";
            logger.log(Level.WARNING, result +":"+ path);
        }
        return result;
    }

    public Boolean updateMarkdownWithId(Integer id, String content){
        Path path = Path.of(markdown_folder_prefix + id + ".md");
        try{
            Files.writeString(path, content);
        }catch(IOException e){
            e.printStackTrace();
            logger.log(Level.WARNING,"failed to write file:"+path);
            return false;
        }
        return true;
    }

    public Boolean deleteMarkdownWithId(Integer id) {
        Path path = Path.of(markdown_folder_prefix + id + ".md");
        try{
            if (Files.exists(path)) Files.delete(path);
        }catch(Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING,"failed to delete file:" +path);
            return false;
        }
        return true;
    }

    public Boolean deleteHTMLWithId(Integer id) {
        Path path = Path.of(html_folder_prefix + id + ".html");
        try{
            if (Files.exists(path)) Files.delete(path);
        }catch(Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING,"failed to delete file:" +path);
            return false;
        }
        return true;
    }
}
