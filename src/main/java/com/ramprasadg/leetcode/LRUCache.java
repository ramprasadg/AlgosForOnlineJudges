package com.ramprasadg.leetcode;

import java.util.HashMap;

public class LRUCache {
    
    int capacity;
    
    ListNode head = null;
    ListNode tail = null;
    
    private class ListNode {
        public int key;
        public int value;
        public ListNode previous = null;
        public ListNode next = null;
    }
    
    HashMap<Integer, ListNode> cache = new HashMap<Integer, ListNode>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        println("get(" + key + ")");
        if(! cache.containsKey(key)) {
            return -1;
        } else {
            ListNode listNode = cache.get(key);
            placeAtHead(listNode);
            return listNode.value;
        }
    }
    
    public void set(int key, int value) {
        println("set(" + key + ", " + value + ")");
        ListNode listNode;
        if(! cache.containsKey(key)) {
            listNode = new ListNode();
        } else {
            listNode = cache.get(key);
        }
        listNode.key = key;
        listNode.value = value;
        placeAtHead(listNode);
    }
    
    private void println(String s) {
        //System.out.println(s);
    }
    
    private void placeAtHead(ListNode node) {
        println("placing at head key:" + node.key);
        cache.put(node.key, node);
        
        if(head == null) {
            head = node;
            tail = node;
        } 
        if(head == node) {
            return;
        }
        else {
            if(node == tail) {
                tail = node.previous;
                tail.next = null;
            }
            
            //prevNode -> node -> nextNode
            ListNode prevNode = node.previous;
            ListNode nextNode = node.next;
            
            if(prevNode != null) {
                prevNode.next = nextNode;
            }
            
            if(nextNode != null) {
                nextNode.previous = prevNode;
            }
            
            node.next = head;
            head.previous = node;
            head = node;
        }
        
        if(cache.size() > capacity && tail.previous != null) {
            println("deleting key:" + tail.key);
            cache.remove(tail.key);
            ListNode newTail = tail.previous;
            newTail.next = null;
            tail = newTail;
        }
    }
}