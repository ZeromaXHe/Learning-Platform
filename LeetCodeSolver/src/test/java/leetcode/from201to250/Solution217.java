package leetcode.from201to250;

import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/13 0:26
 * @Description: 217.存在重复元素 | 难度：简单 | 标签：数组、哈希表
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution217 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 55.15% 的用户
     * 内存消耗： 44.7 MB , 在所有 Java 提交中击败了 35.31% 的用户
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }
}
