import java.util.HashMap;
import java.util.Map;

class LFUCache {

  public static void main(String[] args) {
    LFUCache cache = new LFUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.get(2));
    System.out.println(cache.get(3));
    cache.put(4, 4);
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
  }

  private final int capacity;
  private final Map<Integer, Node> cache = new HashMap<>();
  private final Map<Integer, DLList> listByCounter = new HashMap<>();
  private int lfuCounter;

  public LFUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    var value = cache.get(key);
    if (value == null) {
      return -1; // default
    }
    increment(value);
    return value.value;
  }

  private void increment(Node node) {
    var currentList = listByCounter.get(node.counter);
    if (currentList != null) {
      currentList.remove(node);
    }
    node.counter++;
    var nextList = listByCounter.computeIfAbsent(node.counter, __ -> new DLList());
    nextList.insertFirst(node);
    var lfuList = listByCounter.get(lfuCounter);
    if (lfuList == null || lfuList.isEmpty()) {
      lfuCounter = node.counter;
    } else {
      lfuCounter = Math.min(lfuCounter, node.counter);
    }
  }

  private void removeAndResetLfu() {
    var lfuList = listByCounter.get(lfuCounter);
    var removed = lfuList.removeLast();
    lfuCounter = 1;
    if (removed != null) {
      cache.remove(removed.key);
    }
  }

  public void put(int key, int value) {
    var existing = cache.get(key);
    if (existing == null && cache.size() == capacity) {
      removeAndResetLfu();
    }
    var updated = existing;
    if (updated == null) {
      updated = new Node();
    }
    updated.key = key;
    updated.value = value;
    cache.put(key, updated);
    increment(updated);
  }

  private static final class DLList {
    Node head;
    Node tail;

    void remove(Node node) {
      if (node.next != null) {
        node.next.prev = node.prev;
      }
      if (node.prev != null) {
        node.prev.next = node.next;
      }
      if (node == head) {
        head = node.next;
      }
      if (node == tail) {
        tail = node.prev;
      }
      node.next = null;
      node.prev = null;
    }

    void insertFirst(Node node) {
      if (head == null) {
        head = node;
        tail = node;
        return;
      }
      node.next = head;
      node.prev = null;
      head.prev = node;
      head = node;
    }

    Node removeLast() {
      var removed = tail;
      if (tail == null) {
        return removed;
      }
      var prev = tail.prev;
      if (prev != null) {
        prev.next = null;
      }
      tail.prev = null;
      tail = prev;
      if (tail == null) {
        head = null;
      }
      return removed;
    }

    boolean isEmpty() {
      return head == null;
    }
  }

  private static final class Node {
    Node next;
    Node prev;
    int key;
    int value;
    int counter;

    Node() {
    }
  }
}

