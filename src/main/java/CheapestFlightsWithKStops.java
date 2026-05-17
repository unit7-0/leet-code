import java.util.*;

public class CheapestFlightsWithKStops {
  public static void main(String[] args) {
    CheapestFlightsWithKStops cheapestFlightsWithKStops = new CheapestFlightsWithKStops();
    System.out.println(cheapestFlightsWithKStops.findCheapestPrice(
        3,
        new int[][]{
            {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
        },
        0,
        2,
        0
    ));
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    var graph = new HashMap<Integer, List<int[]>>();
    for (int[] flight : flights) {
      graph.computeIfAbsent(flight[0], __ -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
    }
    int[][] cost = new int[n][k + 2];
    for (int[] ints : cost) {
      Arrays.fill(ints, Integer.MAX_VALUE);
    }
    cost[src][0] = 0;
    var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[2]));
    pq.offer(new int[]{src, 0, 0});
    while (!pq.isEmpty()) {
      int[] edge = pq.poll();
      int v = edge[0];
      int stops = edge[1];
      int totalPrice = edge[2];
      if (v == dst) {
        return totalPrice;
      }
      if (stops > k) {
        continue;
      }
      for (int[] next : graph.getOrDefault(v, List.of())) {
        int u = next[0];
        int nextPrice = next[1];
        int newCost = totalPrice + nextPrice;
        if (cost[u][stops + 1] > newCost) {
          cost[u][stops + 1] = newCost;
          pq.offer(new int[]{u, stops + 1, newCost});
        }
      }
    }
    return -1;
  }
}
