package leetcode.from5701to5750;

import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/28 10:36
 * @Description: 5715. 还原排列的最少操作步数 | 难度：中等 | 标签：贪心算法、数组
 * 给你一个偶数 n​​​​​​ ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i​（下标 从 0 开始 计数）。
 * <p>
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * <p>
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr​​ 赋值​​给 perm 。
 * <p>
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：最初，perm = [0,1]
 * 第 1 步操作后，perm = [0,1]
 * 所以，仅需执行 1 步操作
 * <p>
 * 示例 2：
 * 输入：n = 4
 * 输出：2
 * 解释：最初，perm = [0,1,2,3]
 * 第 1 步操作后，perm = [0,2,1,3]
 * 第 2 步操作后，perm = [0,1,2,3]
 * 所以，仅需执行 2 步操作
 * <p>
 * 示例 3：
 * 输入：n = 6
 * 输出：4
 * <p>
 * 提示：
 * 2 <= n <= 1000
 * n​是一个偶数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5715 {
    /**
     * 66 / 66 个通过测试用例
     * 状态：通过
     * 执行用时: 30 ms
     * 内存消耗: 35.2 MB
     *
     * @param n
     * @return
     */
    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        int[] arr = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        do {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    arr[i] = perm[i / 2];
                } else {
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                }
            }
            int[] temp = perm;
            perm = arr;
            arr = temp;
            count++;
        } while (!isEquals(perm));
        return count;
    }

    private boolean isEquals(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i != arr[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void reinitializePermutationTest() {
        System.out.println(reinitializePermutation(2));
        System.out.println(reinitializePermutation(4));
        System.out.println(reinitializePermutation(6));
        System.out.println(reinitializePermutation(8));
        System.out.println(reinitializePermutation(10));
        System.out.println(reinitializePermutation(1000));
    }
}
