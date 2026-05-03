import java.util.Arrays;

public class HandOfStraights {
  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }
    Arrays.sort(hand);
    for (int i = 0; i < hand.length; ++i) {
      if (hand[i] == -1) {
        continue;
      }
      if (!isSeqExists(hand, i, groupSize)) {
        return false;
      }
    }
    return true;
  }

  private boolean isSeqExists(int[] hand, int i, int groupSize) {
    int prev = hand[i];
    hand[i] = -1;
    groupSize--;
    for (int j = i + 1; j < hand.length && groupSize > 0; ++j) {
      if (hand[j] == -1 || hand[j] - prev == 0) {
        continue;
      }
      if (hand[j] - prev > 1) {
        return false;
      }
      prev = hand[j];
      hand[j] = -1;
      groupSize--;
    }
    return groupSize == 0;
  }
}
