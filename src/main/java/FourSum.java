import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    int n = nums.length;
    Arrays.sort(nums);
    var result = new ArrayList<List<Integer>>();
    for (int i = 0; i < n - 3; ++i) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < n - 2; ++j) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        twoSum(nums, target, i, j, n, result);
      }
    }
    return result;
  }

  private static void twoSum(int[] nums, long target, int i, int j, int n, ArrayList<List<Integer>> result) {
    long target2 = target - nums[i] - nums[j];
    int left = j + 1;
    int right = n - 1;
    while (left < right) {
      long sum = nums[left] + nums[right];
      if (sum == target2) {
        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
        while (left < right && nums[left] == nums[left + 1]) {
          left++;
        }
        while (left < right && nums[right] == nums[right - 1]) {
          right--;
        }
        left++;
        right--;
      } else if (sum < target2) {
        left++;
      } else {
        right--;
      }
    }
  }
}
