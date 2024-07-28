// 653. Two Sum IV - Input is a BST
// Easy
// Topics
// Companies
// Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
//
// Example 1:
//
// Input: root = [5,3,6,2,4,null,7], k = 9
// Output: true
// Example 2:
//
// Input: root = [5,3,6,2,4,null,7], k = 28
// Output: false
//
// Constraints:
//
// The number of nodes in the tree is in the range [1, 104].
// -104 <= Node.val <= 104
// root is guaranteed to be a valid binary search tree.
// -105 <= k <= 105

import java.util.ArrayList;
import java.util.HashSet;

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

public class TwoSumBST {
    
    // First approach: Using HashSet
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private boolean find(TreeNode root, int k, HashSet<Integer> set) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
    
    // Second approach: Using in-order traversal and two-pointer technique
    public boolean findTargetTwoPointer(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    private void inOrderTraversal(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, list);
        list.add(root.val);
        inOrderTraversal(root.right, list);
    }

    public static void main(String[] args) {
        TwoSumBST solution = new TwoSumBST();

        TreeNode root1 = new TreeNode(5, 
                                      new TreeNode(3, new TreeNode(2), new TreeNode(4)), 
                                      new TreeNode(6, null, new TreeNode(7)));
        System.out.println(solution.findTarget(root1, 9)); // true
        System.out.println(solution.findTargetTwoPointer(root1, 9)); // true

        TreeNode root2 = new TreeNode(5, 
                                      new TreeNode(3, new TreeNode(2), new TreeNode(4)), 
                                      new TreeNode(6, null, new TreeNode(7)));
        System.out.println(solution.findTarget(root2, 28)); // false
        System.out.println(solution.findTargetTwoPointer(root2, 28)); // false
    }
}

