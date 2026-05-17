public class RemoveDuplicatesFromSortedArray2 {
  public static void main(String[] args) {
    RemoveDuplicatesFromSortedArray2 r = new RemoveDuplicatesFromSortedArray2();
    System.out.println(r.removeDuplicates(new int[]{
       1, 2, 2
    }));
  }

  public int removeDuplicates(int[] nums) {
    int n = nums.length;
    int j = Math.min(2, n);
    for (int i = j; i < n; ++i) {
      if (nums[i] != nums[j - 2]) {
        nums[j++] = nums[i];
      }
    }
    return j;
  }
}
