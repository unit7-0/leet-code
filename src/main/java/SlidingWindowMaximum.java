import java.util.LinkedList;

public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    var deque = new LinkedList<int[]>();
    int w = 0;
    int[] result = new int[nums.length - k + 1];
    for (int i = 0; i < nums.length; ++i) {
      while (!deque.isEmpty() && deque.getFirst()[0] <= nums[i]) {
        deque.removeFirst();
      }
      int lastIndexToKeep = i - k + 1;
      while (!deque.isEmpty() && deque.getLast()[1] < lastIndexToKeep) {
        deque.removeLast();
      }
      deque.addFirst(new int[]{nums[i], i});
      if (i + 1 >= k) {
        result[w++] = deque.getLast()[0];
      }
    }
    return result;
  }
}
