import java.util.*;

public class ConnectableServersInWeighedTreeNetwork {
  public static void main(String[] args) {
    ConnectableServersInWeighedTreeNetwork cs = new ConnectableServersInWeighedTreeNetwork();
    System.out.println(Arrays.toString(cs.countPairsOfConnectableServers(
        new int[][]{
            {0, 1, 1}, {1, 2, 5}, {2, 3, 13}, {3, 4, 9}, {4, 5, 2}
        },
        1
    )));
  }

  public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
    var adjacency = new HashMap<Integer, List<Edge>>();
    for (var edge : edges) {
      adjacency.computeIfAbsent(edge[0], __ -> new ArrayList<>()).add(new Edge(edge[1], edge[2]));
      adjacency.computeIfAbsent(edge[1], __ -> new ArrayList<>()).add(new Edge(edge[0], edge[2]));
    }
    int n = edges.length + 1;
    int[] count = new int[n];
    for (int c = 0; c < n; ++c) {
      var connectedServers = adjacency.get(c);
      if (connectedServers == null) {
        continue;
      }
      int sum = 0;
      int prev = 0;
      for (Edge edge : connectedServers) {
        int a = dfs(adjacency, 0, c, edge, signalSpeed);
        sum += a * prev;
        prev += a;
      }
      count[c] = sum;
    }
    return count;
  }

  private int dfs(Map<Integer, List<Edge>> adjacency, int path, int parent, Edge edge, int signalSpeed) {
    int current = edge.to;
    var connectedServers = adjacency.get(current);
    int pathToCurrentServer = edge.weight + path;
    int currentServerIsConnected = pathToCurrentServer % signalSpeed == 0 ? 1 : 0;
    if (connectedServers == null) {
      return currentServerIsConnected;
    }
    int sum = currentServerIsConnected;
    for (Edge next : connectedServers) {
      if (next.to != parent) {
        sum += dfs(adjacency, pathToCurrentServer, current, next, signalSpeed);
      }
    }
    return sum;
  }

  private record Edge(int to, int weight) {
  }
}
