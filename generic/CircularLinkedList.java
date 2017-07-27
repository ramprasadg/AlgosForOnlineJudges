package generic;

public class CircularLinkedList {

    static class CNode {
        CNode next;
        int value;
        
        CNode(int value) {
            this.value = value;
            next = this;
        }
    }

    static CNode insert(CNode start, int value) {
        CNode newNode = new CNode(value);
        
        if(start != null) {
            CNode largestNode = start;
            while(largestNode.next.value > largestNode.value) {
                largestNode = largestNode.next;
            }
            
            CNode smallestNode = largestNode.next;
            
            if(newNode.value < smallestNode.value || newNode.value > largestNode.value) {
                largestNode.next = newNode;
                newNode.next = smallestNode;
            } else {
                CNode prev = smallestNode;
                CNode next = smallestNode.next;
                
                while(next.value < value) {
                    prev = next;
                    next = next.next;
                }
                
                prev.next = newNode;
                newNode.next = next;
            }
            
        }
        
        return newNode;
    }

    static void print(CNode start) {
        CNode startBck = start;
        while (start != null) {
            System.out.print(start.value + " -> ");
            start = start.next;
            if (start == startBck) {
                System.out.println("^");
                break;
            }
        }
    }

    public static void main(String[] args) {
        CNode start = insert(null, 3);
        start = insert(start, 2);
        start = insert(start, 1);
        start = insert(start, 5);
        start = insert(start, 4);
        start = insert(start, 0);
        print(start);
    }

}
