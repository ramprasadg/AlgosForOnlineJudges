package com.ramprasadg.generic.graph.mst;

import java.util.*;

public class Prims {
    Map<String, Vertex> adjList;
    HashSet<String> visitedVertices = new HashSet<String>();

    public Prims() {
        adjList = new HashMap<String, Vertex>();
    }

    public void addEdge(String vertex1, String vertex2, int cost) {
        addEdge(new Edge(vertex1, vertex2, cost));
    }

    public void addEdge(Edge edge) {
        if (!adjList.containsKey(edge.vertex1Name)) {
            adjList.put(edge.vertex1Name, new Vertex(edge.vertex1Name));
        }

        Vertex vertex1 = adjList.get(edge.vertex1Name);
        vertex1.edges.add(edge);

        if (!adjList.containsKey(edge.vertex2Name)) {
            adjList.put(edge.vertex2Name, new Vertex(edge.vertex2Name));
        }

        Vertex vertex2 = adjList.get(edge.vertex2Name);
        vertex2.edges.add(edge);
    }

    public void displayGraph() {
        Collection<Vertex> vertexCollection = adjList.values();
        for (Vertex v : vertexCollection) {
            System.out.println(v);
        }
    }

    public Edge getMinimumUnvisitedEdge() {
        Iterator<String> itr = visitedVertices.iterator();
        Edge minEdge = null;
        int minCost = Integer.MAX_VALUE;
        while (itr.hasNext()) {
            String vertexName = itr.next();
            Vertex v = adjList.get(vertexName);
            List<Edge> edges = v.edges;
            for (Edge e : edges) {
                int cost = e.cost;
                boolean visitedBothVertices = visitedVertices.contains(e.vertex1Name)
                        && visitedVertices.contains(e.vertex2Name);
                if (cost < minCost && !visitedBothVertices) {
                    minCost = cost;
                    minEdge = e;
                }
            }
        }
        System.out.println("Minedge: " + minEdge);
        return minEdge;
    }

    public void primsAlgo() {
        Vertex firstVertex = adjList.values().iterator().next();
        visitedVertices.add(firstVertex.name);
        Edge minEdge = getMinimumUnvisitedEdge();
        while (minEdge != null) {
            visitedVertices.add(minEdge.vertex1Name);
            visitedVertices.add(minEdge.vertex2Name);
            minEdge = getMinimumUnvisitedEdge();
        }
    }

    public static void main(String[] args) {
        Prims prim = new Prims();

        prim.addEdge("1", "2", 3);
        prim.addEdge("1", "3", 2);
        prim.addEdge("2", "5", 1);
        prim.addEdge("3", "4", 5);
        prim.addEdge("4", "5", 7);
        prim.addEdge("4", "6", 8);

        // prim.displayGraph();
        prim.primsAlgo();
    }
}
