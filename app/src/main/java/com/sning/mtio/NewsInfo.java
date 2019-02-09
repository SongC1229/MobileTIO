package com.sning.mtio;

public class NewsInfo {

    String title;
    String time;
    String author;
    String url;

    public NewsInfo() {
    }

    public NewsInfo(String title, String time, String author, String url) {
        this.title = title;
        this.time = time;
        this.author = author;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
