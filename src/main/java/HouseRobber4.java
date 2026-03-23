
public class HouseRobber4 {
  public int minCapability(int[] nums, int k) {
    int l = 0, r = 1_000_000_000;
    while (l < r) {
      int minCap = (l + r) / 2;
      if (seqExists(minCap, nums, k)) {
        r = minCap;
      } else {
        l = minCap + 1;
      }
    }
    return l;
  }

  private boolean seqExists(int minCap, int[] nums, int k) {
    int i = 0;
    int count = 0;
    while (i < nums.length && count < k) {
      if (nums[i] <= minCap) {
        i += 2;
        count++;
      } else {
        i += 1;
      }
    }
    return count >= k;
  }

}