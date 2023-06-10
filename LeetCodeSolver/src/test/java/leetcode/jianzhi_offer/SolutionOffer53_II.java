package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 53 - II. 0～n-1中缺失的数字 | 难度：简单 | 标签：位运算、数组、哈希表、数学、二分查找
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * 限制：
 * 1 <= 数组长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 15:22
 */
public class SolutionOffer53_II {
    /**
     * 数组是递增的…… 一下没注意，怪不得是简单题。不过这样就是慢点，无所谓了……
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了 6.51% 的用户
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了 9.08% 的用户
     * 通过测试用例：122 / 122
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        boolean exist = false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = nums[i] % (n + 1);
            if (index == n) {
                exist = true;
            } else {
                nums[index] += n + 1;
            }
        }
        if (!exist) {
            return n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] < n + 1) {
                return i;
            }
        }
        return -1;
    }
}
