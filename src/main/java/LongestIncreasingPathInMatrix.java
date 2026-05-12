public class LongestIncreasingPathInMatrix {
  public static void main(String[] args) {
    LongestIncreasingPathInMatrix l = new LongestIncreasingPathInMatrix();
    System.out.println(
        l.longestIncreasingPath(
            new int[][] {
                {9,9,4},{6,6,8},{2,1,1}
            }
        )
    );
  }

  public int longestIncreasingPath(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] dist = new int[m][n];
    int result = 0;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        result = Math.max(result, dfs(i, j, matrix, dist));
      }
    }
    return result;
  }

  private int dfs(int i, int j, int[][] matrix, int[][] dist) {
    int m = matrix.length;
    int n = matrix[0].length;
    if (dist[i][j] != 0) {
      return dist[i][j];
    }
    int x = matrix[i][j];
    int up = 0, down = 0, left = 0, right = 0;
    if (i > 0 && matrix[i - 1][j] > x) {
      up = dfs(i - 1, j, matrix, dist);
    }
    if (i + 1 < m && matrix[i + 1][j] > x) {
      down = dfs(i + 1, j, matrix, dist);
    }
    if (j > 0 && matrix[i][j - 1] > x) {
      left = dfs(i, j - 1, matrix, dist);
    }
    if (j + 1 < n && matrix[i][j + 1] > x) {
      right = dfs(i, j + 1, matrix, dist);
    }
    dist[i][j] = 1 + Math.max(up, Math.max(down, Math.max(left, right)));
    return dist[i][j];
  }
}
