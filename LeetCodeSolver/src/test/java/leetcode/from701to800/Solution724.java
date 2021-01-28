package leetcode.from701to800;

/**
 * @Author: zhuxi
 * @Time: 2021/1/28 11:12
 * @Description: 724.寻找数组的中心索引 | 难度：简单 | 标签：数组
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * <p>
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例 1：
 * 输入：
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例 2：
 * 输入：
 * nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心索引。
 * <p>
 * 说明：
 * nums 的长度范围为 [0, 10000]。
 * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution724 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB, 在所有 Java 提交中击败了 36.15% 的用户
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        int sumLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum - sumLeft - nums[i] == sumLeft) {
                return i;
            }
            sumLeft += nums[i];
        }
        return -1;
    }
}
