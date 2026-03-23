import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DivisorGame {

  public static void main(String[] args) {
    new DivisorGame().divisorGame(20);
  }

  boolean divisorGame(int n) {
    int[] dp = new int[n + 1];
    for (int i = 2; i < n + 1; i++) {
      for (int j = 1; j < i; j++) {
        if (i % j == 0) {
          dp[i] = dp[i] | (dp[i - j] == 0 ? 1 : 0);  //(!dp[i-j] checks if i-j is a losing position)
        }
      }
    }
    var s1 = IntStream.range(0, dp.length).boxed().map(i -> String.format("%02d", i)).collect(Collectors.joining(","));
    var s2 = Arrays.stream(dp).boxed().map(i -> String.format("%02d", i)).collect(Collectors.joining(","));
    System.out.println(s1);
    System.out.println(s2);
    return dp[n] > 0;
  }
}
