package com.feiyi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Papper {
    private int id;
    private String title;
    private String description;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pub_date;
    private String url;

    public Papper() {
    }

    @Override
    public String toString() {
        return "Papper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", pub_date=" + pub_date +
                ", url='" + url + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Papper(int id, String title, String description, String author, Date pub_date, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.pub_date = pub_date;
        this.url = url;
    }
}
