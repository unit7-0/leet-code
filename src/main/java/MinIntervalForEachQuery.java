import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinIntervalForEachQuery {
  // NI = sizeOf(intervals)
  // NQ = sizeOf(queries)

  // time complexity = O(NQ * LOG NQ + NI * LOG NI + (NI + NQ) * LOG NI))
  // memory complexity = O(NQ + NI)

  public int[] minInterval(int[][] intervals, int[] queries) {
    int n = intervals.length;
    int nq = queries.length;
    int[][] sortedQueries = new int[nq][2];
    int[] answer = new int[nq];

    for (int i = 0; i < nq; ++i) {
      sortedQueries[i][0] = queries[i];
      sortedQueries[i][1] = i;
    }

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

    var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1] - a[0]));
    int intervalsIdx = 0;
    for (int i = 0; i < nq; ++i) {
      int query = sortedQueries[i][0];
      int qIdx = sortedQueries[i][1];
      while (intervalsIdx < n && intervals[intervalsIdx][0] <= query) {
        pq.offer(intervals[intervalsIdx++]);
      }
      while (!pq.isEmpty() && pq.peek()[1] < query) {
        pq.poll();
      }
      if (pq.isEmpty()) {
        answer[qIdx] = -1;
      } else {
        answer[qIdx] = pq.peek()[1] - pq.peek()[0] + 1;
      }
    }

    return answer;
  }
}
