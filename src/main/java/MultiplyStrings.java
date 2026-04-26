public class MultiplyStrings {
  public String multiply(String num1, String num2) {
    int[] result = new int[num1.length() + num2.length()];
    for (int i = num2.length() - 1; i >= 0; --i) {
      int pos = num2.length() - i - 1;
      for (int j = num1.length() - 1; j >= 0; --j, ++pos) {
        int a = num1.charAt(j) - '0';
        int b = num2.charAt(i) - '0';
        result[pos] += a * b;
      }
    }

    handleCarry(result);

    var rb = new StringBuilder();
    int pos = result.length - 1;
    while (pos > 0 && result[pos] == 0) {
      --pos;
    }
    while (pos >= 0) {
      rb.append(result[pos--]);
    }

    return rb.toString();
  }

  private void handleCarry(int[] result) {
    for (int i = 0; i < result.length - 1; ++i) {
      int c = result[i] / 10;
      result[i] = result[i] % 10;
      result[i + 1] += c;
    }
  }
}
