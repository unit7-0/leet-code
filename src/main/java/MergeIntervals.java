import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

  public static void main(String[] args) {
    var res = new MergeIntervals().merge(
      new int[][] {
          new int[] {2,3},
          new int[] {4,5},
          new int[] {6,7},
          new int[] {8,9},
          new int[] {1,10},
      }
    );
  }

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, new Comparator<>() {
      public int compare(int[] a, int[] b) {
        return a[0] - b[0];
      }
    });
    var result = new ArrayList<int[]>();
    int[] prev = new int[2];
    for (int i = 0; i < intervals.length; ++i) {
      int j = i;
      prev[0] = intervals[i][0];
      prev[1] = intervals[i][1];
      for (; j < intervals.length; ++j) {
        if (j + 1 == intervals.length || prev[1] < intervals[j + 1][0]) {
          break;
        }
        prev[1] = Math.max(prev[1], intervals[j + 1][1]);
      }
      var r = new int[] { prev[0], prev[1] };
      i = j;
      result.add(r);
    }
    return (int[][]) result.toArray(new int[result.size()][]);
  }
}
