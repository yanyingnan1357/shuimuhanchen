package algorithm.dp;

/**
 * 连续子数组的最大和
 * dp[i]表示以数组下标为i的数做为结尾的最大子序列和（必须以i为结尾）
 */
public class MaxSum {

    public static Integer maxSum (int[] arr) {
        //初始化dp[i]
        int[] dp = new int[arr.length];
        int result = arr[0];

        for (int i=1; i<arr.length; i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
            result = Math.max(dp[i], result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,-2,3,10,-4,7,2,-5};
        System.out.println(maxSum(arr));
    }
}
