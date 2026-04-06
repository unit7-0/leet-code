public class SumOf2Integers {
  public static void main(String[] args) {
    int a = 5;
    int b = 3;
    while (b != 0) {
      int carry = (a & b) << 1;
      a = a ^ b;
      b = carry;
    }
    System.out.println(a);
    System.out.println(b);
  }
}
