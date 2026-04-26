import java.util.Arrays;

public class CoinChange2 {
  public static void main(String[] args) {
    System.out.println(new CoinChange2().change(105, new int[] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14 }));
  }
  int[][] memo;

  public int change(int amount, int[] coins) {
    memo = new int[coins.length + 1][amount + 1];
    for (var a : memo) {
      Arrays.fill(a, -1);
    }
    return dfs(amount, coins.length - 1, coins);
  }

  private int dfs(int amount, int i, int[] coins) {
    if (i < 0 || amount < 0) {
      return 0;
    }
    if (amount == 0) {
      return 1;
    }
    if (memo[i][amount] != -1) {
      return memo[i][amount];
    }
    int result = dfs(amount, i - 1, coins) + dfs(amount - coins[i], i, coins);
    memo[i][amount] = result;
    return result;
  }
}
