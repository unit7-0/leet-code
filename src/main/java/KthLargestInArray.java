public class KthLargestInArray {
  public static void main(String[] args) {
    System.out.println(new KthLargestInArray().findKthLargestQuickSelect(new int[]{1, 2, 3, 4, 5}, 4));
  }

  public int findKthLargest(int[] nums, int k) {
    int[] buckets = new int[20001];
    for (int i : nums) {
      int v = i + 10000;
      buckets[v]++;
    }
    for (int i = buckets.length - 1; i >= 0; --i) {
      if (buckets[i] > 0) {
        k -= buckets[i];
      }
      if (k <= 0) {
        return i - 10000;
      }
    }
    return Integer.MIN_VALUE;
  }

  private int findKthLargestQuickSelect(int[] nums, int k) {
    return findKthLargestQuickSelect0(nums, nums.length - k);
  }

  private int findKthLargestQuickSelect0(int[] nums, int k) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
      int pivotIdx = partition(l, r, nums);
      if (k == pivotIdx) {
        return nums[pivotIdx];
      } else if (k < pivotIdx) {
        r = pivotIdx - 1;
      } else {
        l = pivotIdx + 1;
      }
    }
    return nums[l];
  }

  private int partition(int left, int right, int[] nums) {
    int pivot = nums[right];
    int pivotLoc = left;
    for (int i = left; i < right; ++i) {
      if (nums[i] < pivot) {
        swap(nums, i, pivotLoc);
        pivotLoc++;
      }
    }
    swap(nums, pivotLoc, right);
    return pivotLoc;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
