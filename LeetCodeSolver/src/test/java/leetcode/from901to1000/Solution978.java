package leetcode.from901to1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/2/8 9:57
 * @Description: 978.最长湍流子数组 | 难度：中等 | 标签：数组、动态规划、滑动窗口
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * <p>
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：[100]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution978 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 97.24% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 64.53% 的用户
     *
     * @param arr
     * @return
     */
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }
        int max = 0;
        // 前面大为1，后面大为-1，相等为0
        int former = Integer.compare(arr[0], arr[1]);
        int count = former == 0 ? 1 : 2;
        for (int i = 2; i < arr.length; i++) {
            int now = Integer.compare(arr[i - 1], arr[i]);
            if (now == 0) {
                if (count > max) {
                    max = count;
                }
                count = 1;
            } else if (former == now) {
                if (count > max) {
                    max = count;
                }
                count = 2;
            } else {
                count++;
            }
            former = now;
        }
        return Math.max(count, max);
    }

    @Test
    public void maxTurbulenceSizeTest() {
        Assert.assertEquals(5, maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        Assert.assertEquals(2, maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        Assert.assertEquals(1, maxTurbulenceSize(new int[]{100}));
        Assert.assertEquals(1, maxTurbulenceSize(new int[]{9, 9}));
    }
}