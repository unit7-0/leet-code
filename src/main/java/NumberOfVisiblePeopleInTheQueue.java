import java.util.ArrayDeque;

public class NumberOfVisiblePeopleInTheQueue {
  public int[] canSeePersonsCount(int[] heights) {
    int n = heights.length;
    var stack = new ArrayDeque<Integer>();
    int[] result = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      int h = heights[i];
      int c = 0;
      while (!stack.isEmpty() && stack.peekFirst() <= h) {
        c++;
        stack.pollFirst();
      }
      c = stack.isEmpty() ? c : (c + 1);
      stack.addFirst(h);
      result[i] = c;
    }
    return result;
  }
}
