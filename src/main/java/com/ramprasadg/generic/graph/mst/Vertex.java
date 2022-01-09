package com.ramprasadg.generic.graph.mst;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    String name;
    List<Edge> edges;
    
    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Name:" + name + " Edges:" + edges;
    }
}
