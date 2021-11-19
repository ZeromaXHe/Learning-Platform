package leetcode.from301to400;

/**
 * @Author: zhuxi
 * @Time: 2021/11/19 9:42
 * @Description: 397. 整数替换 | 难度：中等 | 标签：贪心、位运算、记忆化搜索、动态规划
 * 给定一个正整数 n ，你可以做如下操作：
 * <p>
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1：
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * <p>
 * 示例 2：
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：2
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution397 {
    /**
     * 参考题解。
     * 感觉位运算去理解这个思路确实更方便，大概就是奇数的二进制末尾如果有连续的1的话就+1，否则-1
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 62.45% 的用户
     * 通过测试用例： 47 / 47
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        long num = n;
        int ans = 0;
        while (num != 1) {
            if (num % 2 == 0) {
                num >>= 1;
            } else {
                if (num != 3 && ((num >> 1) & 1) == 1) num++;
                else num--;
            }
            ans++;
        }
        return ans;
    }
}
