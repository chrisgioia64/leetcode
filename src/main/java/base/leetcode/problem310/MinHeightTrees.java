package base.leetcode.problem310;

import java.util.*;

public class MinHeightTrees {

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 0}, {1, 2}, {1, 3}
        };
        MinHeightTrees mht = new MinHeightTrees();
        List<Integer> minHeightTrees = mht.findMinHeightTrees(4, edges);
        System.out.println(minHeightTrees);

        edges = new int[][] {{3,0},{3,1},{3,2},{3,4},{5,4}};
        minHeightTrees = mht.findMinHeightTrees(6, edges);
        System.out.println(minHeightTrees);
    }


    private List<List<Integer>> adjList;
    private int[][] depths;
    private int n;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] heights = new int[n];
        adjList = getAdjList(n, edges);
        depths = new int[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                depths[i][j] = -1;
            }
        }
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            heights[i] = getHeight(i, -1, visited);
//            System.out.println(i + " " + heights[i]);
            minHeight = Math.min(minHeight, heights[i]);
        }
//        System.out.println(Arrays.deepToString(this.depths));
        List<Integer> minHeightNodes = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (heights[i] == minHeight) {
                minHeightNodes.add(i);
            }
        }
        return minHeightNodes;
    }

    private List<List<Integer>> getAdjList(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<>());
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adjList.get(x).add(y);
            adjList.get(y).add(x);
        }
        return adjList;
    }

    private int getHeight(int i, int last, boolean[] visited) {
        int depth = 1;
        if (!visited[i]) {
//            System.out.println("explore: " + i + " " + last);
            visited[i] = true;
            for (Integer integer : adjList.get(i)) {
//                System.out.println("neighbor of " + i + " -> " + integer);
                if (visited[integer]) {
                    continue;
                } else if (depths[i][integer] == -1) {
                    int d = getHeight(integer, i, visited);
                    depth = Math.max(depth, 1 + d);
                } else {
                    depth = Math.max(depth, 1 + depths[i][integer]);
                }
            }
        }
        if (last != -1) {
            depths[last][i] = depth;
        }
        return depth;
    }

    private int getHeight(int i, List<List<Integer>> adjList, int n) {
        boolean[] set = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int j = 0; j < levelSize; j++) {
                Integer node = queue.poll();
                if (!set[node]) {
                    queue.addAll(adjList.get(node));
                    set[node] = true;
                }
            }
            level++;
        }
        return level;
    }

}
