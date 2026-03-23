import java.util.*;

public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    var result = new ArrayList<List<Integer>>();
    for (int i = 0; i < n - 2; ++i) {
      int n1 = nums[i];
      if (i > 0 && n1 == nums[i - 1]) {
        continue;
      }
      if (n1 > 0) {
        break;
      }
      int target = -n1;
      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int n2 = nums[j];
        int n3 = nums[k];
        int sum = n2 + n3;
        if (target == sum) {
          result.add(List.of(n1, n2, n3));
          do {
            j++;
          } while (j < k && nums[j] == nums[j - 1]);
          do {
            k--;
          } while (j < k && k < n - 1 && nums[k] == nums[k + 1]);
        } else if (target < sum) {
          k--;
        } else {
          j++;
        }
      }
    }
    return result;
  }
}
