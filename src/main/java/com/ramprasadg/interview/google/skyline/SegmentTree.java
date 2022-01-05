package src.main.java.com.ramprasadg.interview.google.skyline;

import java.util.*;

import org.junit.Test;

//https://discuss.leetcode.com/topic/60837/java-segment-tree
public class SegmentTree {
    int[] tree;
    int N;
    
    public List<int[]> getSkyline(int[][] buildings) {
        LinkedList<int[]> results = new LinkedList<int[]>();
        
        /*
         * we will store left point as (position, houseIndex, height)
         * right point as (position, houseIndex, 0)
         */
         
        ArrayList<int[]> points = new ArrayList<int[]>();
        
        for(int i=0; i<buildings.length; i++) {
            int[] building = buildings[i];
            int left = building[0];
            int right = building[1];
            int height = building[2];
            
            points.add(new int[] {i, left, height});
            points.add(new int[] {i, right, 0});
        }
        
        //Sort points.
        
        Collections.sort(points, new Comparator<int[]>() {
           public int compare(int[] a, int[] b) {
               return a[1] - b[1];
           }
           public boolean equals(Object obj) {
               return this==obj;
           }
        });
        
        N = 1;
        while(N <= buildings.length) {
            N *= 2;
        }
        
        tree = new int[2 * N - 1];
        
        for(int[] point: points) {
            int index = point[0];
            int pos = point[1];
            int height = point[2];
            
            int maxHeight = updateTree(index, height, 0);
            results.add(new int[]{pos, maxHeight});
            
            while(results.size() > 1) {
                int[] tail = results.get(results.size() - 1);
                int[] prevTail = results.get(results.size() - 2);
                
                if(tail[0] != prevTail[0] && tail[1] != prevTail[1]) {
                    break;
                }
                
                if(tail[0] == prevTail[0]) {
                    prevTail[1] = tail[1];
                }
                
                results.remove(results.size() - 1);
            }
        }
        
        return results;
    }
    
    int updateTree(int index, int height, int node) {
        if(node > N - 2) {
            return tree[node] = height;
        }
        
        if(index % 2 == 0) {
            return tree[node] = Math.max(updateTree(index/2, height, 2 * node + 1), tree[2 * node + 2]);
        } else {
            return tree[node] = Math.max(updateTree(index/2, height, 2 * node + 2), tree[2 * node + 1]);
        }
    }
    
    @Test
    public void test1() {
        getSkyline(SkyLineTest.test1);
    }
}
