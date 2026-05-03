public class Pow {
  public static void main(String[] args) {
    Pow p = new Pow();
    System.out.println(p.myPow(2.0000, 13));
  }

  public double myPow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      n = -n;
      x = 1 / x;
    }
    double pow = 1;
    n = Math.abs(n);
    while (n > 1) {
      if ((n & 1) != 0) {
        pow *= x;
      }
      x *= x;
      n >>= 1;
    }
    return pow;
  }
}
