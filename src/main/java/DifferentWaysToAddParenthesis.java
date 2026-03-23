import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParenthesis {
  List<Integer>[][] memo;

  public static void main(String[] args) {
    System.out.println(
        new DifferentWaysToAddParenthesis().diffWaysToCompute("2*3-4*5")
    );
  }

  public List<Integer> diffWaysToCompute(String expression) {
    int n = expression.length();
    memo = new ArrayList[n][n];
    return split(0, expression.length() - 1, expression);
  }

  private List<Integer> split(int i, int j, String expression) {
    if (memo[i][j] != null) {
      return memo[i][j];
    }
    var result = new ArrayList<Integer>();
    for (int k = i; k <= j; ++k) {
      if (isOperator(k, expression)) {
        var left = split(i, k - 1, expression);
        var right = split(k + 1, j, expression);
        for (int l : left) {
          for (int r : right) {
            var res = calc(expression.charAt(k), l, r);
            result.add(res);
          }
        }
      }
    }
    if (result.isEmpty()) {
      result.add(Integer.parseInt(expression.substring(i, j + 1)));
    }
    memo[i][j] = result;
    return result;
  }

  private boolean isOperator(int i, String expression) {
    var c = expression.charAt(i);
    return c == '+' || c == '-' || c == '*';
  }

  private int calc(char op, int a, int b) {
    return switch (op) {
      case '+' -> a + b;
      case '-' -> a - b;
      case '*' -> a * b;
      default -> 0;
    };
  }
}
