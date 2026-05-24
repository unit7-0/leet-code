public class ReverseNodesInKGroup {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode groupPrev = dummy;

    while (true) {
      var kth = getKthAfter(groupPrev, k);
      if (kth == null) {
        break;
      }
      var groupNext = kth.next;
      var curr = groupPrev.next;
      var prev = groupNext;
      while (curr != groupNext) {
        var tmp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = tmp;
      }
      var tmp = groupPrev.next;
      groupPrev.next = kth;
      groupPrev = tmp;
    }
    return dummy.next;
  }

  private ListNode getKthAfter(ListNode start, int k) {
    while (k > 0 && start != null) {
      start = start.next;
      k--;
    }
    return start;
  }
}
