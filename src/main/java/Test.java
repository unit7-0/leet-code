import java.util.Random;

public class Test {
  public static void main(String[] args) {
    int r = new Random().nextInt(10);
    System.out.println(r);
    String x = String.format("%02x", r);
    System.out.println(x);
    System.out.println(Integer.parseInt(x, 16));
  }
}
