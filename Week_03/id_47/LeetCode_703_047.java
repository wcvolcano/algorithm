/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * 思路: 使用priorityQueue维护最小堆即可
 */
class KthLargest {

    private PriorityQueue<Integer> priorityQueue;
    private final int size;

    public KthLargest(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>(k + 1);
        size = k;
        int i = 0;
        for (; i < k && i < nums.length; ++i) {
            priorityQueue.add(nums[i]);
        }
        for (; i < nums.length; ++i) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < size) {
            priorityQueue.add(val);
            return priorityQueue.peek();
        }
        if (val >= priorityQueue.peek()) {
            priorityQueue.add(val);
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}
