import java.util.LinkedList;

public class SerializeTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public class Codec {

    public String serialize(TreeNode root) {
      var queue = new LinkedList<TreeNode>();
      queue.add(root);
      var result = new StringBuilder();
      while (!queue.isEmpty()) {
        var node = queue.poll();
        if (node == null) {
          result.append("n,");
        } else {
          result.append(node.val);
          result.append(",");
          queue.add(node.left);
          queue.add(node.right);
        }
      }
      result.delete(result.length() - 1, result.length());
      return result.toString();
    }

    public TreeNode deserialize(String data) {
      if ("n".equals(data)) {
        return null;
      }
      String[] parts = data.split(",");
      var queue = new LinkedList<TreeNode>();
      var root = new TreeNode(Integer.parseInt(parts[0]));
      queue.add(root);
      for (int i = 1; i < parts.length; ) {
        String leftPart = parts[i++];
        String rightPart = parts[i++];
        TreeNode node = queue.poll();
        if (!"n".equals(leftPart)) {
          node.left = new TreeNode(Integer.parseInt(leftPart));
          queue.add(node.left);
        }
        if (!"n".equals(rightPart)) {
          node.right = new TreeNode(Integer.parseInt(rightPart));
          queue.add(node.right);
        }
      }
      return root;
    }

  }

}
