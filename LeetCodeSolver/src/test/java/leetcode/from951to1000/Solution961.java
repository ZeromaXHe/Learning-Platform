package leetcode.from951to1000;

import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/21 13:10
 * @Description: 961. 在长度 2N 的数组中找出重复 N 次的元素 | 难度：简单 | 标签：数组、哈希表
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * <p>
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,3]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 * <p>
 * 提示：
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution961 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.1 MB , 在所有 Java 提交中击败了 38.84% 的用户
     * 通过测试用例： 102 / 102
     *
     * @param nums
     * @return
     */
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }
}
