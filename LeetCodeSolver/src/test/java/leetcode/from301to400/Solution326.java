package leetcode.from301to400;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2021/9/23 9:45
 * @Description: 326. 3的幂 | 难度：简单 | 标签：递归、数学
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 * <p>
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：n = 45
 * 输出：false
 * <p>
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * <p>
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution326 {
    private static final Set<Integer> set = new HashSet<>();

    static {
        set.add(1);
        set.add(3);
        set.add(9);
        set.add(27);
        set.add(81);
        set.add(243);
        set.add(729);
        set.add(2187);
        set.add(6561);
        set.add(19683);
        set.add(59049);
        set.add(177147);
        set.add(531441);
        set.add(1594323);
        set.add(4782969);
        set.add(14348907);
        set.add(43046721);
        set.add(129140163);
        set.add(387420489);
        set.add(1162261467);
    }

    /**
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 38.07% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 12.93% 的用户
     * 通过测试用例： 21038 / 21038
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return n > 0 && set.contains(n);
        /// 题解答案：
//        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        for (int i = 1; i > 0; i *= 3) {
            System.out.print("set.add(");
            System.out.print(i);
            System.out.println(");");
        }
    }
}
