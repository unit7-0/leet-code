
public class RecoverBinarySearchTree {
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
    TreeNode root = new TreeNode(2, new TreeNode(3),
        new TreeNode(1));
    new RecoverBinarySearchTree().recoverTree(
        root
    );
    System.out.println(root);
  }

  private TreeNode prev, first, second;

  public void recoverTree(TreeNode root) {
    inorder(root);
    var tmp = first.val;
    first.val = second.val;
    second.val = tmp;
  }

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }
    inorder(node.left);
    if (prev != null && prev.val > node.val) {
      if (first == null) {
        first = prev;
      }
      second = node;
    }
    prev = node;
    inorder(node.right);
  }
}
