import java.util.ArrayDeque;

public class LargestRectangleInHistogram {
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] left = new int[n];
    int[] right = new int[n];
    var stack = new ArrayDeque<Integer>();
    for (int i = 0; i < n; ++i) {
      int h = heights[i];
      while (!stack.isEmpty() && heights[stack.peekFirst()] >= h) {
        stack.removeFirst();
      }
      left[i] = stack.isEmpty() ? -1 : stack.peekFirst();
      stack.addFirst(i);
    }
    stack.clear();
    for (int i = n - 1; i >= 0; --i) {
      int h = heights[i];
      while (!stack.isEmpty() && heights[stack.peekFirst()] >= h) {
        stack.removeFirst();
      }
      right[i] = stack.isEmpty() ? n : stack.peekFirst();
      stack.addFirst(i);
    }
    int maxArea = 0;
    for (int i = 0; i < n; ++i) {
      int l = left[i];
      int r = right[i];
      int h = heights[i];
      int length = r - l - 1;
      int area = h * length;
      maxArea = Math.max(maxArea, area);
    }
    return maxArea;
  }
}
