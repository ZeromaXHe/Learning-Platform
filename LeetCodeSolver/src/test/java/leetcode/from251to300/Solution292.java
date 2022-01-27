package leetcode.from251to300;

/**
 * @Author: zhuxi
 * @Time: 2021/9/18 9:04
 * @Description: 292. Nim游戏 | 难度：简单 | 标签：脑筋急转弯、数学、博弈
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * <p>
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：false
 * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：n = 2
 * 输出：true
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nim-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution292 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.1 MB , 在所有 Java 提交中击败了 59.17% 的用户
     * 通过测试用例： 60 / 60
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
