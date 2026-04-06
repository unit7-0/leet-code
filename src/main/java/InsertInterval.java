import java.util.ArrayList;

public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    int i = 0;
    int n = intervals.length;
    var result = new ArrayList<int[]>();
    int start = newInterval[0];
    int end = newInterval[1];
    while (i < n && intervals[i][1] < start) {
      result.add(intervals[i++]);
    }
    while (i < n && intervals[i][0] <= end) {
      start = Math.min(start, intervals[i][0]);
      end = Math.max(end, intervals[i][1]);
      ++i;
    }
    result.add(new int[]{start, end});
    while (i < n) {
      result.add(intervals[i++]);
    }
    return result.toArray(new int[result.size()][]);
  }
}
