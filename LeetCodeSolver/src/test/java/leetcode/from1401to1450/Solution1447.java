package leetcode.from1401to1450;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/2/10 9:52
 * @Description: 1447. 最简分数 | 难度：中等 | 标签：数学、字符串、数论
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * <p>
 * 示例 4：
 * 输入：n = 1
 * 输出：[]
 * <p>
 * 提示：
 * 1 <= n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplified-fractions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1447 {
    /**
     * 执行用时： 20 ms , 在所有 Java 提交中击败了 55.32% 的用户
     * 内存消耗： 42.1 MB , 在所有 Java 提交中击败了 14.54% 的用户
     * 通过测试用例： 100 / 100
     * <p>
     * 不使用int adder，直接j++的话：
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 84.75% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 5.32% 的用户
     * 通过测试用例： 100 / 100
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> result = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            int adder = i % 2 == 0 ? 2 : 1;
            for (int j = 1; j < i; j += adder) {
                if (gcd(i, j) == 1) {
                    result.add(j + "/" + i);
                }
            }
        }
        return result;
    }

    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}
