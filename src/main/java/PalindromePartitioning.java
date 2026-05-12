import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePartitioning {

  public static void main(String[] args) {
    PalindromePartitioning pp = new PalindromePartitioning();
    System.out.println(pp.partition("aaba"));
  }

  private int[][] memo;

  public List<List<String>> partition(String s) {
    memo = new int[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++) {
      Arrays.fill(memo[i], -1);
    }
    var res = new ArrayList<List<String>>();
    backtracking(new ArrayList<>(), res, 0, s);
    return res;
  }

  private void backtracking(List<String> combination, List<List<String>> result, int i, String s) {
    if (i >= s.length()) {
      result.add(List.copyOf(combination));
      return;
    }
    for (int len = 1; i + len <= s.length(); ++len) {
      if (isPalindrome(i, i + len - 1, s)) {
        combination.add(s.substring(i, i + len));
        backtracking(combination, result, i + len, s);
        combination.removeLast();
      }
    }
  }

  private boolean isPalindrome(int left, int right, String s) {
    if (memo[left][right] != -1) {
      return memo[left][right] == 1;
    }
    while (left < right && s.charAt(left) == s.charAt(right)) {
      left++;
      right--;
    }
    memo[left][right] = left >= right ? 1 : 2;
    return left >= right;
  }
}
