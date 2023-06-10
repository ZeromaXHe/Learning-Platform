package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 53 - I. 在排序数组中查找数字 I | 难度：简单 | 标签：数组、二分查找
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 15:05
 */
public class SolutionOffer53_I {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了 80.46% 的用户
     * 通过测试用例：88 / 88
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        if (leftIdx == -1) {
            return 0;
        }
        int rightIdx = binarySearch(nums, target, false);
        return rightIdx - leftIdx + 1;
    }

    private int binarySearch(int[] nums, int target, boolean left) {
        int l = 0;
        int r = nums.length - 1;
        int result = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (left) {
                result = mid;
                r = mid - 1;
            } else {
                result = mid;
                l = mid + 1;
            }
        }
        return result;
    }
}
