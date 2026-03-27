import java.util.Arrays;

public class CoinChange {
  public static void main(String[] args) {
    var r = new CoinChange().coinChange(new int[]{1, 2, 5}, 11);
    System.out.println(r);
  }

  public int coinChange(int[] coins, int amount) {
    int[] memo = new int[amount + 1];
    Arrays.fill(memo, -2);
    return minCoins(memo, amount, 0, coins);
  }

  private int minCoins(int[] memo, int remaining, int i, int[] coins) {
    if (remaining < 0) {
      return -1;
    }
    if (remaining == 0) {
      return 0;
    }
    if (memo[remaining] != -2) {
      return memo[remaining];
    }
    int min = Integer.MAX_VALUE;
    for (int j = 0; j < coins.length; ++j) {
      int res = minCoins(memo, remaining - coins[j], j, coins);
      if (res == -1) {
        continue;
      }
      min = Math.min(res + 1, min);
    }
    memo[remaining] = min == Integer.MAX_VALUE ? -1 : min;
    return memo[remaining];
  }
}
