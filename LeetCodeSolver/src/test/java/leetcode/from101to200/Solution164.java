package leetcode.from101to200;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/26 20:26
 * @Description: 164.最大间距 | 难度：困难 | 标签：排序
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * <p>
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * <p>
 * 示例 2:
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 * <p>
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution164 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 99.72% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了 75.66% 的用户
     *
     * nlogn解决的，不过评论有人提到了：
     * ”我觉得和数据集的大小有关系 中间On的遍历次数太多了 可能需要一个相当大的测试集才能让nlogn > kn“
     * ”一般的评测机很难卡O(nlogn)，因为32位整数logn最多也就32，和常数没什么区别“
     * 感觉说的还是很中肯的。题解的桶方法，操作过程多了，导致常数项很大，反而慢了
     *
     * 所以说复杂度也只是一个衡量工具，真正应用还是要综合真正的量级来考虑（练习算法就另说了）
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > max) {
                max = nums[i] - nums[i - 1];
            }
        }
        return max;
    }
}
