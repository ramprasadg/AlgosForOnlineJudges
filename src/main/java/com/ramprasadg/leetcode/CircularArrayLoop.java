package src.main.java.com.ramprasadg.leetcode;

//https://leetcode.com/problems/circular-array-loop/
public class CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (bfs(nums, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(int[] nums, int i) {
        if (nums[i] == 0) {
            return true;
        } else {
            int nextI = (i + nums[i]);
            while (nextI < 0) {
                nextI += nums.length;
            }
            nextI = nextI % nums.length;
            if (nextI == i) {
                return false;
            } else {
                nums[i] = 0;
                return bfs(nums, nextI);
            }
        }
    }
}