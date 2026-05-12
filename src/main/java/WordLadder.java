import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordLadder {
  public static void main(String[] args) {
    WordLadder wl = new WordLadder();
    System.out.println(wl.ladderLength(
        "hit", "cog",
        List.of("hot", "dot", "tog", "cog")
    ));
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    var words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
      return 0;
    }
    var q = new LinkedList<String>();
    q.addLast(beginWord);
    int level = 1;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int n = size; n > 0; --n) {
        var word = q.pollFirst();
        if (word.equals(endWord)) {
          return level;
        }
        var newWord = word.toCharArray();
        for (int i = 0; i < newWord.length; ++i) {
          for (char c = 'a'; c <= 'z'; ++c) {
            if (c == word.charAt(i)) {
              continue;
            }
            char t = newWord[i];
            newWord[i] = c;
            var s = new String(newWord);
            if (words.contains(s)) {
              q.addLast(s);
              words.remove(s);
            }
            newWord[i] = t;
          }
        }
      }
      level++;
    }
    return 0;
  }
}
