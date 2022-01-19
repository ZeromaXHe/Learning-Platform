package leetcode.from101to200;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 16:18
 * @Description: 119. 杨辉三角 II | 难度：简单 | 标签：数组、动态规划
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * <p>
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 * <p>
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * <p>
 * 提示:
 * 0 <= rowIndex <= 33
 * <p>
 * 进阶：
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution119 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 90.57% 的用户
     * 通过测试用例： 34 / 34
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex + 1);
        result.add(1);
        long mFactorial = 1;
        long mToNProduct = 1;
        for (int i = 1; i <= rowIndex; i++) {
            // C(n,m)=n!/[m!(n-m)!]
            if (i > rowIndex / 2) {
                result.add(result.get(rowIndex - i));
            } else {
                // 因为每次计算结果都是整数，所以其实这里的 mFactorial 和 mToNProduct 处理成一个变量，这样也不需要引入 gcd 方法了
                mFactorial *= i;
                mToNProduct *= rowIndex + 1 - i;
                result.add((int) (mToNProduct / mFactorial));
                long gcd = gcd(mFactorial, mToNProduct);
                mFactorial /= gcd;
                mToNProduct /= gcd;
            }
        }
        return result;
    }

    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b > 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    @Test
    public void gcdTest() {
        Assert.assertEquals(2, gcd(4, 2));
        Assert.assertEquals(1, gcd(7, 13));
    }
}
