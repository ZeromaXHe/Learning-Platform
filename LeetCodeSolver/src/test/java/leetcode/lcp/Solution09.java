package leetcode.lcp;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * LCP 09. 最小跳跃次数 | 难度：困难 | 标签：广度优先搜索、线段树、数组、动态规划
 * 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。
 * <p>
 * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
 * <p>
 * 示例 1：
 * <p>
 * 输入：jump = [2, 5, 1, 1, 1, 1]
 * <p>
 * 输出：3
 * <p>
 * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
 * <p>
 * 限制：
 * <p>
 * 1 <= jump.length <= 10^6
 * 1 <= jump[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: ZeromaXHe
 * @Time: 2022/3/19 20:21
 * @Description:
 * @ModifiedBy: ZeromaXHe
 */
public class Solution09 {
    @Test
    public void minJumpTest() {
        Assert.assertEquals(3, minJump(new int[]{2, 5, 1, 1, 1, 1}));
        // 通过测试用例： 3 / 41
        Assert.assertEquals(6, minJump(new int[]{3, 7, 6, 1, 4, 3, 7, 8, 1, 2, 8, 5, 9, 8, 3, 2, 7, 5, 1, 1}));
    }

    /**
     * 参考了用户发布的题解
     * https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/solution/jian-dan-yi-dong-de-dong-tai-gui-hua-wan-fa-by-don/
     * <p>
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 120.4 MB , 在所有 Java 提交中击败了 64.47% 的用户
     * 通过测试用例： 41 / 41
     *
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        int[] dp = new int[jump.length];
        dp[jump.length - 1] = 1;
        for (int i = jump.length - 2; i > -1; --i) {
            dp[i] = jump[i] + i >= jump.length ? 1 : dp[jump[i] + i] + 1;
            //遍历当前位置更新后影响到的后面的位置，只需要更新到dp[j] >= dp[i]+1即可
            //如果遍历到某dp[j]<dp[i]+1就不需要向右遍历了,因为j到dp.length的值会被当前遍历到的dp[j]更新而不是dp[i]+1
            for (int j = i + 1; j < dp.length && dp[j] >= dp[i] + 1; ++j) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[0];
    }

    /**
     * 题意看错了。理解成了向右跳到jump[i]以内的一点。且没有看到可以向左
     *
     * @param jump
     * @return
     */
    public int minJump_wrongThinking(int[] jump) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(jump.length - 1);
        for (int i = jump.length - 2; i >= 0; i--) {
            if (jump[i] + i >= stack.peek()) {
                while (stack.size() > 1 && jump[i] + i >= stack.get(1)) {
                    stack.pop();
                }
                if (stack.size() == 1 && jump[i] + i >= jump.length) {
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return stack.size();
    }
}
