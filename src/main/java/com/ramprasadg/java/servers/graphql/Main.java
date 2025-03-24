package com.ramprasadg.java.servers.graphql;

import com.ramprasadg.java.servers.graphql.data.Database;
import com.ramprasadg.java.servers.graphql.data.PostRepository;
import com.ramprasadg.java.servers.graphql.data.UserRepository;
import com.ramprasadg.java.servers.graphql.data.DataFetchers;
import com.ramprasadg.java.servers.graphql.data.Provider;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.execution.config.GraphQLSchemaProvider;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Initialize the database
        Database database = new Database();
        database.initializeDatabase();

        // Initialize repositories
        UserRepository userRepository = new UserRepository(database);
        PostRepository postRepository = new PostRepository(database);

        // Initialize GraphQL data fetchers
        DataFetchers dataFetchers = new DataFetchers(userRepository, postRepository);

        // Initialize GraphQL schema provider
        GraphQLSchemaProvider schemaProvider = new Provider(dataFetchers);

        // Initialize GraphQL configuration
        GraphQLSchema schema = schemaProvider.getReadOnlySchema();
        GraphQLConfiguration config = GraphQLConfiguration.with(schema).build();

        // Initialize GraphQL servlet
        GraphQLHttpServlet graphQLServlet = GraphQLHttpServlet.with(config);

        // Create Spark service
        spark.Service http = spark.Service.ignite();
        http.port(8080);

        // Handle GraphQL POST requests
        http.post("/graphql", (request, response) -> {
            response.type("application/json");
            graphQLServlet.service(request.raw(), response.raw());
            return response.body();
        });

        // Add a test endpoint
        http.get("/test", (req, res) -> "GraphQL Server is running!");

        // Log server start
        logger.info("Server started on port 8080");
    }
}
