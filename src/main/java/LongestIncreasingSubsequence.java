import java.util.*;

public class LongestIncreasingSubsequence {

  public int lengthOfLIS(int[] nums) {
    return binarySearch(nums);
  }

  private static int binarySearch(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int n : nums) {
      int i = 0, j = size;
      while (i < j) {
        int m = (i + j) / 2;
        if (n < tails[m]) {
          i = m + 1;
        } else {
          j = m;
        }
      }
      tails[i] = n;
      if (i == size) {
        size++;
      }
    }
    return size;
  }

  private static int dpSolution(int[] nums) {
    int count = 1;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < nums.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          count = Math.max(count, dp[i]);
        }
      }
    }
    return count;
  }
}
