package leetcode.from301to400;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/12 22:02
 * @Description: 376.摆动序列 | 难度：中等 | 标签：贪心算法、动态规划
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * <p>
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * <p>
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * <p>
 * 示例 1:
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * <p>
 * 示例 2:
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * <p>
 * 示例 3:
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * <p>
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution376 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.1 MB , 在所有 Java 提交中击败了 47.11% 的用户
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
//        int[] diff = new int[nums.length - 1];
//        for (int i = 1; i < nums.length; i++) {
//            diff[i - 1] = nums[i] - nums[i - 1];
//        }
        int length = nums.length;
        if (length < 2) {
            return length;
        }
        int prevdiff = nums[1] - nums[0];
        // 开始两数差距为0就只记录1个数，否则两个数都算入最大记录
        int ret = prevdiff != 0 ? 2 : 1;
        // 每次向后计数，都记录不同方向的增长变化，每一次增长方向的变化对应一个最大摆动数列的元素数量
        for (int i = 2; i < length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }

}
