import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SwimInRisingWater {

  public static void main(String[] args) {
    var s = new PQueue();
    System.out.println(s.swimInWater(new int[][]{
        {3, 2}, {0, 1}}));
  }

  static class BFS {
    public int swimInWater(int[][] grid) {
      int l = 0, r = max(grid);
      while (l < r) {
        int t = (l + r) / 2;
        if (bfs(grid, t)) {
          r = t;
        } else {
          l = t + 1;
        }
      }
      return l;
    }

    private boolean bfs(int[][] grid, int t) {
      if (grid[0][0] > t) {
        return false;
      }
      int n = grid.length;
      var visited = new boolean[n][n];
      var q = new LinkedList<int[]>();
      q.addLast(new int[]{0, 0});
      var directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
      while (!q.isEmpty() && !visited[n - 1][n - 1]) {
        var v = q.pollFirst();
        int i = v[0];
        int j = v[1];
        if (visited[i][j]) {
          continue;
        }
        visited[i][j] = true;
        for (int[] dir : directions) {
          int ni = i + dir[0];
          int nj = j + dir[1];
          if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
            continue;
          }
          if (visited[ni][nj] || grid[ni][nj] > t) {
            continue;
          }
          q.addLast(new int[]{ni, nj});
        }
      }
      return visited[n - 1][n - 1];
    }

    private int max(int[][] grid) {
      int n = grid.length;
      int max = 0;
      for (int[] ints : grid) {
        for (int j = 0; j < n; ++j) {
          max = Math.max(max, ints[j]);
        }
      }
      return max;
    }
  }

  static class DFS {
    public int swimInWater(int[][] grid) {
      int n = grid.length;
      int l = 0, r = max(grid);
      var visited = new boolean[n][n];
      while (l < r) {
        int t = (l + r) / 2;
        if (dfs(0, 0, grid, visited, t)) {
          r = t;
        } else {
          l = t + 1;
        }
        for (int i = 0; i < n; ++i) {
          Arrays.fill(visited[i], false);
        }
      }
      return l;
    }

    private boolean dfs(int i, int j, int[][] grid, boolean[][] visited, int t) {
      int n = grid.length;
      if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] > t || visited[i][j]) {
        return false;
      }
      if (i == n - 1 && j == n - 1) {
        return true;
      }
      visited[i][j] = true;
      return dfs(i - 1, j, grid, visited, t) ||
             dfs(i + 1, j, grid, visited, t) ||
             dfs(i, j - 1, grid, visited, t) ||
             dfs(i, j + 1, grid, visited, t);
    }

    private int max(int[][] grid) {
      int n = grid.length;
      int max = 0;
      for (int[] ints : grid) {
        for (int j = 0; j < n; ++j) {
          max = Math.max(max, ints[j]);
        }
      }
      return max;
    }
  }

  static class PQueue {
    public int swimInWater(int[][] grid) {
      int n = grid.length;
      var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));
      var visited = new boolean[n][n];
      var directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
      int t = -1;
      int ans = 0;
      while (!pq.isEmpty()) {
        var v = pq.poll();
        t = v[0];
        int i = v[1];
        int j = v[2];
        ans = Math.max(ans, t);
        visited[i][j] = true;
        if (i == n - 1 && j == n - 1) {
          return ans;
        }
        for (int[] dir : directions) {
          int ni = i + dir[0];
          int nj = j + dir[1];
          if (ni < 0 || nj < 0 || ni >= n || nj >= n || visited[ni][nj]) {
            continue;
          }
          pq.offer(new int[]{grid[ni][nj], ni, nj});
        }
      }
      return Math.max(t, grid[n - 1][n - 1]);
    }

  }

  static class UnionSet {
    public int swimInWater(int[][] grid) {
      int n = grid.length;
      var dsu = new DSU(n * n);
      var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          pq.offer(new int[]{grid[i][j], i, j});
        }
      }

      int finalCell = convert(n - 1, n - 1, n);
      var directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
      int t = -1;
      while (!pq.isEmpty() && dsu.find(0) != dsu.find(finalCell)) {
        var v = pq.poll();
        t = v[0];
        int i = v[1];
        int j = v[2];
        for (int[] dir : directions) {
          int ni = i + dir[0];
          int nj = j + dir[1];
          if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
            continue;
          }
          if (grid[i][j] >= grid[ni][nj]) {
            dsu.union(convert(i, j, n), convert(ni, nj, n));
          }
        }
      }
      return Math.max(t, grid[n - 1][n - 1]);
    }

    private int convert(int i, int j, int n) {
      return i * n + j;
    }

    static class DSU {
      private final int[] parent;

      DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; ++i) {
          parent[i] = i;
        }
      }

      int find(int x) {
        int p = parent[x];
        if (x == p) {
          return p;
        }
        parent[x] = find(p);
        return parent[x];
      }

      void union(int x, int y) {
        int px = find(x), py = find(y);
        parent[py] = px;
      }
    }
  }
}
