import java.util.HashMap;
import java.util.Map;

public class TreeFromPreAndInOrderTraversal {
  public class TreeNode {
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

  private final Map<Integer, Integer> inorderIndex = new HashMap<>();
  private int preorderIdx;

  public static void main(String[] args) {
    var t = new TreeFromPreAndInOrderTraversal().buildTree(
        new int[]{3, 9, 20, 15, 7},
        new int[]{9, 3, 15, 20, 7}
    );
    System.out.println(t);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0; i < inorder.length; ++i) {
      inorderIndex.put(inorder[i], i);
    }
    preorderIdx = 0;
    return build(preorder, 0, preorder.length - 1);
  }

  private TreeNode build(int[] preorder, int left, int right) {
    if (left > right) {
      return null;
    }
    var root = new TreeNode(preorder[preorderIdx++]);
    int mid = inorderIndex.get(root.val);
    root.left = build(preorder, left, mid - 1);
    root.right = build(preorder, mid + 1, right);
    return root;
  }
}
