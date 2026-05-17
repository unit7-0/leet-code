public class PartialSubsetSum {
  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int i : nums) {
      sum += i;
    }
    if (sum % 2 != 0) {
      return false;
    }
    int target = sum / 2;
    boolean[] prev = new boolean[target + 1];
    prev[0] = true;

    for (int i = 1; i <= nums.length; ++i) {
      boolean[] curr = new boolean[target + 1];
      for (int j = 1; j <= target; ++j) {
        if (nums[i - 1] <= j) {
          curr[j] = prev[j - nums[i - 1]] || prev[j];
        } else {
          curr[j] = prev[j];
        }
      }
      prev = curr;
    }

    return prev[target];
  }
}
