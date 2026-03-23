import java.util.*;

class SubsetsBacktracking {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtracking(result, new ArrayList<>(), 0, nums);
    return result;
  }

  private void backtracking(List<List<Integer>> result, List<Integer> subset, int start, int[] nums) {
    result.add(new ArrayList<>(subset));
    for (int i = start; i < nums.length; ++i) {
      int n = nums[i];
      subset.add(n);
      backtracking(result, subset, i + 1, nums);
      subset.removeLast();
    }
  }
}