package base.leetcode.problems;


import base.leetcode.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

public class HouseRobber {

    private Map<TreeNode, Integer> map;

    public int rob(TreeNode root) {
        this.map = new HashMap<>();
        return recurse(root);
    }

    private int recurse(TreeNode node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            map.put(node, node.val);
            return node.val;
        } else if (map.containsKey(node)) {
            return map.get(node);
        } else {
            int second = recurse(node.left) + recurse(node.right);
            int first = node.val;
            if (node.left != null) {
                first += recurse(node.left.left) + recurse(node.left.right);
            }
            if (node.right != null) {
                first += recurse(node.right.left) + recurse(node.right.right);
            }
            int value = Math.max(first, second);
            map.put(node, value);
            return value;
        }
    }

    public List<Integer> getWeights(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelWeight = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelWeight += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(levelWeight);
        }
        return list;
    }

}
