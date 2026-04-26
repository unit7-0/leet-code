import java.util.Arrays;
import java.util.Comparator;

public class MinArrowsShots {
  public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
    int count = 0;
    int lastEnd = points[0][1];
    for (int i = 0; i < points.length; ++i) {
      var point = points[i];
      if (lastEnd >= point[0]) {
        lastEnd = Math.min(lastEnd, point[1]);
      } else {
        count++;
        lastEnd = point[1];
      }
    }
    return count + 1;
  }
}
