/*
 * Topological sort
 * Difficulty: Medium
 * Accuracy: 56.52%
 * Submissions: 219K+
 * Points: 4
 * 
 * Given an adjacency list for a Directed Acyclic Graph (DAG) where adj_list[i] 
 * contains a list of all vertices j such that there is a directed edge from 
 * vertex i to vertex j, with V vertices and E edges, your task is to find any 
 * valid topological sorting of the graph.
 * 
 * In a topological sort, for every directed edge u -> v, u must come before v 
 * in the ordering.
 * 
 * Example 1:
 * Input:
 * V = 4, adj = [[], [0], [0], [1, 2]]
 * Output:
 * 1
 * Explanation:
 * The output 1 denotes that the order is valid. So, if you have implemented 
 * your function correctly, then output would be 1 for all test cases. One 
 * possible Topological order for the graph is 3, 2, 1, 0.
 * 
 * Example 2:
 * Input:
 * V = 6, adj = [[], [0], [0], [1, 2], [3], [4]]
 * Output:
 * 1
 * Explanation:
 * The output 1 denotes that the order is valid. So, if you have implemented 
 * your function correctly, then output would be 1 for all test cases. One 
 * possible Topological order for the graph is 5, 4, 2, 1, 3, 0.
 * 
 * Your Task:
 * You don't need to read input or print anything. Your task is to complete the 
 * function topoSort() which takes the integer V denoting the number of vertices 
 * and adjacency list as input parameters and returns an array consisting of the 
 * vertices in Topological order. As there are multiple Topological orders 
 * possible, you may return any of them. If your returned topo sort is correct 
 * then the console output will be 1 else 0.
 * 
 * Expected Time Complexity: O(V + E).
 * Expected Auxiliary Space: O(V).
 * 
 * Constraints:
 * 2 ≤ V ≤ 10^4
 * 1 ≤ E ≤ (N*(N-1))/2
 */

 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 
public class TopologicalSort {
     // Function to return list containing vertices in Topological order. 
     static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
         int[] indegree = new int[V]; // Array to store the indegree of vertices
 
         // Calculate the indegree of each vertex
         for (int u = 0; u < adj.size(); u++) {
             for (int v : adj.get(u)) {
                 indegree[v]++;
             }
         }
 
         Queue<Integer> queue = new LinkedList<>();
         // Add vertices with indegree 0 to the queue
         for (int i = 0; i < V; i++) {
             if (indegree[i] == 0) {
                 queue.offer(i);
             }
         }
 
         ArrayList<Integer> res = new ArrayList<>();
         while (!queue.isEmpty()) {
             int node = queue.poll();
             res.add(node);
             for (int neighbour : adj.get(node)) {
                 indegree[neighbour]--;
                 if (indegree[neighbour] == 0) {
                     queue.offer(neighbour);
                 }
             }
         }
 
         int[] ans = new int[V];
         for (int i = 0; i < V; i++) {
             ans[i] = res.get(i);
         }
 
         return ans;
     }
 
     public static void main(String[] args) {
         // Example 1
         int V1 = 4;
         ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
         for (int i = 0; i < V1; i++) {
             adj1.add(new ArrayList<>());
         }
         adj1.get(1).add(0);
         adj1.get(2).add(0);
         adj1.get(3).add(1);
         adj1.get(3).add(2);
         int[] result1 = topoSort(V1, adj1);
         for (int val : result1) {
             System.out.print(val + " ");
         }
         System.out.println();
 
         // Example 2
         int V2 = 6;
         ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
         for (int i = 0; i < V2; i++) {
             adj2.add(new ArrayList<>());
         }
         adj2.get(1).add(0);
         adj2.get(2).add(0);
         adj2.get(3).add(1);
         adj2.get(3).add(2);
         adj2.get(4).add(3);
         adj2.get(5).add(4);
         int[] result2 = topoSort(V2, adj2);
         for (int val : result2) {
             System.out.print(val + " ");
         }
     }
 }
 