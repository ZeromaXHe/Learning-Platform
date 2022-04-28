package leetcode.from901to950;

/**
 * @Author: zhuxi
 * @Time: 2022/4/28 9:47
 * @Description: 905. 按奇偶排序数组 | 难度：简单 | 标签：数组、双指针、排序
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * <p>
 * 返回满足此条件的 任一数组 作为答案。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution905 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 70.29% 的用户
     * 内存消耗： 42.3 MB , 在所有 Java 提交中击败了 33.16% 的用户
     * 通过测试用例： 285 / 285
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            while (l < nums.length && nums[l] % 2 == 0) {
                l++;
            }
            while (r > l && nums[r] % 2 == 1) {
                r--;
            }
            if (r > l) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        return nums;
    }
}
