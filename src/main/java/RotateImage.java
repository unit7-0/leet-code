import java.util.Arrays;

public class RotateImage {

  public static void main(String[] args) {
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    new RotateImage().rotate(
        matrix
    );
    System.out.println(Arrays.toString(matrix));
  }

  public void rotate(int[][] matrix) {
    // [
    //     1,  2,  3,  4,  5
    //     6,  7,  8,  9,  10
    //     11, 12, 13, 14, 15
    //     16, 17, 18, 19, 20
    //     21, 22, 23, 24, 25
    // ]

    // [
    //     1,  2,  3,  4,
    //     6,  7,  8,  9,
    //     11, 12, 13, 14,
    //     16, 17, 18, 19
    // ]

    int n = matrix.length;
    int maxIdx = n - 1;
    for (int i = 0; i < n / 2; ++i) {
      for (int j = i; j < maxIdx - i; ++j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[maxIdx - j][i];
        matrix[maxIdx - j][i] = matrix[maxIdx - i][maxIdx - j];
        matrix[maxIdx - i][maxIdx - j] = matrix[j][maxIdx - i];
        matrix[j][maxIdx - i] = temp;
      }
    }
  }
}