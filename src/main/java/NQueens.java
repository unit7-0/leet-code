import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
  private List<List<String>> result = new ArrayList<>();
  private boolean[] cols;
  private boolean[] mainDiag;
  private boolean[] antiDiag;

  public List<List<String>> solveNQueens(int n) {
    char[][] board = new char[n][n];
    cols = new boolean[n];
    mainDiag = new boolean[2 * n - 1];
    antiDiag = new boolean[2 * n - 1];
    for (int i = 0; i < n; ++i) {
      Arrays.fill(board[i], '.');
    }
    placeQueen(board, -1);
    return result;
  }

  private void setPosition(char[][] board, int i, int j, boolean set) {
    int n = board.length;
    board[i][j] = set ? 'Q' : '.';
    cols[j] = set;
    mainDiag[mainDiagIdx(i, j, n)] = set;
    antiDiag[i + j] = set;
  }

  private void placeQueen(char[][] board, int r) {
    int n = board.length;
    if (r == n - 1) {
      addToResult(board);
      return;
    }
    int i = r + 1;
    for (int j = 0; j < n; ++j) {
      if (isValid(i, j, n)) {
        setPosition(board, i, j, true);
        placeQueen(board, i);
        setPosition(board, i, j, false);
      }
    }
  }

  private int mainDiagIdx(int r, int c, int n) {
    return r - c + (n - 1);
  }

  private void addToResult(char[][] board) {
    int n = board.length;
    var rl = new ArrayList<String>();
    for (int i = 0; i < n; ++i) {
      rl.add(new String(board[i]));
    }
    result.add(rl);
  }

  private boolean isValid(int r, int c, int n) {
    return !(cols[c] || mainDiag[mainDiagIdx(r, c, n)] || antiDiag[r + c]);
  }
}
