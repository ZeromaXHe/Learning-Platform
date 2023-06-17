package leetcode.from2451to2500;

/**
 * @author zhuxi
 * @apiNote 2481. 分割圆的最少切割次数 | 难度：简单 | 标签：几何、数学
 * 圆内一个 有效切割 ，符合以下二者之一：
 * <p>
 * 该切割是两个端点在圆上的线段，且该线段经过圆心。
 * 该切割是一端在圆心另一端在圆上的线段。
 * 一些有效和无效的切割如下图所示。
 * <p>
 * 给你一个整数 n ，请你返回将圆切割成相等的 n 等分的 最少 切割次数。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：
 * 上图展示了切割圆 2 次，得到四等分。
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：
 * 最少需要切割 3 次，将圆切成三等分。
 * 少于 3 次切割无法将圆切成大小相等面积相同的 3 等分。
 * 同时可以观察到，第一次切割无法将圆切割开。
 * <p>
 * 提示：
 * 1 <= n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/17 9:52
 */
public class Solution2481 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了 90.86% 的用户
     * 通过测试用例：100 / 100
     *
     * @param n
     * @return
     */
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        int count = 0;
        if (n % 2 == 0) {
            n /= 2;
            count++;
        }
        return count + n;
    }
}
