package base.leetcode.problem257;

import base.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreePaths {
    private List<String> result;
    private LinkedList<TreeNode> path;

    public List<String> binaryTreePaths(TreeNode root) {
        result = new LinkedList<>();
        path = new LinkedList<>();
        iterate(root);
        return result;
    }

    private void iterate(TreeNode node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                path.add(node);
                result.add(getPath(path));
                path.removeLast();
            } else {
                path.add(node);
                iterate(node.left);
                iterate(node.right);
                path.removeLast();
            }
        }
    }

    private String getPath(List<TreeNode> nodes) {
        String path = nodes.stream().map((x) -> x.val + "").collect(Collectors.joining("->"));
        return path;
    }

    public static void main(String[] args) {
        TreeNode zero = new TreeNode(0);
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2, zero, five);
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1, two, three);
        BinaryTreePaths btp = new BinaryTreePaths();
        List<String> result = btp.binaryTreePaths(one);
        System.out.println(result);
    }

}
