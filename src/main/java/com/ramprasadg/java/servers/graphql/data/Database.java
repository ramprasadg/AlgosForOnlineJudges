package com.ramprasadg.java.servers.graphql.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    public void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                logger.info("Connected to the database");
                createTables(conn);
            }
        } catch (SQLException e) {
            logger.error("Error connecting to the database", e);
        }
    }

    private void createTables(Connection conn) throws SQLException {
        String dropUserTable = "DROP TABLE IF EXISTS users";
        String dropPostTable = "DROP TABLE IF EXISTS posts";

        String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL)";

        String createPostTable = "CREATE TABLE IF NOT EXISTS posts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "content TEXT NOT NULL," +
                "userId INTEGER NOT NULL," +
                "FOREIGN KEY (userId) REFERENCES users(id))";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(dropUserTable);
            stmt.execute(dropUserTable);
            stmt.execute(createUserTable);
            stmt.execute(createPostTable);
            logger.info("Tables created successfully");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email FROM users";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error getting all users", e);
        }
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting user by id", e);
        }
        return null;
    }

    public List<Post> getPostsByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT id, title, content, userId FROM posts WHERE userId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("userId"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting posts by user id", e);
        }
        return posts;
    }

    public Post getPostById(int id) {
        String sql = "SELECT id, title, content, userId FROM posts WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("userId"));
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting post by id", e);
        }
        return null;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT id, title, content, userId FROM posts";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("userId"));
                posts.add(post);
            }
        } catch (SQLException e) {
            logger.error("Error getting all posts", e);
        }
        return posts;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            logger.info("User added successfully");
        } catch (SQLException e) {
            logger.error("Error adding user", e);
        }
    }

    public void addPost(Post post) {
        String sql = "INSERT INTO posts (title, content, userId) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, post.getUserId());
            pstmt.executeUpdate();
            logger.info("Post added successfully");
        } catch (SQLException e) {
            logger.error("Error adding post", e);
        }
    }
}

