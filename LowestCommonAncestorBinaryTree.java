/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: 
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 * 
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * 
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * 
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the tree.
 */

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
    }
}

public class LowestCommonAncestorBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode leftChild = lowestCommonAncestor(root.left, p, q);
        TreeNode rightChild = lowestCommonAncestor(root.right, p, q);

        if (leftChild == null) {
            return rightChild;
        } else if (rightChild == null) {
            return leftChild;
        } else {
            return root;
        }
    }
    
    public static void main(String[] args) {
        // Example usage and testing
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        
        TreeNode p1 = root.left;
        TreeNode q1 = root.right;
        
        TreeNode p2 = root.left;
        TreeNode q2 = root.left.right.right;
        
        TreeNode p3 = root;
        TreeNode q3 = root.left;
        
        LowestCommonAncestorBinaryTree solution = new LowestCommonAncestorBinaryTree();
        
        System.out.println("LCA of nodes " + p1.val + " and " + q1.val + ": " + solution.lowestCommonAncestor(root, p1, q1).val);
        System.out.println("LCA of nodes " + p2.val + " and " + q2.val + ": " + solution.lowestCommonAncestor(root, p2, q2).val);
        System.out.println("LCA of nodes " + p3.val + " and " + q3.val + ": " + solution.lowestCommonAncestor(root, p3, q3).val);
    }
}
