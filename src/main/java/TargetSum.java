public class TargetSum {
  public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }

    if (Math.abs(target) > sum) {
      return 0;
    }

    int size = sum * 2 + 1;
    int offset = sum;
    int[] prev = new int[size];
    prev[offset] = 1;

    for (int i = 1; i <= nums.length; ++i) {
      int num = nums[i - 1];
      int[] curr = new int[size];
      for (int j = -sum; j <= sum; ++j) {
        int ways = prev[j + offset];
        if (ways != 0) {
          curr[j + offset + num] += ways;
          curr[j + offset - num] += ways;
        }
      }
      prev = curr;
    }
    return prev[target + offset];
  }
}