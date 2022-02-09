package leetcode.from2001to2050;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/2/9 9:59
 * @Description: 2006. 差的绝对值为 K 的数对数目 | 难度：简单 | 标签：数组、哈希表、计数
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 * <p>
 * |x| 的值定义为：
 * <p>
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * <p>
 * 示例 2：
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * <p>
 * 示例 3：
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution2006 {
    @Test
    public void countKDifferenceTest() {
        Assert.assertEquals(4, countKDifference(new int[]{1, 2, 2, 1}, 1));
        Assert.assertEquals(0, countKDifference(new int[]{1, 3}, 3));
        Assert.assertEquals(3, countKDifference(new int[]{3, 2, 1, 5, 4}, 2));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 79.08% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 7.22% 的用户
     * 通过测试用例： 237 / 237
     * <p>
     * 哈希表应该复杂度低一点
     *
     * @param nums
     * @param k
     * @return
     */
    public int countKDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0;
        int r = 1;
        while (r < nums.length && nums[r] == nums[r - 1]) {
            r++;
        }
        int count = 0;
        while (r < nums.length) {
            int abs = Math.abs(nums[l] - nums[r]);
            if (abs == k) {
                int countL = 0;
                int countR = 0;
                do {
                    l++;
                    countL++;
                } while (l < nums.length && nums[l] == nums[l - 1]);
                do {
                    r++;
                    countR++;
                } while (r < nums.length && nums[r] == nums[r - 1]);
                count += countL * countR;
            } else if (abs > k) {
                do {
                    l++;
                } while (l < nums.length && nums[l] == nums[l - 1]);
                if (l == r) {
                    do {
                        r++;
                    } while (r < nums.length && nums[r] == nums[r - 1]);
                }
            } else {
                do {
                    r++;
                } while (r < nums.length && nums[r] == nums[r - 1]);
            }
        }
        return count;
    }
}
