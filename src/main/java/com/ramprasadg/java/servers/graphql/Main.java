package com.ramprasadg.java.servers.graphql;

import com.ramprasadg.java.servers.graphql.data.DataFetchers;
import com.ramprasadg.java.servers.graphql.data.Database;
import com.ramprasadg.java.servers.graphql.data.PostRepository;
import com.ramprasadg.java.servers.graphql.data.Provider;
import com.ramprasadg.java.servers.graphql.data.UserRepository;
import graphql.kickstart.execution.config.GraphQLSchemaProvider;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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

        // Create and configure Jetty Server
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        // Add GraphQL servlet
        GraphQLHttpServlet graphQLServlet = GraphQLHttpServlet.with(config);
        ServletHolder holder = new ServletHolder(graphQLServlet);
        context.addServlet(holder, "/graphql");

        try {
            server.start();
            logger.info("Server started on port 8080");
            server.join();
        } catch (Exception e) {
            logger.error("Error starting server", e);
        }
    }
}
