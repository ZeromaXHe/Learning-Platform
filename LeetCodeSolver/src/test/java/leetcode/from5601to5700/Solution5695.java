package leetcode.from5601to5700;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/20 22:48
 * @Description: 5695.N次操作后的最大分数和 | 难度：困难 | 标签：递归、动态规划、回溯算法
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
 * <p>
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 * <p>
 * 选择两个元素 x 和 y 。
 * 获得分数 i * gcd(x, y) 。
 * 将 x 和 y 从 nums 中删除。
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 * <p>
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最优操作是：
 * (1 * gcd(1, 2)) = 1
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,6,8]
 * 输出：11
 * 解释：最优操作是：
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 * <p>
 * 提示：
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-score-after-n-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5695 {
    /**
     * 未完成
     *
     * @param nums
     * @return
     */
    public int maxScore(int[] nums) {
        // Arrays.sort(nums);
        int n = nums.length;
        boolean[] selected = new boolean[n];
        int[][] cache = new int[n][n];
        for (int i = 0; i < n; i++) {
            cache[i][i] = i;
            for (int j = i + 1; j < n; j++) {
                cache[j][i] = cache[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] indexs = new int[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        // 感觉估计写个回溯全遍历好些，但我没有时间了。
        // 最多 14! / 7! / 2^7 = 1 * 3 * 5 * 7 * 9 * 11 * 13 = 135135 种可能
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                int i1 = indexs[i * 2];
                int i2 = indexs[i * 2 + 1];
                int j1 = indexs[j * 2];
                int j2 = indexs[j * 2 + 1];
                int i1toBe = i1;
                int i2toBe = i2;
                int j1toBe = j1;
                int j2toBe = j2;
                int max = i * cache[i1][i2] + j * cache[j1][j2];
                if (i * cache[i1][j2] + j * cache[i2][j1] > max) {
                    max = i * cache[i1][j2] + j * cache[i2][j1];
                    i2toBe = j2;
                    j1toBe = i2;
                }
                indexs[i * 2] = i1toBe;
                indexs[i * 2 + 1] = i2toBe;
                indexs[j * 2] = j1toBe;
                indexs[j * 2 + 1] = j2toBe;
            }
        }
        int sum = 0;
        return sum;
    }

    public int maxScore_wrong(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] selected = new boolean[n];
        int[][] cache = new int[n][n];
        for (int i = 0; i < n; i++) {
            cache[i][i] = i;
            for (int j = i + 1; j < n; j++) {
                cache[j][i] = cache[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int count = n / 2;
        int sum = 0;
        for (int i = n - 1; i >= 1; i--) {
            if (selected[i]) {
                continue;
            }
            int max = 0;
            int select = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < max) {
                    break;
                }
                if (selected[j]) {
                    continue;
                }
                if (cache[j][i] > max) {
                    max = cache[j][i];
                    select = j;
                }
                for (int k = j - 1; k >= 0; k--) {
                    if (selected[k]) {
                        continue;
                    }
                    if (cache[k][j] > max) {
                        max = cache[k][j];
                        select = k;
                    }
                }
            }
            System.out.println("选择了[" + i + ", " + select + "]");
            selected[select] = true;
            sum += count * max;
            System.out.println("sum:" + sum + " count:" + count + " max:" + max);
            count--;
        }
        return sum;
    }

    private int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    @Test
    public void maxScoreTest() {
        // 预期：869
        System.out.println(maxScore(new int[]{697035, 181412, 384958, 575458}));
        System.out.println(gcd(575458, 697035));
        System.out.println("" + (2 * gcd(575458, 697035) + gcd(181412, 384958)));
    }
}
