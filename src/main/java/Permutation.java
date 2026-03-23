import java.util.*;

class Permutation {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.length;
    boolean[] used = new boolean[n];
    var permutation = new ArrayList<Integer>();
    backtracking(result, permutation, nums, used);
    return result;
  }

  private void backtracking(List<List<Integer>> result, List<Integer> permutation, int[] input, boolean[] used) {
    if (input.length == permutation.size()) {
      result.add(permutation);
      return;
    }
    for (int i = 0; i < input.length; ++i) {
      if (used[i]) {
        continue;
      }
      permutation.add(input[i]);
      used[i] = true;
      backtracking(result, new ArrayList<>(permutation), input, used);
      used[i] = false;
      permutation.removeLast();
    }
  }
}