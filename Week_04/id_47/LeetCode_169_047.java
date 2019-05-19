/**
 * https://leetcode.com/problems/majority-element/
 * 169. Majority Element
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊
 * n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 */
class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 0;
        for(int t : nums) {
            if(t == res) {
                count +=1;
            }
            else if(count > 0) {
                count -= 1;
            }
            else {
                count = 1;
                res = t;
            }
        }
        return res;
    }
}