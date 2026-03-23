public class HouseRobber2 {
  public static void main(String[] args) {
    System.out.println(
        new HouseRobber2().rob(new int[] {1,2,3,1})
    );
  }

    public int rob(int[] nums) {
      return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public int rob(int[] nums, int start, int end) {
      int n = nums.length;
      int max1 = 0;
      int max2 = 0;
      if (start >= n || end < 0) {
        return nums[0];
      }

      for (int i = start; i <= end; ++i) {
        int tmp = max2;
        max2 = Math.max(max1 + nums[i], max2);
        max1 = tmp;
      }
      return max2;
    }
  }
