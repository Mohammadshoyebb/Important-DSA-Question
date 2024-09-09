/**
 * 1367. Linked List in Binary Tree
 *
 * Given a binary tree root and a linked list with head as the first node, 
 * return true if all the elements in the linked list starting from the head 
 * correspond to some downward path connected in the binary tree, otherwise return false.
 *
 * In this context, a downward path means a path that starts at some node and goes downwards.
 *
 * Example 1:
 * Input: head = [4, 2, 8], root = [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
 * Output: true
 * Explanation: Nodes in blue form a subpath in the binary Tree.
 *
 * Example 2:
 * Input: head = [1, 4, 2, 6], root = [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
 * Output: true
 *
 * Example 3:
 * Input: head = [1, 4, 2, 6, 8], root = [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the tree will be in the range [1, 2500].
 * - The number of nodes in the list will be in the range [1, 100].
 * - 1 <= Node.val <= 100 for each node in the linked list and binary tree.
 */

 public class LinkedListInBinaryTree {
    
    public boolean dfs(ListNode head, TreeNode node){
        if(head == null){
            return true;
        }
        if(node == null){
            return false;
        }
        if(head.val != node.val){
            return false;
        }

        return dfs(head.next, node.left) || dfs(head.next, node.right);
    }
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null){
            return false;
        }
        if(head.val == root.val){
            if(dfs(head, root)){
                return true;
            }
        }

        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Definition for a binary tree node.
    public static class TreeNode {
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

    // Main method to test the code
    public static void main(String[] args) {
        // Example 1
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(8)));
        TreeNode root1 = new TreeNode(1, 
                            new TreeNode(4, 
                                new TreeNode(2, null, new TreeNode(8)), 
                                null), 
                            new TreeNode(4));
        LinkedListInBinaryTree obj = new LinkedListInBinaryTree();
        boolean result1 = obj.isSubPath(head1, root1);
        System.out.println("Example 1 Result: " + result1); // Output: true

        // Example 2
        ListNode head2 = new ListNode(1, new ListNode(4, new ListNode(2, new ListNode(6))));
        TreeNode root2 = new TreeNode(1, 
                            new TreeNode(4, 
                                new TreeNode(2, null, new TreeNode(6, new TreeNode(8), null)), 
                                null), 
                            new TreeNode(4));
        boolean result2 = obj.isSubPath(head2, root2);
        System.out.println("Example 2 Result: " + result2); // Output: true

        // Example 3
        ListNode head3 = new ListNode(1, new ListNode(4, new ListNode(2, new ListNode(6, new ListNode(8)))));
        TreeNode root3 = new TreeNode(1, 
                            new TreeNode(4, 
                                new TreeNode(2, null, new TreeNode(6, new TreeNode(8), null)), 
                                null), 
                            new TreeNode(4));
        boolean result3 = obj.isSubPath(head3, root3);
        System.out.println("Example 3 Result: " + result3); // Output: false
    }
}

// ======================================  faster Approach Using single dfs call ===========================

/*
  
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, head, root);
    }

    boolean dfs(ListNode head, ListNode cur, TreeNode root) {
        if(cur == null) return true;
        if(root == null) return false;
        if(cur.val == root.val) cur = cur.next;
        else if (head.val == root.val) head = head.next;
        else cur = head;
        return dfs(head, cur, root.left) || dfs(head, cur, root.right);
    }

}
    
 */