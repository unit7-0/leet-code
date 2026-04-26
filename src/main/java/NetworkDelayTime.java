import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

  public static void main(String[] args) {
    NetworkDelayTime nd = new NetworkDelayTime();
    System.out.println(nd.networkDelayTime(
        new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1
    ));
  }

  public int networkDelayTime(int[][] times, int n, int k) {
    int inf = Integer.MAX_VALUE;
    int[] dist = new int[n + 1];
    List<int[]>[] edges = new List[n + 1];
    var q = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));

    for (int i = 0; i < n; ++i) {
      dist[i + 1] = inf;
      edges[i + 1] = new ArrayList<>();
    }

    for (int i = 0; i < times.length; ++i) {
      int u = times[i][0];
      edges[u].add(new int[]{times[i][1], times[i][2]});
    }
    q.offer(new int[]{k, 0});
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int curNode = curr[0];
      int time = curr[1];
      if (dist[curNode] <= time) {
        continue;
      }
      dist[curNode] = time;
      for (int[] edge : edges[curNode]) {
        int nextTime = time + edge[1];
        if (dist[edge[0]] == inf || dist[edge[0]] > nextTime) {
          q.offer(new int[]{edge[0], nextTime});
        }
      }
    }

    int res = 0;
    for (int i = 1; i <= n; ++i) {
      res = Math.max(res, dist[i]);
      if (dist[i] == inf) {
        return -1;
      }
    }

    return res;
  }
}
