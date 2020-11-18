package leetcode.from201to300;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/19 0:17
 * @Description: 283. 移动零 | 难度：简单 | 标签：数组、双指针
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution283 {
    /**
     * 执行用时： 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.5 MB, 在所有 Java 提交中击败了 93.25% 的用户
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        int numIndex = 0;
        while (numIndex < nums.length) {
            if (nums[numIndex] == 0) {
                if (zeroIndex == -1) {
                    zeroIndex = numIndex;
                }
            } else {
                if (zeroIndex >= 0) {
                    nums[zeroIndex] = nums[numIndex];
                    nums[numIndex] = 0;
                    zeroIndex++;
                }
            }
            numIndex++;
        }
    }
}
