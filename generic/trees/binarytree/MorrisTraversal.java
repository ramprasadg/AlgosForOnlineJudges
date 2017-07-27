package generic.trees.binarytree;

import org.junit.Test;

public class MorrisTraversal {
    
    private static void traverse(Node root) {
        if(root == null) {
            return;
        }
        
        if(root.left == null) {
            System.out.print(root + " ");
            traverse(root.right);
        } else {
            Node predecessor = findRightMost(root.left);
            predecessor.right = root;
            Node left = root.left;
            root.left = null;
            traverse(left);
        }
    }
    
    private static Node findRightMost(Node node) {
        if(node.right == null)
            return node;
        else
            return findRightMost(node.right);
    }

    @Test
    public void test1() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.right.left = new Node(12);
        
        MorrisTraversal.traverse(root);
    }
}
