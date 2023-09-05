package leetcode.from2601to2650;

/**
 * @author zhuxi
 * @apiNote 2605. 从两个数字数组里生成最小数字 | 难度：简单 | 标签：数组、哈希表、枚举
 * 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 * <p>
 * 示例 1：
 * 输入：nums1 = [4,1,3], nums2 = [5,7]
 * 输出：15
 * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
 * <p>
 * 示例 2：
 * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
 * 输出：3
 * 解释：数字 3 的数位 3 在两个数组中都出现了。
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * 每个数组中，元素 互不相同 。
 * @implNote
 * @since 2023/9/5 9:56
 */
public class Solution2605 {
    /**
     * 时间 1 ms
     * 击败 65.22% 使用 Java 的用户
     * 内存 38.55 MB
     * 击败 36.09% 使用 Java 的用户
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int minNumber(int[] nums1, int[] nums2) {
        int min1 = 10;
        boolean[] exist1 = new boolean[10];
        for (int num : nums1) {
            min1 = Math.min(min1, num);
            exist1[num] = true;
        }
        int min2 = 10;
        int minSame = 10;
        for (int num : nums2) {
            min2 = Math.min(min2, num);
            if (exist1[num]) {
                minSame = Math.min(minSame, num);
            }
        }
        if (minSame < 10) {
            return minSame;
        } else if (min1 < min2) {
            return min1 * 10 + min2;
        } else {
            return min2 * 10 + min1;
        }
    }
}
