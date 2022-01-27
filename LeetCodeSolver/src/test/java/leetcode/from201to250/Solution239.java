package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/2 17:40
 * @Description: 239.滑动窗口最大值 | 难度：困难 | 标签：堆，滑动窗口
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 *  
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *  
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution239 {
    /**
     * 执行用时： 165 ms , 在所有 Java 提交中击败了 10.08% 的用户
     * 内存消耗： 55.5 MB , 在所有 Java 提交中击败了 14.52% 的用户
     * 效率很低，待优化
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k - 1; i++) {
            heap.offer(nums[i]);
        }
        int index = 0;
        while (index < result.length) {
            heap.offer(nums[index + k - 1]);
            int peek = heap.peek();
            while (map.containsKey(peek)) {
                if (map.get(peek) == 1) {
                    map.remove(peek);
                } else {
                    map.put(peek, map.get(peek) - 1);
                }
                heap.poll();
                peek = heap.peek();
            }
            result[index] = heap.peek();
            if (map.containsKey(nums[index])) {
                map.put(nums[index], map.get(nums[index]) + 1);
            } else {
                map.put(nums[index], 1);
            }
            index++;
        }
        return result;
    }

    @Test
    public void maxSlidingWindowTest() {
        Assert.assertArrayEquals(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[]{3, 3, 5, 5, 6, 7});
    }
}
