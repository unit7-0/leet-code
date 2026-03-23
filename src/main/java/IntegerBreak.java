public class IntegerBreak {
  public static void main(String[] args) {
    new IntegerBreak().integerBreak(10);
  }

    public int integerBreak(int n) {
      int[] dp = new int[n + 1];
      int result = 0;
      dp[1] = 1;
      for (int i = 1; i <= n; ++i) {
        for (int j = 1; j < i; ++j) {
          dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
          result = Math.max(result, dp[i]);
        }
      }
      return result;
  }
}
