package leetcode.jianzhi_offer;

import java.util.HashSet;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/21 11:59
 * @Description: 剑指 Offer 03. 数组中重复的数字 | 难度：简单 | 标签：数组、哈希表
 * 找出数组中重复的数字。
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * 限制：
 * 2 <= n <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class SolutionOffer03 {
    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 31.35% 的用户
     * 内存消耗： 47.2 MB , 在所有 Java 提交中击败了 40.46% 的用户
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            } else {
                set.add(i);
            }
        }
        return -1;
    }
}
