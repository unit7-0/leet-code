public class TaskScheduler {
  public static void main(String[] args) {
    TaskScheduler ts = new TaskScheduler();
    System.out.println(ts.leastInterval(
        new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2
    ));
  }

  public int leastInterval(char[] input, int n) {
    Task[] tasks = new Task[26];
    int toBeExecuted = 0;
    for (char task : input) {
      int idx = task - 'A';
      if (tasks[idx] == null) {
        tasks[idx] = new Task();
      }
      tasks[idx].executionsLeft++;
      toBeExecuted++;
    }

    int time = 0;
    int idles = 0;
    while (toBeExecuted > 0) {
      Task next = null;
      int earliestTime = Integer.MAX_VALUE;
      for (var task : tasks) {
        if (task == null) {
          continue;
        }
        next = nextTaskToExecute(next, task, time, n);
        if (task.executionsLeft > 0) {
          earliestTime = Math.min(earliestTime, task.time);
        }
      }
      if (next != null) {
        next.executionsLeft--;
        next.time = time;
        time++;
        toBeExecuted--;
      } else {
        int idleCycles = (earliestTime + n + 1) - time;
        idles += idleCycles;
        time += idleCycles;
      }
    }
    return idles + input.length;
  }

  private Task nextTaskToExecute(Task next, Task task, int time, int n) {
    if (task.executionsLeft > 0 && time > task.time + n && (next == null || next.executionsLeft < task.executionsLeft)) {
      return task;
    }
    return next;
  }

  private static class Task {
    int executionsLeft;
    int time = Integer.MIN_VALUE;
  }
}
