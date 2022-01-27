package leetcode.from101to150;

import java.util.HashSet;

/**
 * @Author: zhuxi
 * @Time: 2021/1/22 11:28
 * @Description: 128.最长连续序列 | 难度：困难 | 标签：并查集、数组
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * 提示：
 * 0 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution128 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 82.11% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 40.50% 的用户
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int max = 0;
        for (int i : set) {
            if (!set.contains(i - 1)) {
                int count = 1;
                int num = i;
                while (set.contains(++num)) {
                    count++;
                }
                if (count > max) {
                    max = count;
                }
            }
        }
        return max;
    }
}
