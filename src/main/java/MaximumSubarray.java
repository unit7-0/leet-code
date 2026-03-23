public class MaximumSubarray {
  public static void main(String[] args) {

    System.out.println(
    new MaximumSubarray().maxSubArray(new int[] {
        -2,1,-3,4,-1,2,1,-5,4
    }));
  }
  public int maxSubArray(int[] nums) {
    int n = nums.length;
    int max = nums[0];
    int prefix = 0;
    for (int i = 0; i < n; ++i) {
      if (nums[i] > prefix + nums[i]) {
        prefix = nums[i];
      } else {
        prefix += nums[i];
      }
      max = Math.max(prefix, max);
    }
    return max;
  }
}
