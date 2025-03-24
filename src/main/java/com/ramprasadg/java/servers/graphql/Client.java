package com.ramprasadg.java.servers.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final String GRAPHQL_ENDPOINT = "http://localhost:8080/graphql";

    public static void main(String[] args) {
        Client client = new Client();

        // Example 1: Get all users
        String getAllUsersQuery = "{ users { id name email } }";
        client.executeQuery(getAllUsersQuery);

        // Example 2: Get user by ID
        String getUserByIdQuery = "{ user(id: 1) { id name email } }";
        client.executeQuery(getUserByIdQuery);

        // Example 3: Get all posts
        String getAllPostsQuery = "{ posts { id title content user { id name } } }";
        client.executeQuery(getAllPostsQuery);

        // Example 4: Get posts by user ID
        String getPostsByUserIdQuery = "{ user(id: 1) { id name posts { id title content } } }";
        client.executeQuery(getPostsByUserIdQuery);

        // Example 5: Add a new user
        String addUserMutation = "mutation { addUser(name: \"New User\", email: \"new.user@example.com\") { id name email } }";
        client.executeQuery(addUserMutation);

        // Example 6: Add a new post
        String addPostMutation = "mutation { addPost(title: \"New Post\", content: \"This is a new post\", userId: 1) { id title content user { id name } } }";
        client.executeQuery(addPostMutation);
    }

    public void executeQuery(String query) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(GRAPHQL_ENDPOINT);
        httpPost.setHeader("Content-Type", "application/json");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode queryNode = objectMapper.createObjectNode();
            queryNode.put("query", query);

            StringEntity requestEntity = new StringEntity(queryNode.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(requestEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                logger.info("Response: {}", responseString);
                JsonNode jsonResponse = objectMapper.readTree(responseString);
                if (jsonResponse.has("errors")) {
                    logger.error("GraphQL errors: {}", jsonResponse.get("errors"));
                }
            }
        } catch (IOException e) {
            logger.error("Error executing GraphQL query", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("Error closing HTTP client", e);
            }
        }
    }
}
