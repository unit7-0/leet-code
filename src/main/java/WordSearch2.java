import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearch2 {
  // TODO index words, not the board

  public static void main(String[] args) {
    WordSearch2 ws = new WordSearch2();
    System.out.println(ws.findWords(
        new char[][]{
            {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
        },
        new String[]{"oath", "pea", "eat", "rain"}
    ));
  }

  private final char taken = '-';
  private final int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public List<String> findWords(char[][] board, String[] words) {
    var trie = buildTrie(board, 10);
    var result = new ArrayList<String>();
    for (var word : words) {
      if (trie.contains(word)) {
        result.add(word);
      }
    }
    return result;
  }

  private Node buildTrie(char[][] board, int maxLen) {
    var root = new Node();
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        buildTrie(root, board, i, j, maxLen);
      }
    }
    return root;
  }

  private void buildTrie(Node root, char[][] board, int i, int j, int charsLeft) {
    if (charsLeft <= 0) {
      return;
    }
    if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
      return;
    }
    if (board[i][j] == taken) {
      return;
    }
    char c = board[i][j];
    board[i][j] = taken;
    var node = root.add(c);
    for (int[] dir : directions) {
      buildTrie(node, board, i + dir[0], j + dir[1], charsLeft - 1);
    }
    board[i][j] = c;
  }

  private static class Node {
    private final Map<Character, Node> next = new HashMap<>();

    Node add(char c) {
      return next.computeIfAbsent(c, __ -> new Node());
    }

    boolean contains(String word) {
      Node root = this;
      int i = 0;
      while (root != null && i < word.length()) {
        root = root.next.get(word.charAt(i++));
      }
      return root != null && i == word.length();
    }
  }
}
