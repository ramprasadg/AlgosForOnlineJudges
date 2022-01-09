package com.ramprasadg.hackerrank;

import java.util.ArrayList;

public class Quantiles {
    private static class Node {
        int v;
        int startIndex;
        int endIndex;
        
        public Node(int v, int startIndex, int endIndex) {
            this.v = v;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        
        @Override
        public String toString() {
            return "v:" + v + " s:" + startIndex + " e:" + endIndex; 
        }
    }
    
    private static Node search(ArrayList<Node> arr, int index) {
        System.out.println("searching: " + index);
        int lo = 0;
        int hi = arr.size() - 1;
        while(lo<=hi) {
            int mid = lo + (hi - lo)/2;
            Node curr = arr.get(mid);
            if(index >= curr.startIndex  && index <= curr.endIndex ) return curr;
            else if(curr.startIndex > index) {hi = mid - 1;}
            else {lo = mid + 1;}
        }
        return null;
    }
    
    public static void main(String args[]) {
        ArrayList<Node> arr = new ArrayList();
        Node a = new Node(1,1,1);
        arr.add(a);
        Node b = new Node(2,2,4);
        arr.add(b);
        Node c = new Node(3,5,5);
        arr.add(c);
        
        for(int i=1;i<=5;i++) {
            System.out.println(search(arr, i));
        }
        
        System.out.println();
        System.out.println();
        
        int q = 2;
        int n = 5;
        for(int k=1;k<q;k++) {
            int index = n*k/q;
            System.out.println(search(arr, index));
        }
    }
}
