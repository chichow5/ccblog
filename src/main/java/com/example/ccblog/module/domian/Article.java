package com.example.ccblog.module.domian;

import java.util.Date;
import java.util.Map;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private Date created;
    private Date modified;
    private Integer ctg_type;
    private String tags;
    private Boolean allow_comment;

    public Article(){ }

    public Article(Map<String, Object> map){
        Object t;
        t = map.get("id");
        if (t != null) id = Integer.parseInt((String) t);
        else id = null;

        title = (String) map.get("title");
        content = (String) map.get("content");
        ctg_type = Integer.parseInt((String) map.get("ctg_type"));
        tags = (String) map.get("tags");

        allow_comment = true;
    }

    public Article(Integer id, String title, String content, Date created, Date modified, Integer ctg_type, String tags, Boolean allow_comment) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = modified;
        this.ctg_type = ctg_type;
        this.tags = tags;
        this.allow_comment = allow_comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getCtg_type() {
        return ctg_type;
    }

    public void setCtg_type(Integer ctg_type) {
        this.ctg_type = ctg_type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(Boolean allow_comment) {
        this.allow_comment = allow_comment;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", ctg_type=" + ctg_type +
                ", tags='" + tags + '\'' +
                ", allow_comment=" + allow_comment +
                '}';
    }
}
