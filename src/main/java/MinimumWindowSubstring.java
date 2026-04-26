public class MinimumWindowSubstring {
  public static void main(String[] args) {
    var s = new MinimumWindowSubstring();
    System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
  }

  public String minWindow(String s, String t) {
    int[] count = new int[128];
    int required = 0;
    for (int i = 0; i < t.length(); ++i) {
      count[t.charAt(i)]++;
      required++;
    }
    int left = 0;
    int right = 0;
    int minStart = 0;
    int minWindow = Integer.MAX_VALUE;
    while (right < s.length()) {
      char c = s.charAt(right++);
      count[c]--;
      if (count[c] >= 0) {
        required--;
      }
      while (required == 0) {
        if (right - left < minWindow) {
          minWindow = right - left;
          minStart = left;
        }
        int c1 = s.charAt(left++);
        count[c1]++;
        if (count[c1] > 0) {
          required++;
        }
      }
    }

    return minWindow == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minWindow);
  }
}
