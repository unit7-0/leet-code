import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTZigZagOrderTraversal {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    System.out.println(
        new BTZigZagOrderTraversal().zigzagLevelOrder(
            new TreeNode(
                1,
                new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, null, new TreeNode(5)))
        )
    );
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return List.of();
    }
    var q = new LinkedList<TreeNode>();
    var result = new ArrayList<List<Integer>>();
    var reverse = false;
    q.add(root);
    while (!q.isEmpty()) {
      int size = q.size();
      var level = new LinkedList<Integer>();
      for (int i = 0; i < size; ++i) {
        TreeNode n = q.pop();
        if (reverse) {
          level.addFirst(n.val);
        } else {
          level.addLast(n.val);
        }
        if (n.left != null) {
          q.add(n.left);
        }
        if (n.right != null) {
          q.add(n.right);
        }
      }
      reverse = !reverse;
      result.add(level);
    }
    return result;
  }
}
