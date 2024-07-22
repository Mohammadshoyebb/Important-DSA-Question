// 543. Diameter of Binary Tree
// Easy
// Topics
// Companies
// Given the root of a binary tree, return the length of the diameter of the tree.

// The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

// The length of a path between two nodes is represented by the number of edges between them.

// Example 1:

// Input: root = [1,2,3,4,5]
// Output: 3
// Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

// Example 2:

// Input: root = [1,2]
// Output: 1

// Constraints:

// The number of nodes in the tree is in the range [1, 104].
// -100 <= Node.val <= 100

import java.util.*;

public class DiameterOfBinaryTree {
    
    public int diameterOfBinaryTree(TreeNode root) {
        // we are making maximum value pass by reference;
        int maximum[] = new int[1];
        findDiameter(root, maximum);
        return maximum[0];
    }
    
    public int findDiameter(TreeNode root, int maximum[]) {
        if (root == null) {
            return 0;
        }
        int left = findDiameter(root.left, maximum);
        int right = findDiameter(root.right, maximum);

        maximum[0] = Math.max(maximum[0], (left + right));

        return (1 + Math.max(left, right));
    }

    public static void main(String[] args) {
        // Example usage:
        // Create a sample tree: 
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        System.out.println("Diameter of the tree: " + solution.diameterOfBinaryTree(root)); // Output: 3
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

