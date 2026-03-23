
public class IntegerToRoman {
  private final int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private final String[] chars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  public static void main(String[] args) {
    System.out.println(
        new IntegerToRoman().intToRoman(3749)
    );
  }

  public String intToRoman(int num) {
    var result = new StringBuilder();
    while (num > 0) {
      num = convertNext(num, result);
    }
    return result.toString();
  }

  private int convertNext(int num, StringBuilder result) {
    for (int i = 0; i < values.length; ++i) {
      int probe = values[i];
      if (num >= probe) {
        result.append(chars[i]);
        return num - probe;
      }
    }
    throw new IllegalArgumentException("num=" + num);
  }
}