import java.util.*;

class Permutations {
  public static void main(String[] args) {
    System.out.println(new Permutations().permute(new int[] {1, 2, 3}));
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    int n = nums.length;
    for (int i = 0; i < n; ++i) {
      var input = makeList(nums);
      var permutation = new ArrayList<Integer>();
      backtracking(result, permutation, input, i);
    }

    return result;
  }

  private List<Integer> makeList(int[] arr) {
    var list = new ArrayList<Integer>();
    for (int i = 0; i < arr.length; ++i) {
      list.add(arr[i]);
    }
    return list;
  }

  private void backtracking(List<List<Integer>> result, List<Integer> permutation, List<Integer> input, int pickedIdx) {
    permutation.add(input.get(pickedIdx));
    if (input.size() == 1) {
      result.add(new ArrayList<Integer>(permutation));
      return;
    }
    input.remove(pickedIdx);
    for (int i = 0; i < input.size(); ++i) {
      backtracking(result, new ArrayList<>(permutation), new ArrayList<>(input), i);
    }
  }
}