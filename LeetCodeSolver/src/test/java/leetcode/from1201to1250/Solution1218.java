package leetcode.from1201to1250;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/11/5 9:50
 * @Description: 1218. 最长定差子序列 | 难度：中等 | 标签：数组、哈希表、动态规划
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * <p>
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * <p>
 * 示例 1：
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * <p>
 * 示例 2：
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * <p>
 * 示例 3：
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 * <p>
 * 提示：
 * 1 <= arr.length <= 105
 * -104 <= arr[i], difference <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1218 {
    /**
     * 执行用时： 36 ms , 在所有 Java 提交中击败了 78.17% 的用户
     * 内存消耗： 55.4 MB , 在所有 Java 提交中击败了 37.06% 的用户
     * 通过测试用例： 39 / 39
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int result = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int val = map.getOrDefault(num - difference, 0) + 1;
            map.put(num, val);
            if (val > result) {
                result = val;
            }
        }
        return result;
    }
}
