package leetcode.from1351to1400;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 1375. 二进制字符串前缀一致的次数 | 难度：中等 | 标签：数组
 * 给你一个长度为 n 、下标从 1 开始的二进制字符串，所有位最开始都是 0 。我们会按步翻转该二进制字符串的所有位（即，将 0 变为 1）。
 * <p>
 * 给你一个下标从 1 开始的整数数组 flips ，其中 flips[i] 表示对应下标 i 的位将会在第 i 步翻转。
 * <p>
 * 二进制字符串 前缀一致 需满足：在第 i 步之后，在 闭 区间 [1, i] 内的所有位都是 1 ，而其他位都是 0 。
 * <p>
 * 返回二进制字符串在翻转过程中 前缀一致 的次数。
 * <p>
 * 示例 1：
 * 输入：flips = [3,2,4,1,5]
 * 输出：2
 * 解释：二进制字符串最开始是 "00000" 。
 * 执行第 1 步：字符串变为 "00100" ，不属于前缀一致的情况。
 * 执行第 2 步：字符串变为 "01100" ，不属于前缀一致的情况。
 * 执行第 3 步：字符串变为 "01110" ，不属于前缀一致的情况。
 * 执行第 4 步：字符串变为 "11110" ，属于前缀一致的情况。
 * 执行第 5 步：字符串变为 "11111" ，属于前缀一致的情况。
 * 在翻转过程中，前缀一致的次数为 2 ，所以返回 2 。
 * <p>
 * 示例 2：
 * 输入：flips = [4,1,2,3]
 * 输出：1
 * 解释：二进制字符串最开始是 "0000" 。
 * 执行第 1 步：字符串变为 "0001" ，不属于前缀一致的情况。
 * 执行第 2 步：字符串变为 "1001" ，不属于前缀一致的情况。
 * 执行第 3 步：字符串变为 "1101" ，不属于前缀一致的情况。
 * 执行第 4 步：字符串变为 "1111" ，属于前缀一致的情况。
 * 在翻转过程中，前缀一致的次数为 1 ，所以返回 1 。
 * <p>
 * 提示：
 * n == flips.length
 * 1 <= n <= 5 * 104
 * flips 是范围 [1, n] 中所有整数构成的一个排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/14 9:53
 */
public class Solution1375 {
    @Test
    public void testNumTimesAllBlue() {
        Assert.assertEquals(2, numTimesAllBlue(new int[]{3, 2, 4, 1, 5}));
        Assert.assertEquals(1, numTimesAllBlue(new int[]{4, 1, 2, 3}));
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 92.00% 的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了 95.00% 的用户
     * 通过测试用例：63 / 63
     *
     * @param flips
     * @return
     */
    public int numTimesAllBlue(int[] flips) {
        int maxFlip = 0;
        int count = 0;
        for (int i = 0; i < flips.length; i++) {
            maxFlip = Math.max(flips[i] - 1, maxFlip);
            if (i == maxFlip) {
                count++;
            }
        }
        return count;
    }
}
