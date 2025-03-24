package com.ramprasadg.java.servers.graphql.data;

import java.util.List;

public class PostRepository {
    private final Database database;

    public PostRepository(Database database) {
        this.database = database;
        // Add some initial data
        if (database.getPostById(1) == null) {
            database.addPost(new Post(1, "First Post", "This is the first post", 1));
        }
        if (database.getPostById(2) == null) {
            database.addPost(new Post(2, "Second Post", "This is the second post", 1));
        }
        if (database.getPostById(3) == null) {
            database.addPost(new Post(3, "Third Post", "This is the third post", 2));
        }
    }

    public List<Post> getPostsByUserId(int userId) {
        return database.getPostsByUserId(userId);
    }

    public List<Post> getAllPosts() {
        return database.getAllPosts();
    }

    public Post getPostById(int id) {
        return database.getPostById(id);
    }

    public void addPost(Post post) {
        database.addPost(post);
    }
}
