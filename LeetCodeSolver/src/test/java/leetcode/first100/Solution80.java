package leetcode.first100;

/**
 * @Author: zhuxi
 * @Time: 2021/4/6 10:15
 * @Description: 80.删除有序数组中的重复项 II | 难度：中等 | 标签：数组、双指针
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 说明：
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 提示：
 * 0 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按升序排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution80 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.95% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 57.50% 的用户
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        int pre = Integer.MIN_VALUE;
        while (index2 < nums.length) {
            if (nums[index2] == pre) {
                pre = nums[index2];
                if (count < 2) {
                    nums[index1++] = nums[index2++];
                    count++;
                } else {
                    index2++;
                }
            } else {
                pre = nums[index2];
                nums[index1++] = nums[index2++];
                count = 1;
            }
        }
        return index1;
    }
}
