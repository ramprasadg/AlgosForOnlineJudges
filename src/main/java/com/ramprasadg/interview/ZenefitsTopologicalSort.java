package src.main.java.com.ramprasadg.interview;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class ZenefitsTopologicalSort {

    static Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    static Set<Integer> allNodes = new HashSet<Integer>();
    static Set<Integer> visitedNodes = new HashSet<Integer>();
    static Stack<Integer> stack = new Stack<Integer>();

    static void topologicalSortUtil(int v) {
        visitedNodes.add(v);

        Iterator<Integer> it = graph.get(v).iterator();
        while (it.hasNext()) {
            int i = it.next();
            if (!visitedNodes.contains(i))
                topologicalSortUtil(i);
        }

        stack.push(v);
    }

    static int[] taskOrdering(int[][] dependency) {

        for (int i = 0; i < dependency.length; i++) {
            int a = dependency[i][0];
            int b = dependency[i][1];
            if (!graph.containsKey(a)) {
                graph.put(a, new LinkedList<Integer>());
            }
            if (!graph.containsKey(b)) {
                graph.put(b, new LinkedList<Integer>());
            }

            allNodes.add(a);
            allNodes.add(b);
            graph.get(a).add(b);
        }

        for (Integer node : allNodes)
            if (!visitedNodes.contains(node))
                topologicalSortUtil(node);

        int[] arr = new int[stack.size()];

        for (int i = 0; i < arr.length; i++)
            arr[i] = stack.pop();

        return arr;
    }

    @Test
    public void test1() {
        int[][] dependency = { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 3 }, { 3, 2 }, { 4, 2 }, { 4, 3 }, { 5, 4 } };

        int[] arr = { 5, 4, 3, 2, 1 };
        int[] output = taskOrdering(dependency);
        for (int i = 0; i < arr.length; i++) {
            assertEquals(output[i], arr[i]);
        }
    }
}
