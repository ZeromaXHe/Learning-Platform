package leetcode.from101to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 13:41
 * @Description: 136. 只出现一次的数字 | 难度：简单 | 标签：位运算、数组
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution136 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 83.43% 的用户
     * 通过测试用例： 61 / 61
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int test = 0;
        for (int num : nums) {
            test ^= num;
        }
        return test;
    }
}
