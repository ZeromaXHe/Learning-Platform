package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/14 10:14
 * @Description: 60. 排列序列 | 难度：困难 | 标签：属性、回溯算法
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 * <p>
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 * <p>
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution60 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.95% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 64.54% 的用户
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        char[] chars = new char[n];
        boolean[] exist = new boolean[n];
        int[] factorial = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        k--;
        for (int i = 0; i < n; i++) {
            int nextPossibility = factorial[n - 1 - i];
            int chosen = k / nextPossibility;
            for (int j = 0; j < n; j++) {
                if (!exist[j]) {
                    if (chosen == 0) {
                        chars[i] = (char) ('1' + j);
                        exist[j] = true;
                        break;
                    } else {
                        chosen--;
                    }
                }
            }
            k %= nextPossibility;
        }
        return new String(chars);
    }

    @Test
    public void getPermutationTest() {
        Assert.assertEquals("213", getPermutation(3, 3));
        Assert.assertEquals("2314", getPermutation(4, 9));
        Assert.assertEquals("123", getPermutation(3, 1));
    }
}
