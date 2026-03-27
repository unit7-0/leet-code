public class ReverseBits {
  public static void main(String[] args) {
    System.out.println(reverseBits(43261596));
  }

  public static int reverseBits(int n) {
    int res = 0;
    int numBits = 31;
    int pos = 0;
    while (pos < numBits) {
      res |= ((n & (1 << pos)) >> pos) << (numBits - pos);
      pos++;
    }
    return res;
  }
}
