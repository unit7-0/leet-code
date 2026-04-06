public class LongestRepeatingCharReplacement {
  public int characterReplacement(String s, int k) {
    int n = s.length();
    int[] freqs = new int[26];
    int maxFreq = 1;
    int l = 0, r = 0;
    int maxWindowSize = 1;
    while (r < n) {
      maxFreq = Math.max(maxFreq, ++freqs[s.charAt(r) - 'A']);
      if (!isValidWindow(l, r, k, maxFreq)) {
        freqs[s.charAt(l) - 'A']--;
        ++l;
      }
      maxWindowSize = Math.max(maxWindowSize, r - l + 1);
      ++r;
    }
    return maxWindowSize;
  }

  private boolean isValidWindow(int l, int r, int k, int maxFreq) {
    return r - l + 1 - maxFreq <= k;
  }
}
