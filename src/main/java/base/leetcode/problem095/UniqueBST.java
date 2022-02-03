package base.leetcode.problem095;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class UniqueBST {

    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private class TreeNodeList {
        private List<TreeNode> list;

        public TreeNodeList(List<TreeNode> list) {
            this.list = list;
        }
    }

    private List<TreeNode> result;
    private TreeNodeList[][] dp;

    public List<TreeNode> generateTrees(int n) {
        dp = new TreeNodeList[n+2][n+2];
        List<TreeNode> nullList = new LinkedList<>();
        nullList.add(null);
        TreeNodeList nullList2 = new TreeNodeList(nullList);

        for (int i = 1; i <= n+1; i++) {
            List<TreeNode> list = new LinkedList<TreeNode>();
            list.add(new TreeNode(i));
            dp[i][i] = new TreeNodeList(list);
            dp[i][i-1] = nullList2;
        }
        for (int diff = 1; diff < n; diff++) {

            for (int start = 1; start <= n - diff; start++) {
                int end = start + diff;
                List<TreeNode> list = new LinkedList<TreeNode>();
                for (int middle = start; middle <= end; middle++) {
                    list.addAll(create(middle, dp[start][middle-1], dp[middle+1][end]));
                }
                TreeNodeList llist = new TreeNodeList(list);
                dp[start][end] = llist;
            }
        }
        return dp[1][n].list;
    }

    private List<TreeNode> create(int middle, TreeNodeList leftChildren, TreeNodeList rightChildren) {
        List<TreeNode> result = new LinkedList<>();
        for (TreeNode leftChild : leftChildren.list) {
            for (TreeNode rightChild : rightChildren.list) {
                TreeNode node = new TreeNode(middle, leftChild, rightChild);
                result.add(node);
            }
        }
        return result;
    }

    private static void printNode(TreeNode node) {
        String val = node != null ? node.val + "" : "null";
        System.out.print(val + " ");
        if (node != null) {
            printNode(node.left);
            printNode(node.right);
        }
    }

    public static void main(String[] args) {
        UniqueBST bst = new UniqueBST();
        List<TreeNode> nodes = bst.generateTrees(4);
        for (TreeNode node : nodes) {
            printNode(node);
            System.out.println();
        }
//        System.out.println(bst.generateTrees(3));
    }
}
