package com.ramprasadg.generic.trees.binarytree;

public class Node {
    public int value;
    public Node left;
    public Node right;
    
    public Node(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
