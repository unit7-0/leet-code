import java.util.ArrayList;
import java.util.List;

public class LongestPalindrome {

  public static void main(String[] args) {
    System.out.println(
        new LongestPalindrome().longestPalindrome("cbbd")
    );
  }

  public String longestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }
    int n = s.length();
    int start = 0, end = 0;
    for (int center = 0; center < n; ++center) {
      int len1 = findLongestPalindrome(s, center, center);
      int len2 = findLongestPalindrome(s, center, center + 1);
      int maxLen = Math.max(len1, len2);
      if (maxLen > end - start + 1) {
        start = center - (maxLen - 1) / 2;
        end = center + maxLen / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int findLongestPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    return right - left - 1;
  }
}