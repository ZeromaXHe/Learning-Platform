package leetcode.from301to400;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/11/30 11:16
 * @Description: 400. 第 N 位数字 | 难度：中等 | 标签：数学、二分查找
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution400 {
    @Test
    public void findNthDigitTest() {
        Assert.assertEquals(3, findNthDigit(3));
        Assert.assertEquals(0, findNthDigit(11));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 79.58% 的用户
     * 通过测试用例： 71 / 71
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        long base = 9;
        int digit = 1;
        while (n > base * digit) {
            n -= base * digit;
            base *= 10;
            digit++;
        }
        int num = (n - 1) / digit;
        int count = (n - 1) % digit;
        if (count == 0) {
            return (int) (num / (base / 9) + 1);
        }
        int delete = digit - 1 - count;
        while (delete > 0) {
            num /= 10;
            delete--;
        }
        return num % 10;
    }
}
