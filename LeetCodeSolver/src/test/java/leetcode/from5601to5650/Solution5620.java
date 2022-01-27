package leetcode.from5601to5650;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/6 10:44
 * @Description: 5620. 连接连续二进制数字
 * 给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：1
 * 解释：二进制的 "1" 对应着十进制的 1 。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：27
 * 解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
 * 将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
 *
 * 示例 3：
 * 输入：n = 12
 * 输出：505379714
 * 解释：连接结果为 "1101110010111011110001001101010111100" 。
 * 对应的十进制数字为 118505380540 。
 * 对 109 + 7 取余后，结果为 505379714 。
 *
 * 提示：
 * 1 <= n <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/concatenation-of-consecutive-binary-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5620 {
    /**
     * 403 / 403 个通过测试用例
     * 状态：通过
     * 执行用时: 2489 ms
     * 内存消耗: 38.2 MB
     * 这执行用时也行……
     *
     * @param n
     * @return
     */
    public int concatenatedBinary(int n) {
        long result = 0;
        int limit = 1;
        for (int i = 0; i < 9; i++) {
            limit *= 10;
        }
        limit += 7;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                stack.push(temp % 2);
                temp >>= 1;
            }
            while(!stack.isEmpty()){
                result <<= 1;
                result += stack.pop();
                if (result > limit) {
                    result %= limit;
                }
            }
        }
        return (int) result;
    }

    @Test
    public void concatenatedBinaryTest() {
        System.out.println(concatenatedBinary(3));
    }
}
