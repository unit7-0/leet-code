import java.util.*;

class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    solve(result, nums);
    return result;
  }

  private void solve(List<List<Integer>> result, int[] nums) {
    int n = nums.length;
    for (int i = 0; i < (1 << n); ++i) {
      var subset = new ArrayList<Integer>();
      for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) != 0) {
          subset.add(nums[j]);
        }
      }
      result.add(subset);
    }
  }
}