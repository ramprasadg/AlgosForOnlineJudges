package src.main.java.com.ramprasadg.hackerrank;

/*
 * HACKERRANK INTERVIEW QUESTION
 * 
 * You are given a list of size N, initialized with zeroes. You have to perform M queries on the list and output the
 * maximum of final values of all the N elements in the list. For every query, you are given three integers a, b and k and
 * you have to add value k to all the elements ranging from index a to b(both inclusive).
 * 
 * Input Format
 * First line will contain two integers N and M separated by a single space.
 * Next M lines will contain three integers a, b and k separated by a single space.
 * Numbers in list are numbered from 1 to N.
 * 
 * Output Format
 * A single line containing maximum value in the final list.
 */

//Algo explained in https://www.youtube.com/watch?v=xuoQdt5pHj0

public class ListMaxSegmentTree {
    public static class Operation {
        int start;
        int end;
        int k;

        public Operation(int start, int end, int k) {
            this.start = start;
            this.end = end;
            this.k = k;
        }
    }

    int[] tree;
    int[] lazyTree;
    int n;

    public ListMaxSegmentTree(int n) {
        int size = nextPowerOf2(n);

        this.n = n;
        tree = new int[size];
        lazyTree = new int[size];
    }

    private int nextPowerOf2(int n) {
        int i = 1;
        while (i <= n)
            i *= 2;
        return i;
    }

    void performOperation(Operation o) {
        performOperation(o, 0, n - 1, 0);
    }

    int leftChild(int pos) {
        return 2 * pos + 1;
    }

    int rightChild(int pos) {
        return 2 * pos + 2;
    }

    private int performOperation(Operation o, int start, int end, int pos) {
        if (end < o.start)
            return Integer.MIN_VALUE;
        else if (o.end < start)
            return Integer.MIN_VALUE;;
        System.err.println(
                "Start:" + start + " End:" + end + " Pos:" + pos + " Op.start:" + o.start + " Op.end:" + o.end);

        if (pos >= tree.length) {
            return 0;
        }

        if (start == end) {
            tree[pos] = tree[pos] + o.k + lazyTree[pos];
            lazyTree[pos] = 0;
            printMax("Setting: " + start);
        } else {
            if (o.start <= start && end <= o.end) {
                lazyTree[pos] += o.k;
                tree[pos] = tree[pos] + lazyTree[pos];

                lazyTree[leftChild(pos)] = lazyTree[leftChild(pos)] + lazyTree[pos];
                lazyTree[rightChild(pos)] = lazyTree[rightChild(pos)] + lazyTree[pos];
                lazyTree[pos] = 0;

                printMax("Lazy Update");
            } else {
                int mid = (start + end) / 2;

                lazyTree[leftChild(pos)] = lazyTree[leftChild(pos)] + lazyTree[pos];
                lazyTree[rightChild(pos)] = lazyTree[rightChild(pos)] + lazyTree[pos];
                lazyTree[pos] = 0;

                int leftMax = performOperation(o, start, mid, leftChild(pos));
                int rightMax = performOperation(o, mid + 1, end, rightChild(pos));
                tree[pos] = Math.max(leftMax, rightMax);
                printMax("Updating Parent");
            }
        }

        return tree[pos];
    }

    public static void main(String args[]) {
        int n = 8;

        ListMaxSegmentTree tree = new ListMaxSegmentTree(n);
        tree.performOperation(new Operation(0, 5, 3));
        tree.performOperation(new Operation(0, 1, 1));
        tree.performOperation(new Operation(1, 1, 1));
        tree.performOperation(new Operation(2, 2, 2));
        tree.performOperation(new Operation(3, 3, 3));
    }

    private void printMax(String message) {
        System.err.println(message);
        for (int i : tree)
            System.err.print(i + ", ");

        System.err.println();

        for (int i : lazyTree)
            System.err.print(i + ", ");

        System.err.println();
        
        System.err.print(tree[0]);
    }
}
