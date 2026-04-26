import java.util.Arrays;

public class RedundantConnection {

  public static void main(String[] args) {
    DSU dsu = new DSU();
    System.out.println(Arrays.toString(dsu.findRedundantConnection(new int[][]{
        {1,4},{3,4},{1,2},{4,5}
    })));
  }

  private static class DSU {
    public int[] findRedundantConnection(int[][] edges) {
      var union = new Union(edges.length + 1);
      for (var edge : edges) {
        if (!union.add(edge[0], edge[1])) {
          return edge;
        }
      }
      throw new IllegalStateException();
    }

    private static class Union {
      private final int[] parent;
      private final int[] rank;

      public Union(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
          parent[i] = i;
        }
      }

      int find(int x) {
        int p = parent[x];
        if (p == x) {
          return p;
        }
        int pp = find(p);
        parent[x] = pp;
        return pp;
      }

      boolean add(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) {
          return false;
        }
        if (rank[px] > rank[py]) {
          parent[py] = px;
        } else if (rank[py] > rank[px]) {
          parent[px] = py;
        } else {
          parent[px] = py;
          rank[py]++;
        }
        return true;
      }
    }
  }

  private static class DFS {
    public int[] findRedundantConnection(int[][] edges) {
      int n = edges.length;
      boolean[][] graph = new boolean[n + 1][n + 1];
      boolean[] visited = new boolean[n + 1];
      for (int[] edge : edges) {
        graph[edge[0]][edge[1]] = true;
        graph[edge[1]][edge[0]] = true;
        if (dfs(-1, edge[0], graph, visited)) {
          return edge;
        }
        Arrays.fill(visited, false);
      }
      throw new IllegalStateException();
    }

    private boolean dfs(int source, int v, boolean[][] graph, boolean[] visited) {
      if (visited[v]) {
        return true;
      }
      visited[v] = true;
      for (int j = 0; j < graph[v].length; ++j) {
        if (graph[v][j] && j != source) {
          if (dfs(v, j, graph, visited)) {
            return true;
          }
        }
      }
      return false;
    }
  }
}
