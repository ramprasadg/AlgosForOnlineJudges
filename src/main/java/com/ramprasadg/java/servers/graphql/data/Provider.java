package com.ramprasadg.java.servers.graphql.data;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import graphql.GraphQL;
import graphql.kickstart.execution.config.GraphQLSchemaProvider;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Provider implements GraphQLSchemaProvider {
    private static final Logger logger = LoggerFactory.getLogger(Provider.class);
    private final DataFetchers dataFetchers;
    private GraphQL graphQL;

    public Provider(DataFetchers dataFetchers) {
        this.dataFetchers = dataFetchers;
        this.graphQL = buildGraphQL();
    }

    private GraphQL buildGraphQL() {
        GraphQLSchema graphQLSchema = buildSchema();
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema() {
        String schema = "/GraphQLSchema.graphqls";
        InputStream inputStream = getClass().getResourceAsStream(schema);
        if (inputStream == null) {
            throw new RuntimeException("Schema file not found: " + schema);
        }
        try (Reader reader = new InputStreamReader(inputStream)) {
            TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(reader);
            RuntimeWiring runtimeWiring = buildWiring();
            SchemaGenerator schemaGenerator = new SchemaGenerator();
            return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        } catch (Exception e) {
            logger.error("Error building schema", e);
            throw new RuntimeException("Error building schema", e);
        }
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(
                        newTypeWiring("Query")
                                .dataFetcher("users", dataFetchers.getAllUsers())
                                .dataFetcher("user", dataFetchers.getUserById())
                                .dataFetcher("posts", dataFetchers.getAllPosts()))
                .type(newTypeWiring("User").dataFetcher("posts", dataFetchers.getPostsByUserId()))
                .type(
                        newTypeWiring("Mutation")
                                .dataFetcher("addUser", dataFetchers.addUser())
                                .dataFetcher("addPost", dataFetchers.addPost()))
                .build();
    }

    @Override
    public GraphQLSchema getSchema() {
        return graphQL.getGraphQLSchema();
    }

    @Override
    public GraphQLSchema getReadOnlySchema() {
        return graphQL.getGraphQLSchema();
    }
}
