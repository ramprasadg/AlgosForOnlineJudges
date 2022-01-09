package com.ramprasadg.generic.graph.mst;

public class Edge {
    String vertex1Name;
    String vertex2Name;
    int cost;
    
    public Edge(String vertex1, String vertex2, int cost) {
        vertex1Name = vertex1;
        vertex2Name = vertex2;
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "Vertex1:" + vertex1Name + " Vertex2:" + vertex2Name + " Cost:" + cost;
    }
}
