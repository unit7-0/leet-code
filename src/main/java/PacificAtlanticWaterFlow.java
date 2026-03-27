import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;
    boolean[][] atlantic = new boolean[m][n];
    boolean[][] pacific = new boolean[m][n];

    for (int i = 0; i < m; ++i) {
      dfs(i, 0, heights[i][0], heights, pacific);
    }
    for (int j = 0; j < n; ++j) {
      dfs(0, j, heights[0][j], heights, pacific);
    }
    for (int i = 0; i < m; ++i) {
      dfs(i, n - 1, heights[i][n - 1], heights, atlantic);
    }
    for (int j = 0; j < n; ++j) {
      dfs(m - 1, j, heights[m - 1][j], heights, atlantic);
    }

    var result = new ArrayList<List<Integer>>();
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(List.of(i, j));
        }
      }
    }
    return result;
  }

  private void dfs(int i, int j, int heightFrom, int[][] heights, boolean[][] visited) {
    if (i < 0 || j < 0 || i >= heights.length || j >= heights[i].length) {
      return;
    }
    if (visited[i][j]) {
      return;
    }
    if (heights[i][j] < heightFrom) {
      return;
    }
    visited[i][j] = true;
    int height = heights[i][j];
    dfs(i - 1, j, height, heights, visited);
    dfs(i, j - 1, height, heights, visited);
    dfs(i + 1, j, height, heights, visited);
    dfs(i, j + 1, height, heights, visited);
  }
}
