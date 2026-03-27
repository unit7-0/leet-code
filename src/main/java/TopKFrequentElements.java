import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    int n = nums.length;
    var count = new HashMap<Integer, Integer>();
    for (int i : nums) {
      count.put(i, count.getOrDefault(i, 0) + 1);
    }

    List<Integer>[] freq = new List[n + 1];
    for (var entry : count.entrySet()) {
      var i = entry.getKey();
      var f = entry.getValue();
      if (freq[f] == null) {
        freq[f] = new ArrayList<>();
      }
      freq[f].add(i);
    }

    int taken = 0;
    int[] result = new int[k];
    for (int i = n; i >= 0 && taken < k; --i) {
      if (freq[i] != null) {
        for (var e : freq[i]) {
          result[taken++] = e;
          if (taken == k) {
            return result;
          }
        }
      }
    }
    return result;
  }
}
