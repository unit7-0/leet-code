public class AddBinary {
  public String addBinary(String a, String b) {
    int carry = 0;
    int n = a.length();
    int m = b.length();
    int i = n - 1, j = m - 1;
    if (n < m) {
      return addBinary(b, a);
    }
    int k = n;
    char[] result = new char[n + 1];
    while (i >= 0) {
      carry += a.charAt(i) - '0';
      if (j >= 0) {
        carry += b.charAt(j) - '0';
      }
      result[k] = (char) ((carry & 1) + '0');
      carry >>= 1;
      --k;
      --i;
      --j;
    }
    if (carry > 0) {
      result[k] = '1';
      return new String(result);
    }
    return new String(result, 1, n);
  }
}
