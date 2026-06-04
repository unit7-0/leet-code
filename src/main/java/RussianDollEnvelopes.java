import java.util.Arrays;

public class RussianDollEnvelopes {
  public int maxEnvelopes(int[][] envelopes) {
    int n = envelopes.length;
    int[] tails = new int[n];
    Arrays.sort(
        envelopes,
        (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0])
    );
    int size = 0;
    for (int i = 0; i < n; ++i) {
      int h = envelopes[i][1];
      size = updateTail(tails, size, h, i);
    }
    return size;
  }

  private static int updateTail(int[] tails, int size, int h, int i) {
    int l = 0, r = size;
    while (l < r) {
      int m = (l + r) / 2;
      if (tails[m] < h) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    tails[l] = h;
    if (l == size) {
      size++;
    }
    return size;
  }
}
