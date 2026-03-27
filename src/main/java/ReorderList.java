public class ReorderList {

  public static class ListNode {
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

    @Override
    public String toString() {
      return String.valueOf(val);
    }
  }

  public static void main(String[] args) {
    new ReorderList().reorderList(
        new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4)))));
  }

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    var fast = head;
    var slow = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    var second = reverse(slow.next);
    slow.next = null;
    merge(head, second);
    System.out.println(head);
  }

  private void merge(ListNode head, ListNode tail) {
    var p1 = head;
    var p2 = tail;
    while (p1 != null && p2 != null) {
      var np1 = p1.next;
      var np2 = p2.next;
      p1.next = p2;
      p2.next = np1;
      p1 = np1;
      p2 = np2;
    }
  }

  private ListNode reverse(ListNode node) {
    if (node == null) {
      return null;
    }
    var p = node.next;
    ListNode head = node;
    head.next = null;
    while (p != null) {
      var tmp = p.next;
      p.next = head;
      head = p;
      p = tmp;
    }
    return head;
  }
}
