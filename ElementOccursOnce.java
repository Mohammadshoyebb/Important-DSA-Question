/*
 * Find the element that appears once
Last Updated : 26 Jun, 2024
Given an array where every element occurs three times, except one element which occurs only once.
Find the element that occurs once.

Note: Expected time complexity is O(n) and O(1) extra space.

Examples:

Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3} 
Output: 2 
In the given array all element appear three times except 2 which appears once.

Input: arr[] = {10, 20, 10, 30, 10, 30, 30} 
Output: 20 
In the given array all element appear three times except 20 which appears once. 

Approach using Bitmask:

Use two variables, ones and twos, to track the bits that appear an odd and even number of times, 
respectively. In each iteration, XOR the current element with ones to update ones with the bits that appear 
an odd number of times then use a bitwise AND operation between ones and the current element to find the 
common bits that appear three times. These common bits are removed from both ones and twos using a 
bitwise AND operation with the negation of the common bits. Finally, ones contains the element that 
appears only once.
 */
public class ElementOccursOnce {
    // Method to find the element that occur only once
    static int getSingle(int arr[], int n)
    {
        int ones = 0, twos = 0;
        int common_bit_mask;

        for (int i = 0; i < n; i++) {
            /*"one & arr[i]" gives the bits that are there in
            both 'ones' and new element from arr[]. We
            add these bits to 'twos' using bitwise OR*/
            twos = twos | (ones & arr[i]);

            /*"one & arr[i]" gives the bits that are
            there in both 'ones' and new element from arr[].
            We add these bits to 'twos' using bitwise OR*/
            ones = ones ^ arr[i];

            /* The common bits are those bits which appear third time
            So these bits should not be there in both 'ones' and 'twos'.
            common_bit_mask contains all these bits as 0, so that the bits can 
            be removed from 'ones' and 'twos'*/
            common_bit_mask = ~(ones & twos);

            /*Remove common bits (the bits that appear third time) from 'ones'*/
            ones &= common_bit_mask;

            /*Remove common bits (the bits that appear third time) from 'twos'*/
            twos &= common_bit_mask;
        }
        return ones;
    }

    // Driver method
    public static void main(String args[])
    {
        int arr[] = { 3, 3, 2, 3 };
        int n = arr.length;
        System.out.println("The element with single occurrence is " + getSingle(arr, n));
    }
}


/*
  public class GFG {
    // Method to find the element that occurs only once
    static int getSingle(int arr[], int n) {
        int ones = 0, twos = 0;
        for (int i = 0; i < n; i++) {
            // Update twos with bits that are common in ones and the new element
            twos |= (ones & arr[i]);
            // XOR ones with the new element
            ones ^= arr[i];
            // Common bits in ones and twos
            int common_bit_mask = ~(ones & twos);
            // Remove common bits from ones and twos
            ones &= common_bit_mask;
            twos &= common_bit_mask;
        }
        return ones; // The unique element
    }

    // Driver method
    public static void main(String args[]) {
        int arr[] = { 3, 3, 2, 3 };
        int n = arr.length;
        System.out.println("The element with single occurrence is " + getSingle(arr, n));
    }
}

 */