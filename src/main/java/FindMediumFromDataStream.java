import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMediumFromDataStream {

  public static void main(String[] args) {
    MedianFinder finder = new MedianFinder();
    finder.addNum(1);
    finder.addNum(2);
    finder.addNum(3);
    finder.addNum(4);
    finder.addNum(5);
    System.out.println(finder.findMedian());
  }

  static class MedianFinder {
    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
      if (maxHeap.isEmpty() || maxHeap.peek() > num) {
        maxHeap.add(num);
      } else {
        minHeap.add(num);
      }
      if (maxHeap.size() > minHeap.size() + 1) {
        minHeap.add(maxHeap.poll());
      } else if (maxHeap.size() + 1 < minHeap.size()) {
        maxHeap.add(minHeap.poll());
      }
    }

    public double findMedian() {
      if (maxHeap.size() == minHeap.size()) {
        return ((double) minHeap.peek() + maxHeap.peek()) / 2;
      } else if (maxHeap.size() > minHeap.size()) {
        return maxHeap.peek();
      } else {
        return minHeap.peek();
      }
    }
  }

}
