package leetcode.from501to600;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/6 12:05
 * @Description: 503.下一个更大元素II | 难度：中等 | 标签：栈
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution503 {
    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 92.76% 的用户
     * 内存消耗： 40.2 MB , 在所有 Java 提交中击败了 56.24% 的用户
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, -1);
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < len * 2 - 1; i++) {
            if (nums[i % len] > nums[(i - 1) % len]) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]) {
                    result[stack.pop()] = nums[i % len];
                }
            }
            stack.push(i % len);
        }
        return result;
    }

    @Test
    public void nextGreaterElementsTest() {
        Assert.assertArrayEquals(new int[]{2, -1, 2}, nextGreaterElements(new int[]{1, 2, 1}));
    }
}
