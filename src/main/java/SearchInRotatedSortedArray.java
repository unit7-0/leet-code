public class SearchInRotatedSortedArray {
  public static void main(String[] args) {
    new SearchInRotatedSortedArray().search(
        new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0
    );
  }

  public int search(int[] nums, int target) {
    int l = 0, r = nums.length;
    while (l < r) {
      int mid = (l + r) / 2;
      if (nums[mid] == target) {
        return mid;
      }

      if (nums[l] <= nums[mid]) { // left half sorted
        if (nums[l] <= target && target < nums[mid]) {
          r = mid;
        } else {
          l = mid + 1;
        }
      } else { // right half sorted
        if (nums[mid] < target && target <= nums[r - 1]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
    }
    return -1;
  }
}