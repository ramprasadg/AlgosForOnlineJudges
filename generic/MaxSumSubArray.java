package generic;

public class MaxSumSubArray {
    public static void main (String args[]) {
        int arr[] = {-10, 4, 8, 0, -10, 22, -7, 15, -30};
        //int arr[] = {-10, -12, -4 , -8};
        
        findMaxSumSubArray(arr);
    }

    private static void findMaxSumSubArray(int[] arr) {
        if(arr == null || arr.length == 0)
            System.out.println("Empty input");
        
        int sum = arr[0];
        int startIndex = 0;
        
        int maxSum = sum;
        int maxStartIndex = startIndex;
        int maxEndIndex = 0;
        
        for(int i=1; i< arr.length; i++) {
            if(sum < 0) {
                sum = arr[i];
                startIndex = i;
            } else {
                sum = sum + arr[i]; 
            }
            
            if(sum > maxSum) {
                maxSum = sum;
                maxStartIndex = startIndex;
                maxEndIndex = i;
            }
        }
        
        System.out.println(String.format("start=%d end=%d maxSum=%s", maxStartIndex, maxEndIndex, maxSum));
        
    }
}
