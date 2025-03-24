package com.ramprasadg.amazon;

import java.util.*;

public class BFS {
    Map<Integer, User> map = new HashMap<>();

    public static class User {
        public int id;
        public List<User> friends;
    }

    public void addUser(User a) {
        map.put(a.id, a);
    }

    public void getUser(int id) {
        map.get(id);
    }

    public void getUser(User a) {
        map.get(a.id);
    }

    public int findDegreeOfSeparation(User a, User b) {
        int level = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> qNext = new LinkedList<>();
        q.add(a.id);

        Set<Integer> visited = new HashSet<>();

        while (!q.isEmpty()) {
            int id = q.poll();

            if (visited.contains(id)) {
                continue;
            }

            User ele = map.get(id);

            if (ele == null) {
                return -1;
            }

            if (ele == b) {
                return level;
            } else {
                for (User u : ele.friends) {
                    qNext.add(u.id);
                }
            }

            visited.add(id);

            if (q.isEmpty()) {
                level++;
                q.addAll(qNext);
                qNext.clear();
            }
        }

        return -1;
    }

    public static void main(String args[]) {
        User a = new User();
        a.id = 1;
        a.friends = new LinkedList<>();

        User b = new User();
        b.id = 2;
        b.friends = new LinkedList<>();

        User c = new User();
        c.id = 3;
        c.friends = new LinkedList<>();

        User d = new User();
        d.id = 4;
        d.friends = new LinkedList<>();

        a.friends.add(b);
        b.friends.add(c);
        c.friends.add(d);

        BFS bfs = new BFS();
        bfs.addUser(a);
        bfs.addUser(b);
        bfs.addUser(c);
        bfs.addUser(d);

        System.out.println(bfs.findDegreeOfSeparation(b, d));
    }
}
