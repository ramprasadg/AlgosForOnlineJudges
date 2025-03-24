package com.ramprasadg.java.servers.graphql.data;

import java.util.List;

public class UserRepository {
    private final Database database;

    public UserRepository(Database database) {
        this.database = database;
        // Add some initial data
        User user1 = database.getUserById(1);
        if(user1 == null) {
            database.addUser(new User(1, "John Doe", "john.doe@example.com"));
        }

        User user2 = database.getUserById(2);
        if(user2 == null) {
            database.addUser(new User(2, "Jane Smith", "jane.smith@example.com"));
        }
    }

    public List<User> getAllUsers() {
        return database.getAllUsers();
    }

    public User getUserById(int id) {
        return database.getUserById(id);
    }

    public void addUser(User user) {
        database.addUser(user);
    }
}

