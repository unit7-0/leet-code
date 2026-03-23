public class SwapListPairs {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      return "" + val + "->" + (next == null ? "null" : next.toString());
    }
  }

  public static void main(String[] args) {
    System.out.println(new SwapListPairs().swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))))));
  }

  public ListNode swapPairs(ListNode head) {
    var p = head;
    ListNode prev = null;
    var newHead = head == null || head.next == null ? head : head.next;
    while (p != null && p.next != null) {
      var newCurrent = p.next;
      var newNext = p.next.next;
      if (prev != null) {
        prev.next = newCurrent;
      }
      p.next.next = p;
      p.next = newNext;
      prev = p;
      p = newNext;
    }
    return newHead;
  }
}