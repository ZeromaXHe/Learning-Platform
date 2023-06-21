package leetcode.jianzhi_offer_II;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 037. 小行星碰撞 | 难度：中等 | 标签：栈、数组
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * <p>
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * <p>
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * <p>
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * <p>
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * <p>
 * 示例 4：
 * 输入：asteroids = [-2,-1,1,2]
 * 输出：[-2,-1,1,2]
 * 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 * <p>
 * 提示：
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * <p>
 * 注意：本题与主站 735 题相同： https://leetcode-cn.com/problems/asteroid-collision/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/XagZNi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 17:36
 */
public class SolutionOfferII37 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 96.64% 的用户
     * 内存消耗：43.6 MB, 在所有 Java 提交中击败了 9.83% 的用户
     * 通过测试用例：275 / 275
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0 || stack.isEmpty() || stack.peek() < 0) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                }
            }
        }
        int[] result = new int[stack.size()];
        Iterator<Integer> iter = stack.iterator();
        int i = result.length - 1;
        while (iter.hasNext()) {
            result[i--] = iter.next();
        }
        return result;
    }
}
