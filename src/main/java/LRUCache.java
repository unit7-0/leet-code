import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
  private final int capacity;
  private final Map<Integer, Node> cache = new HashMap<>();

  private Node head;
  private Node tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    var e = cache.get(key);
    if (e == null) {
      return -1;
    }
    touch(e);
    return e.value;
  }

  public void put(int key, int value) {
    var existing = cache.get(key);
    if (existing != null) {
      existing.value = value;
      touch(existing);
    } else {
      var node = new Node(key, value);
      cache.put(key, node);
      add(node);
    }
    if (cache.size() > capacity) {
      cache.remove(head.key);
      remove(head);
    }
  }

  private void touch(Node node) {
    remove(node);
    add(node);
  }

  private void remove(Node node) {
    var previous = node.previous;
    var next = node.next;
    if (previous == null && next == null) {
      head = null;
      tail = null;
    } else if (previous == null) {
      next.previous = null;
      head = next;
    } else if (next == null) {
      previous.next = null;
      tail = previous;
    } else {
      previous.next = next;
      next.previous = previous;
    }
    node.next = null;
    node.previous = null;
  }

  private void add(Node node) {
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      node.previous = tail;
      tail = node;
    }
  }

  private static final class Node {
    final int key;
    int value;
    Node next;
    Node previous;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    var cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.get(2));
    cache.put(4, 4);
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
  }
}