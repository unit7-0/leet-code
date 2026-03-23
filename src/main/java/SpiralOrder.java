import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
  public static void main(String[] args) {
    var r  = new SpiralOrder().spiralOrder(new int[][] {
        {7},
        {9},
        {6},
    });
    System.out.println(r);
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0;
    int right = n - 1;
    int top = 0;
    int bottom = m - 1;
    var result = new ArrayList<Integer>();

    while (left <= right && top <= bottom) {
      for (int j = left; j <= right; ++j) {
        result.add(matrix[top][j]);
      }
      for (int i = top + 1; i <= bottom; ++i) {
          result.add(matrix[i][right]);
      }
      if (top < bottom) {
        for (int j = right - 1; j >= left; --j) {
          result.add(matrix[bottom][j]);
        }
      }
      if (left < right) {
        for (int i = bottom - 1; i > top; --i) {
          result.add(matrix[i][left]);
        }
      }
      left++;
      top++;
      right--;
      bottom--;
    }

    return result;
  }
}
