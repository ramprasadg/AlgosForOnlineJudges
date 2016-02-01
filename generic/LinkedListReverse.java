package generic;

public class LinkedListReverse {
    static class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
        }
    }
    
    static Node reverse(Node currNode, Node prevNode) {
        if(currNode == null)
            return null;
        if(currNode.next == null) {
            currNode.next = prevNode;
            return currNode;
        }
        
        Node nextNode = currNode.next;
        currNode.next = prevNode;
        
        return reverse(nextNode, currNode);
    }
    
    static void print(Node head) {
        if(head == null) {
            System.out.println();
            return;
        }
        System.out.print(head.data + " ");
        print(head.next);
    }
    
    public static void main(String args[]) {
        Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);
        
        print(n);
        Node m = reverse(n, null);
        print(m);
    }
}
