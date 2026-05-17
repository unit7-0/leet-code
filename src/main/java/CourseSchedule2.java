import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CourseSchedule2 {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] indegree = new int[numCourses];
    List<Integer>[] graph = new List[numCourses];
    for (int[] prereq : prerequisites) {
      int a = prereq[0];
      int b = prereq[1];
      indegree[a]++;
      if (graph[b] == null) {
        graph[b] = new ArrayList<>();
      }
      graph[b].add(a);
    }
    var q = new ArrayDeque<Integer>();
    for (int i = 0; i < numCourses; ++i) {
      if (indegree[i] == 0) {
        q.addLast(i);
      }
    }
    int[] result = new int[numCourses];
    int resIdx = 0;
    while (!q.isEmpty()) {
      var v = q.pollFirst();
      result[resIdx++] = v;
      if (graph[v] != null) {
        for (int u : graph[v]) {
          indegree[u]--;
          if (indegree[u] == 0) {
            q.addLast(u);
          }
        }
      }
    }
    return resIdx == numCourses ? result : new int[]{};
  }
}
