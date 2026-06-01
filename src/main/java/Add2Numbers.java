public class Add2Numbers {
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

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    var head = new ListNode();
    var next = head;
    var p1 = l1;
    var p2 = l2;
    int carry = 0;
    ListNode prev = null;
    while (p1 != null || p2 != null) {
      int sum = (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val) + carry;
      next.val = sum % 10;
      carry = sum / 10;
      prev = next;
      next.next = new ListNode();
      next = next.next;
      p1 = p1 == null ? null : p1.next;
      p2 = p2 == null ? null : p2.next;
    }
    if (carry != 0) {
      next.val = carry;
    } else if (prev != null) {
      prev.next = null;
    }
    return head;
  }
}
