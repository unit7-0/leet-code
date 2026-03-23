public class RotateList {
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

  public ListNode rotateRight(ListNode head, int k) {
    int n = size(head);
    if (n < 2) {
      return head;
    }
    int normalizedK = k % n;

    if (normalizedK == 0) {
      return head;
    }

    if (n == 2) {
      var newHead = head.next;
      head.next = null;
      newHead.next = head;
      return newHead;
    }

    var last = findNth(head, n - 1);
    var beforeNewHead = findNth(head, n - normalizedK - 1);
    var newHead = beforeNewHead.next;
    beforeNewHead.next = null;
    last.next = head;

    return newHead;
  }

  private int size(ListNode head) {
    int counter = 0;
    var p = head;
    while (p != null) {
      p = p.next;
      counter++;
    }
    return counter;
  }

  private ListNode findNth(ListNode head, int i) {
    var p = head;
    while (p != null && i-- > 0) {
      p = p.next;
    }
    return p;
  }
}
