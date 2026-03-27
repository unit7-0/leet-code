import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    var result = new ArrayList<List<Integer>>();
    backtracking(target, 0, candidates, result, new ArrayList<>());
    return result;
  }

  private void backtracking(int remaining, int i, int[] candidates, List<List<Integer>> result, List<Integer> combination) {
    if (remaining < 0) {
      return;
    }
    if (remaining == 0) {
      result.add(List.copyOf(combination));
      return;
    }
    for (int j = i; j < candidates.length; ++j) {
      combination.add(candidates[j]);
      backtracking(remaining - candidates[j], j, candidates, result, combination);
      combination.removeLast();
    }
  }
}
