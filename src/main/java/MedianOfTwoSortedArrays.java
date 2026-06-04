public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int half = (n + m + 1) / 2;
    if (m > n) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int l1 = 0;
    int r1 = m;
    while (l1 <= r1) {
      int n1 = (l1 + r1) / 2;
      int n2 = half - n1;
      int lr1 = n1 - 1;
      int rl1 = n1;
      int lr2 = n2 - 1;
      int rl2 = n2;

      int lrv1 = Integer.MIN_VALUE;
      int lrv2 = Integer.MIN_VALUE;
      int rlv1 = Integer.MAX_VALUE;
      int rlv2 = Integer.MAX_VALUE;

      if (lr1 >= 0) {
        lrv1 = nums1[lr1];
      }
      if (rl1 < m) {
        rlv1 = nums1[rl1];
      }
      if (lr2 >= 0) {
        lrv2 = nums2[lr2];
      }
      if (rl2 < n) {
        rlv2 = nums2[rl2];
      }

      if (lrv1 <= rlv2 && rlv1 >= lrv2) {
        if ((n + m) % 2 == 0) {
          return (Math.max(lrv1, lrv2) + Math.min(rlv1, rlv2)) / 2.0;
        } else {
          return Math.max(lrv1, lrv2);
        }
      } else {
        if (lrv1 > rlv2) {
          r1 = n1 - 1;
        } else {
          l1 = n1 + 1;
        }
      }
    }
    return 0;
  }
}
