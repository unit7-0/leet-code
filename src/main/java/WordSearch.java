public class WordSearch {
  public static void main(String[] args) {
    System.out.println(new WordSearch().exist(
        new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},
        "ABCCED"
    ));
  }

    public boolean exist(char[][] board, String word) {
      int m = board.length;
      int n = board[0].length;
      var taken = new boolean[m][n];

      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (board[i][j] == word.charAt(0)) {
            if (findWord(board, i, j, 0, word, taken)) {
              return true;
            }
          }
        }
      }

      return findWord(board, 0, 0, 0, word, taken);
    }

    private boolean findWord(char[][] board, int i, int j, int letterIdx, String word, boolean[][] taken) {
      if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
        return false;
      }
      if (taken[i][j]) {
        return false;
      }
      char boardLetter = board[i][j];
      char wordLetter = word.charAt(letterIdx);
      if (boardLetter == wordLetter) {
        taken[i][j] = true;
        letterIdx++;
      } else {
        return false;
      }
      if (letterIdx >= word.length()) {
        return true;
      }

      var result = findWord(board, i, j + 1, letterIdx, word, taken);
      if (!result) {
        result = findWord(board, i + 1, j, letterIdx, word, taken);
      }
      if (!result) {
        result = findWord(board, i, j - 1, letterIdx, word, taken);
      }
      if (!result) {
        result = findWord(board, i - 1, j, letterIdx, word, taken);
      }
      taken[i][j] = false;
      return result;
    }
  }
