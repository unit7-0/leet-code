public class NextPermutation {
  public void nextPermutation(int[] nums) {
    int n = nums.length;
    int insertionPoint = -1;
    for (int i = n - 1; i > 0; --i) {
      int t = nums[i - 1];
      int c = nums[i];
      if (t < c) {
        insertionPoint = i - 1;
        break;
      }
    }
    if (insertionPoint == -1) {
      reverse(nums, 0);
    } else {
      int minMaxIdx = insertionPoint + 1;
      for (int i = insertionPoint + 2; i < n; ++i) {
        if (nums[i] > nums[insertionPoint] && nums[i] <= nums[minMaxIdx]) {
          minMaxIdx = i;
        }
      }
      swap(nums, insertionPoint, minMaxIdx);
      reverse(nums, insertionPoint + 1);
    }
  }

  private void reverse(int[] nums, int start) {
    int n = nums.length - start;
    for (int i = start; (i - start) < n / 2; ++i) {
      swap(nums, i, nums.length - (i - start) - 1);
    }
  }

  private void swap(int[] nums, int i1, int i2) {
    int t = nums[i1];
    nums[i1] = nums[i2];
    nums[i2] = t;
  }
}