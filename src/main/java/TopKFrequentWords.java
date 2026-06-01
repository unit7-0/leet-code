import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFrequentWords {
  public List<String> topKFrequent(String[] words, int k) {
    var freq = new HashMap<String, Integer>();
    for (var word : words) {
      int updated = freq.getOrDefault(word, 0) + 1;
      freq.put(word, updated);
    }
    var pq = new PriorityQueue<Word>((a, b) -> {
      if (a.count != b.count) {
        return Integer.compare(a.count, b.count);
      }
      return b.w.compareTo(a.w);
    });
    for (var entry : freq.entrySet()) {
      pq.offer(new Word(entry.getKey(), entry.getValue()));
      if (pq.size() > k) {
        pq.poll();
      }
    }
    var kWords = new String[pq.size()];
    for (int i = pq.size() - 1; !pq.isEmpty(); --i) {
      kWords[i] = pq.poll().w;
    }
    return Arrays.asList(kWords);
  }

  private record Word(String w, int count) {
  }
}
