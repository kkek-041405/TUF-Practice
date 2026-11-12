package runner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Standard Binary Tree Node definition used across Take U Forward & LeetCode.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this.val = 0;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Builds a binary tree from level-order array with nulls (e.g. [3, 9, 20, null, null, 15, 7]).
     */
    public static TreeNode fromLevelOrder(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode curr = queue.poll();

            // Left child
            if (i < arr.length) {
                if (arr[i] != null) {
                    curr.left = new TreeNode(arr[i]);
                    queue.offer(curr.left);
                }
                i++;
            }

            // Right child
            if (i < arr.length) {
                if (arr[i] != null) {
                    curr.right = new TreeNode(arr[i]);
                    queue.offer(curr.right);
                }
                i++;
            }
        }
        return root;
    }

    /**
     * Serializes tree to level-order list of Integers (trailing nulls trimmed).
     */
    public static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                result.add(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                result.add(null);
            }
        }

        // Trim trailing nulls
        int lastNonNull = result.size() - 1;
        while (lastNonNull >= 0 && result.get(lastNonNull) == null) {
            lastNonNull--;
        }
        return result.subList(0, lastNonNull + 1);
    }

    /**
     * Deep structural equality check between two binary trees.
     */
    public static boolean equals(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return equals(t1.left, t2.left) && equals(t1.right, t2.right);
    }

    @Override
    public String toString() {
        return toLevelOrder(this).toString();
    }
}
