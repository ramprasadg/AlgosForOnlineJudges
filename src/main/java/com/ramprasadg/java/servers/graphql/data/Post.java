package com.ramprasadg.java.servers.graphql.data;

public class Post {
    private int id;
    private String title;
    private String content;
    private int userId;

    public Post(int id, String title, String content, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }
}
