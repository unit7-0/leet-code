public class InterleavingString {
  public static void main(String[] args) {
    InterleavingString s = new InterleavingString();
    System.out.println(s.isInterleave("", "b", "b"));
  }

    public boolean isInterleave(String s1, String s2, String s3) {
      int ns1 = s1.length();
      int ns2 = s2.length();
      int ns3 = s3.length();
      if (ns3 != ns1 + ns2) {
        return false;
      }

      boolean[] prev = new boolean[ns2 + 1];
      boolean[] curr = new boolean[ns2 + 1];
      prev[0] = true;
      curr[0] = true;

      for (int j = 1; j <= ns2; ++j) {
        prev[j] = prev[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
          curr[j] = prev[j];
      }

      for (int i = 1; i <= ns1; ++i) {
        curr[0] = prev[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int j = 1; j <= ns2; ++j) {
          int is3 = i + j - 1;
          curr[j] = prev[j] && s1.charAt(i - 1) == s3.charAt(is3);
          curr[j] |= curr[j - 1] && s2.charAt(j - 1) == s3.charAt(is3);
        }
        prev = curr;
      }

      return curr[ns2];
    }
}
