import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubtreeOfAnotherTree {
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

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    var hashes = new HashMap<Long, List<TreeNode>>();
    computeHash(root, hashes);

    long subRootHash = computeHash(subRoot, new HashMap<>());
    var candidates = hashes.get(subRootHash);
    if (candidates == null) {
      return false;
    }
    for (var candidate : candidates) {
      if (isSame(candidate, subRoot)) {
        return true;
      }
    }
    return false;
  }

  private long computeHash(TreeNode node, Map<Long, List<TreeNode>> hashes) {
    if (node == null) {
      return 3;
    }
    var left = computeHash(node.left, hashes);
    var right = computeHash(node.right, hashes);
    var hash = left * 31 + node.val + right * 47;
    hashes.computeIfAbsent(hash, __ -> new ArrayList<>()).add(node);
    return hash;
  }

  private boolean isSame(TreeNode root, TreeNode subRoot) {
    if (subRoot == null && root == null) {
      return true;
    }
    if (root == null || subRoot == null) {
      return false;
    }
    if (root.val != subRoot.val) {
      return false;
    }
    return isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
  }
}
