import java.util.*;

public class WordsDataStructure {

  public static void main(String[] args) {
    var w = new WordsDataStructure();
    w.addWord("a");
    w.addWord("ab");
    System.out.println(w.search("a"));
  }

  private final Node head = new Node();

  public void addWord(String word) {
    var p = head;
    for (int i = 0; i < word.length(); ++i) {
      char c = word.charAt(i);
      p = p.add(c, i == word.length() - 1);
    }
  }

  public boolean search(String word) {
    return search(head, 0, word);
  }

  public boolean search(Node node, int i, String word) {
    if (i == word.length() && node != null && node.leaf) {
      return true;
    }
    char c = word.charAt(i);
    if (c == '.') {
      for (var n : node.getAll()) {
        if (search(n, i + 1, word)) {
          return true;
        }
      }
      return false;
    }
    return search(node.get(c), i + 1, word);
  }

  private static class Node {
    final Map<Character, Node> next = new HashMap<>();
    boolean leaf;

    Node add(char letter, boolean leaf) {
      var node = next.computeIfAbsent(letter, __ -> new Node());
      node.leaf |= leaf;
      return node;
    }

    Node get(char letter) {
      return next.get(letter);
    }

    Collection<Node> getAll() {
      return next.values();
    }
  }
}

