public class ContainerWithTheMostWater {
  public int maxArea(int[] height) {
    int n = height.length;
    int left = 0;
    int right = n - 1;
    int maxWater = 0;
    while (left < right) {
      int water = Math.min(height[left], height[right]) * (right - left);
      maxWater = Math.max(water, maxWater);
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return maxWater;
  }
}
