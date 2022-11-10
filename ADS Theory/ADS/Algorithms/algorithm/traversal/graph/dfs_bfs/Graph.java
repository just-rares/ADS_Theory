package algorithm.traversal.graph.dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    ArrayList<Node> nodes;
    int[][] matrix;

    Graph (int size) {
        nodes = new ArrayList<>();
        matrix = new int[size][size];
    }

    public void addNode (Node node) {
        nodes.add(node);
    }
    public void addEdge (int src, int dst) {
        matrix[src][dst] = 1;
    }

    public boolean checkEdge (int src, int dst) {
        if (matrix[src][dst] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void print () {
        System.out.print("  ");
        for (Node node : nodes){
            System.out.print(node.data + " ");
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++){
            System.out.print(nodes.get(i).data + " ");
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void depthFirstSearch(int src, List<Character> sol) {
        boolean[] visited = new boolean[matrix.length];
        dfsHelper(src, visited, sol);
    }
    private void dfsHelper(int src, boolean[] visited, List<Character> sol){
        if(visited[src]){
            return;
        } else {
            visited[src] = true;
            sol.add(nodes.get(src).data);
        }

        for(int i = 0; i < matrix[src].length; ++i){
            if(matrix[src][i] == 1) {
                dfsHelper(i, visited, sol);
            }
        }
    }
    public void breadthFirstSearch(int src, List<Character> sol){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[matrix.length];

        queue.offer(src);
        visited[src] = true;

        while(queue.size() != 0) {
            src = queue.poll();
            sol.add(nodes.get(src).data);

            for(int i = 0; i < matrix[src].length; ++i){
                if(matrix[src][i] == 1 && !visited[i]){
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}


