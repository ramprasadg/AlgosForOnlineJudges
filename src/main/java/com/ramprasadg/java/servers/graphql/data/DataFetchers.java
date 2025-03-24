package com.ramprasadg.java.servers.graphql.data;

import graphql.schema.DataFetcher;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataFetchers {
    private static final Logger logger = LoggerFactory.getLogger(DataFetchers.class);
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DataFetchers(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public DataFetcher<List<User>> getAllUsers() {
        return dataFetchingEnvironment -> {
            logger.info("Fetching all users");
            return userRepository.getAllUsers();
        };
    }

    public DataFetcher<User> getUserById() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            int userId = Integer.parseInt(arguments.get("id").toString());
            logger.info("Fetching user with id: {}", userId);
            return userRepository.getUserById(userId);
        };
    }

    public DataFetcher<List<Post>> getPostsByUserId() {
        return dataFetchingEnvironment -> {
            User user = dataFetchingEnvironment.getSource();
            logger.info("Fetching posts for user with id: {}", user.getId());
            return postRepository.getPostsByUserId(user.getId());
        };
    }

    public DataFetcher<List<Post>> getAllPosts() {
        return dataFetchingEnvironment -> {
            logger.info("Fetching all posts");
            return postRepository.getAllPosts();
        };
    }

    public DataFetcher<User> addUser() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            String name = arguments.get("name").toString();
            String email = arguments.get("email").toString();
            logger.info("Adding user with name: {} and email: {}", name, email);
            User user = new User(0, name, email);
            userRepository.addUser(user);
            return user;
        };
    }

    public DataFetcher<Post> addPost() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            String title = arguments.get("title").toString();
            String content = arguments.get("content").toString();
            int userId = Integer.parseInt(arguments.get("userId").toString());
            logger.info("Adding post with title: {}, content: {} and userId: {}", title, content,
                    userId);
            Post post = new Post(0, title, content, userId);
            postRepository.addPost(post);
            return post;
        };
    }
}
