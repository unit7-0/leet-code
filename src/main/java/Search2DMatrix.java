public class Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    int top = 0, bottom = matrix.length - 1;
    while (top < bottom) {
      int mid = (top + bottom) / 2;
      if (matrix[mid][0] < target) {
        top = mid + 1;
      } else {
        bottom = mid;
      }
    }
    int row = top;
    if (matrix[top][0] > target && row > 0) {
      row--;
    }
    int l = 0, r = matrix[0].length;
    while (l < r) {
      int mid = (l + r) / 2;
      if (matrix[row][mid] < target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return l < matrix[0].length && matrix[row][l] == target;
  }
}
