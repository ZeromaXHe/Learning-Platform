package leetcode.jianzhi_offer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 59 - I. 滑动窗口的最大值 | 难度：困难 | 标签：队列、滑动窗口、单调队列、堆（优先队列）
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组 不为空 的情况下，1 ≤ k ≤ nums.length。
 * <p>
 * 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 11:15
 */
public class SolutionOffer59_I {
    /**
     * 参考题解做的，感觉没有完全理解，下次估计还是想不到
     * <p>
     * 执行用时：28 ms, 在所有 Java 提交中击败了 74.02% 的用户
     * 内存消耗：57.8 MB, 在所有 Java 提交中击败了 68.82% 的用户
     * 通过测试用例：51 / 51
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] result = new int[nums.length - k + 1];
        result[0] = nums[deque.peekFirst()];
        for (int i = 1; i < result.length; i++) {
            while (!deque.isEmpty() && nums[k + i - 1] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(k + i - 1);
            while (deque.peekFirst() < i) {
                deque.pollFirst();
            }
            result[i] = nums[deque.peekFirst()];
        }
        return result;
    }

    /**
     * 执行用时：117 ms, 在所有 Java 提交中击败了 5.02% 的用户
     * 内存消耗：57.7 MB, 在所有 Java 提交中击败了 72.36% 的用户
     * 通过测试用例：51 / 51
     * <p>
     * 用 int[]{值, 索引} 的优先队列的话：
     * 执行用时：115 ms, 在所有 Java 提交中击败了 5.02% 的用户
     * 内存消耗：60.3 MB, 在所有 Java 提交中击败了 13.51% 的用户
     * 通过测试用例：51 / 51
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow_pq(int[] nums, int k) {
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt((Integer i) -> nums[i]).reversed());
        for (int i = 0; i < k; i++) {
            pq.offer(i);
        }
        int[] result = new int[nums.length - k + 1];
        result[0] = nums[pq.peek()];
        for (int i = 1; i < result.length; i++) {
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            pq.offer(k + i - 1);
            result[i] = nums[pq.peek()];
        }
        return result;
    }
}
