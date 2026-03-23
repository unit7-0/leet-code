import java.util.*;

class Router {
  private final int memoryLimit;
  private final Set<Package> queue;
  private final Map<Integer, ArrayList<Integer>> destinationTimestamps;
  private final Map<Integer, Integer> destStartIdx = new HashMap<>();

  public static void main(String[] args) {
    Router router = new Router(10); // Initialize Router with memoryLimit of 3.
    System.out.println(
    router.addPacket(5, 2, 0) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(4, 2, 4) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(4, 2, 8) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(5, 2, 8) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(4, 2, 9) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(4, 2, 10) // Packet is added. Return True.
    );
    System.out.println(
    router.addPacket(4, 2, 13) // Packet is added. Return True.
    );
    System.out.println(
        Arrays.toString(router.forwardPacket()) // Return [2, 5, 90] and remove it from router.
    );
    System.out.println(
    router.getCount(2, 1, 10) // The only packet with destination 5 and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
    );
  }

  public Router(int memoryLimit) {
    if (memoryLimit <= 0) {
      throw new IllegalArgumentException();
    }
    this.memoryLimit = memoryLimit;
    this.queue = new LinkedHashSet<>();
    this.destinationTimestamps = new HashMap<>();
  }

  public boolean addPacket(int source, int destination, int timestamp) {
    var pack = new Package(source, destination, timestamp);
    if (queue.contains(pack)) {
      return false;
    }
    if (queue.size() >= memoryLimit) {
      var it = queue.iterator();
      removePackage(it);
    }
    destinationTimestamps.computeIfAbsent(destination, __ -> new ArrayList<>()).add(timestamp);
    queue.add(pack);
    return true;
  }

  public int[] forwardPacket() {
    var it = queue.iterator();
    if (!it.hasNext()) {
      return new int[] {};
    }
    var pack = removePackage(it);
    return new int[] { pack.source, pack.destination, pack.timestamp };
  }

  private Package removePackage(Iterator<Package> it) {
    var pack = it.next();
    it.remove();
    destStartIdx.put(pack.destination, destStartIdx.getOrDefault(pack.destination, 0) + 1);
    return pack;
  }

  public int getCount(int destination, int startTime, int endTime) {
    var timestamps = destinationTimestamps.get(destination);
    if (timestamps == null) {
      return 0;
    }
    var start = destStartIdx.getOrDefault(destination, 0);
    var l = lowerBound(timestamps, start, startTime);
    var r = upperBound(timestamps, start, endTime);
    return r - l;
  }

  private int lowerBound(ArrayList<Integer> list, int start, int target) {
    int l = start, r = list.size();
    while (l < r) {
      int m = (l + r) >>> 1;
      if (list.get(m) < target) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }

  private int upperBound(ArrayList<Integer> list, int start, int target) {
    int l = start, r = list.size();
    while (l < r) {
      int m = (l + r) >>> 1;
      if (list.get(m) <= target) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }

  record Package(int source, int destination, int timestamp) {}
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */