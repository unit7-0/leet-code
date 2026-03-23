public class MaxProductSubArray {

  public static void main(String[] args) {
    System.out.println(
        new MaxProductSubArray()
            .maxProduct(new int[] {2,-5,-2,-4,3})
    );
  }

  public int maxProduct(int[] nums) {
    // [2,3,-2,4,-1]
    // 2,-6,-2,4,-1
    // 2,6,-8,-32,32
    //  0,1, 2,3, 4
    // [2,3,-2,4,-1]
    //  2,6, 6,

    // -5,2,-2,-4,8
    // 2,-10,20,-80,-240
    // dp[i]
    return maxProduct(nums, 1, nums[0]);
  }

  private int maxProduct(int[] nums, int i, int product) {
    if (i >= nums.length) {
      return product;
    }
    int updated = product * nums[i];
    int p1 = maxProduct(nums, i + 1, updated);
    int p2 = maxProduct(nums, i + 1, nums[i]);
    return Math.max(product, Math.max(p1, p2));
  }
}