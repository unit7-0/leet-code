import java.util.*;

public class CombinationSum2 {
  public static void main(String[] args) {
    CombinationSum2 s = new CombinationSum2();
    System.out.println(s.combinationSum2(
        new int[]{
            2, 5, 2, 1, 2
        }, 5
    ));
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    var result = new ArrayList<List<Integer>>();
    combinationSum2(candidates, 0, target, result, new ArrayList<>());
    return result;
  }

  private void combinationSum2(int[] candidates, int index, int target, List<List<Integer>> result, List<Integer> combination) {
    if (target == 0) {
      result.add(List.copyOf(combination));
      return;
    }
    for (int i = index; i < candidates.length; ++i) {
      if (i > index && candidates[i - 1] == candidates[i]) {
        continue;
      }
      if (target < candidates[i]) {
        break;
      }
      combination.add(candidates[i]);
      combinationSum2(candidates, i + 1, target - candidates[i], result, combination);
      combination.removeLast();
    }
  }
}
