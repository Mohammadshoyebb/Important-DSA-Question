import java.util.*;

/**
 * LeetCode Problem 49: Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

    // Efficient approach using sorted strings as keys
    public List<List<String>> groupAnagramsUsingSorting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            
            map.get(sortedStr).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // using character count arrays as keys(O(N))
    public List<List<String>> groupAnagramsUsingCount(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i : count) {
                sb.append('#');
                sb.append(i);
            }
            
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }

    // Main method to test both functions
    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        // Test case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Using Sorting:");
        System.out.println(solution.groupAnagramsUsingSorting(strs1));
        System.out.println("Using Character Count:");
        System.out.println(solution.groupAnagramsUsingCount(strs1));
        
        // Test case 2
        String[] strs2 = {""};
        System.out.println("Using Sorting:");
        System.out.println(solution.groupAnagramsUsingSorting(strs2));
        System.out.println("Using Character Count:");
        System.out.println(solution.groupAnagramsUsingCount(strs2));
        
        // Test case 3
        String[] strs3 = {"a"};
        System.out.println("Using Sorting:");
        System.out.println(solution.groupAnagramsUsingSorting(strs3));
        System.out.println("Using Character Count:");
        System.out.println(solution.groupAnagramsUsingCount(strs3));
    }
}

//Slower Approach

/*
 * class Solution {
public List<List> groupAnagrams(String[] strs) {
HashMap<HashMap<Character,Integer>,ArrayList> bigMap = new HashMap<>();

    for(String str: strs){
        HashMap<Character,Integer> fMap = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            fMap.put(ch, fMap.getOrDefault(ch, 0)+1);
        }
        if(bigMap.containsKey(fMap) == false){
            ArrayList<String> list = new ArrayList<>();
            list.add(str);
            bigMap.put(fMap, list);
        }
        else{
            ArrayList<String> list = bigMap.get(fMap);
            list.add(str);
        }
    }
    List<List<String>> result = new ArrayList<>();

    for(List<String> val : bigMap.values()){
        result.add(val);
    }
    return result;
}
}
 */
