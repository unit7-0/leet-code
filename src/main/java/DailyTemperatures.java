import java.util.Arrays;
import java.util.LinkedList;

public class DailyTemperatures {
  public static void main(String[] args) {
    DailyTemperatures d = new DailyTemperatures();
    System.out.println(Arrays.toString(d.dailyTemperatures(
        new int[]{
            73, 74, 75, 71, 69, 72, 76, 73
        }
    )));
  }

  public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    var s = new LinkedList<Integer>();
    int[] answer = new int[n];
    for (int i = 0; i < n; ++i) {
      int temp = temperatures[i];
      while (!s.isEmpty()) {
        int idx = s.peekFirst();
        int earlierTemp = temperatures[idx];
        if (temp > earlierTemp) {
          s.pollFirst();
          answer[idx] = i - idx;
        } else {
          break;
        }
      }
      s.offerFirst(i);
    }
    return answer;
  }
}
