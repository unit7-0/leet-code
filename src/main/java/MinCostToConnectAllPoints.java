import java.util.Arrays;
import java.util.Comparator;

public class MinCostToConnectAllPoints {

  public static void main(String[] args) {
    MinCostToConnectAllPoints c = new MinCostToConnectAllPoints();
    System.out.println(c.minCostConnectPoints(
        new int[][] {
            {0,0},{2,2},{3,10},{5,2},{7,0}
        }
    ));
  }

  public int minCostConnectPoints(int[][] points) {
    int n = points.length;
    int[][] q = new int[n * n][3];
    int idx = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        q[idx][0] = getDistance(points[i], points[j]);
        q[idx][1] = i;
        q[idx][2] = j;
        idx++;
      }
    }
    Arrays.sort(q, 0, idx, Comparator.comparingInt(a -> a[0]));
    int result = 0;
    var dsu = new DSU(n);
    int connected = 0;
    int i = 0;
    while (connected < n - 1) {
      var edge = q[i++];
      int dist = edge[0];
      int a = edge[1];
      int b = edge[2];
      if (dsu.find(a) != dsu.find(b)) {
        result += dist;
        dsu.union(a, b);
        connected++;
      }
    }
    return result;
  }

  private int getDistance(int[] a, int[] b) {
    return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
  }

  static class DSU {
    private final int[] parent;

    DSU(int n) {
      parent = new int[n];
      for (int i = 0; i < n; ++i) {
        parent[i] = i;
      }
    }

    int find(int v) {
      var p = parent[v];
      if (p == v) {
        return p;
      }
      parent[v] = find(p);
      return parent[v];
    }

    void union(int x, int y) {
      int px = find(x), py = find(y);
      parent[py] = px;
    }
  }
}
