public class Sqrt {
  public static void main(String[] args) {
    System.out.println(
        new Sqrt().mySqrt(2147483647)
    );
  }

  public int mySqrt(int x) {
    int r = x;
    int l = 1;
    int ans = 0;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (mid != 0 && mid <= x / mid) {
        ans = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    // l + (r - l) / 2
    return ans;
  }
}
