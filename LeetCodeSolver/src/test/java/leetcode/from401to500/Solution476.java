package leetcode.from401to500;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/10/18 10:05
 * @Description: 476. 数字的补数 | 难度：简单 | 标签：位运算
 * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * 示例 1：
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * <p>
 * 示例 2：
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * <p>
 * 提示：
 * 给定的整数 num 保证在 32 位带符号整数的范围内。
 * num >= 1
 * 你可以假定二进制数不包含前导零位。
 * 本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-complement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution476 {
    @Test
    public void findComplementTest() {
        Assert.assertEquals(0, findComplement(1));
        Assert.assertEquals(2, findComplement(5));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 83.47% 的用户
     * 通过测试用例： 149 / 149
     *
     * @param num
     * @return
     */
    public int findComplement(int num) {
        int result = 0;
        int count = 0;
        while (num > 0) {
            result += (1 << count++) * ((num % 2) ^ 1);
            num >>= 1;
        }
        return result;
    }
}
