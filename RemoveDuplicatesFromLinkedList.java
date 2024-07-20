/**
 * Remove duplicates from an unsorted linked list
 * Difficulty: Easy
 * Accuracy: 45.95%
 * Submissions: 229K+
 * Points: 2
 * 
 * Given an unsorted linked list of N nodes. The task is to remove duplicate elements from this unsorted Linked List. When a value appears in multiple nodes, the node which appeared first should be kept, all others duplicates are to be removed.
 * 
 * Example 1:
 * 
 * Input:
 * N = 4
 * value[] = {5, 2, 2, 4}
 * Output: 5 2 4
 * Explanation: Given linked list elements are
 * 5->2->2->4, in which 2 is repeated only.
 * So, we will delete the extra repeated
 * elements 2 from the linked list and the
 * resultant linked list will contain 5->2->4.
 * 
 * Example 2:
 * 
 * Input:
 * N = 5
 * value[] = {2, 2, 2, 2, 2}
 * Output: 2
 * Explanation: Given linked list elements are
 * 2->2->2->2->2, in which 2 is repeated. So,
 * we will delete the extra repeated elements
 * 2 from the linked list and the resultant
 * linked list will contain only 2.
 * 
 * Your Task:
 * You have to complete the method removeDuplicates() which takes 1 argument: the head of the linked list. Your function should return a pointer to a linked list with no duplicate element.
 * 
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(N)
 * 
 * Constraints:
 * 1 <= size of linked lists <= 106
 * 0 <= numbers in list <= 104
 */

 import java.util.HashSet;

 class Node {
     int data;
     Node next;
 
     Node(int data) {
         this.data = data;
         this.next = null;
     }
 }
 
 public class RemoveDuplicatesFromLinkedList {
     
     public static Node removeDuplicates(Node head) {
         if (head == null) {
             return null;
         }
 
         HashSet<Integer> seen = new HashSet<>();
         Node current = head;
         Node prev = null;
 
         while (current != null) {
             if (seen.contains(current.data)) {
                 // Duplicate found, skip the node
                 prev.next = current.next;
             } else {
                 // First occurrence, add to the seen set
                 seen.add(current.data);
                 prev = current;
             }
             current = current.next;
         }
 
         return head;
     }
 
     // Utility function to print the linked list
     public static void printList(Node head) {
         Node temp = head;
         while (temp != null) {
             System.out.print(temp.data + " ");
             temp = temp.next;
         }
         System.out.println();
     }
 
     public static void main(String[] args) {
         Node head = new Node(5);
         head.next = new Node(2);
         head.next.next = new Node(2);
         head.next.next.next = new Node(4);
 
         System.out.println("Original List:");
         printList(head);
 
         head = removeDuplicates(head);
 
         System.out.println("List after removing duplicates:");
         printList(head);
     }
 }
 