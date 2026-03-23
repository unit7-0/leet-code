import com.sun.source.tree.Tree;

public class BinTreeToLinkedList {
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

    @Override
    public String toString() {
      return "[" + val + "," + left + "," + right + "]";
    }
  }

  public static void main(String[] args) {
    TreeNode res = new TreeNode(
        1, new TreeNode(
        2, new TreeNode(
        3, null, null),
        new TreeNode(4, null, null)),
        new TreeNode(5, null, new TreeNode(6, null, null))
    );
    new BinTreeToLinkedList().flatten(
        res
    );
    System.out.println(res);
  }

  public void flatten(TreeNode root) {
    flatten0(root);
  }

  private TreeNode flatten0(TreeNode node) {
    if (node == null) return null;

    TreeNode leftTail = flatten0(node.left);
    TreeNode rightTail = flatten0(node.right);

    if (node.left != null) {
      TreeNode temp = node.right;
      node.right = node.left;
      node.left = null;

      leftTail.right = temp;
    }

    if (rightTail != null) return rightTail;
    if (leftTail != null) return leftTail;
    return node;
  }
}