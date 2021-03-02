package algorithm.dp;

/**
 * 二维数组左上角到右下角的最短路径和
 */
public class MinPathSum {

    public static Integer minPathSum (int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];

        for (int i=0; i<arr[0].length; i++) {
            for (int j=0; j<arr.length; j++) {
                if (i==0 && j==0) {
                    dp[i][j] = arr[i][j];
                } else if (i==0) {
                    dp[i][j] = arr[i][j] + dp[i][j-1];
                } else if (j==0) {
                    dp[i][j] = arr[i][j] + dp[i-1][j];
                }else {
                    dp[i][j] = arr[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[arr.length-1][arr[0].length-1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        System.out.println(minPathSum(arr));
    }
}
