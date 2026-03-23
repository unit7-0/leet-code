public class EditDistance {
  public static void main(String[] args) {
    System.out.println(
    new EditDistance().minDistance("horse", "ros")
    );
  }

  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; ++i) {
      dp[i][0] = i;
    }
    for (int i = 1; i <= n; ++i) {
      dp[0][i] = i;
    }

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
        }
        print(word1, word2, i, j, dp);
        System.out.println();
      }
    }
    return dp[m][n];
  }

  private void print(String word1, String word2, int i, int j, int[][] dp) {
    System.out.println("Prefixes:");
    System.out.println(word1.substring(0, i) + " -> " + word2.substring(0, j));
    for (int i1 = 0; i1 < dp.length; ++i1) {
      System.out.print("[");
      for (int j1 = 0; j1 < dp[i1].length; j1++) {
        System.out.print(dp[i1][j1]);
        System.out.print(",");
      }
      System.out.println("]");
    }
  }
}
