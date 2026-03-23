import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class MinimumPathSum {
    private static final int EMPTY = Integer.MAX_VALUE;

  public static void main(String[] args) {
    System.out.println(
        ChronoUnit.DAYS.between(
        LocalDate.of(2025, Month.SEPTEMBER, 26),
        LocalDate.of(2026, Month.MARCH, 28)
        )
    );
  }

    public int minPathSum(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      var minCost = new int[m][n];
      for (int i = 0; i < m; ++i) {
        Arrays.fill(minCost[i], EMPTY);
      }
      return findMinCost(0, 0, minCost, grid);
    }

    private int findMinCost(int i, int j, int[][] minCost, int[][] grid) {
      if (i >= grid.length || j >= grid[i].length) {
        return EMPTY;
      }
      if (i == grid.length - 1 && j == grid[i].length - 1) {
        return grid[i][j];
      }
      if (minCost[i][j] != EMPTY) {
        return minCost[i][j] + grid[i][j];
      }
      int right = findMinCost(i, j + 1, minCost, grid);
      int bottom = findMinCost(i + 1, j, minCost, grid);
      int min = Math.min(right, bottom);
      minCost[i][j] = min;
      return min + grid[i][j];
    }
  }