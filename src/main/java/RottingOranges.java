import java.util.LinkedList;

public class RottingOranges {
  public static void main(String[] args) {
    RottingOranges ro = new RottingOranges();
    System.out.println(ro.orangesRotting(
        new int[][]{
            {2, 1, 1}, {0, 1, 1}, {1, 0, 1}
        }
    ));
  }

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int fresh = 0;
    var q = new LinkedList<int[]>();
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (grid[i][j] == 2) {
          q.offer(new int[]{i, j});
        }
        if (grid[i][j] == 1) {
          fresh++;
        }
      }
    }
    if (fresh == 0) {
      return 0;
    }

    int minutes = -1;
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    while (!q.isEmpty()) {
      ++minutes;
      int size = q.size();
      for (int k = 0; k < size; ++k) {
        int[] rotten = q.poll();
        for (int[] dir : directions) {
          int i = rotten[0] + dir[0];
          int j = rotten[1] + dir[1];
          if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) {
            continue;
          }
          grid[i][j] = 2;
          q.offer(new int[]{i, j});
          --fresh;
        }
      }
    }

    return fresh == 0 ? minutes : -1;
  }

}
