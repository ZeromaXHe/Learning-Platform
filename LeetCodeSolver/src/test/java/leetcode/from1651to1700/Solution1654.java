package leetcode.from1651to1700;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 1654. 到家的最少跳跃次数 | 难度：中等 | 标签：广度优先搜索、数组、动态规划
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 * <p>
 * 跳蚤跳跃的规则如下：
 * <p>
 * 它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。
 * 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 * <p>
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。
 * 如果没有恰好到达 x 的可行方案，请你返回 -1 。
 * <p>
 * 示例 1：
 * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * 输出：3
 * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 * <p>
 * 示例 2：
 * 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * 输出：2
 * 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
 * <p>
 * 提示：
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * forbidden 中所有位置互不相同。
 * 位置 x 不在 forbidden 中。
 * @implNote
 * @since 2023/8/30 9:53
 */
public class Solution1654 {
    @Test
    public void minimumJumpsTest() {
        Assert.assertEquals(2, minimumJumps(new int[]{1, 6, 2, 14, 5, 17, 4}, 16, 9, 7));
        Assert.assertEquals(6, minimumJumps(new int[]{128, 178, 147, 165, 63, 11, 150, 20, 158, 144, 136},
                61, 170, 135));
        Assert.assertEquals(121, minimumJumps(new int[]{162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73,
                200, 127, 30, 124, 193, 84, 184, 36, 103, 149, 153, 9, 54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59,
                71, 48, 177, 82, 35, 14, 176, 16, 108, 111, 6, 168, 31, 134, 164, 136, 72, 98}, 29, 98, 80));
        Assert.assertEquals(-1, minimumJumps(new int[]{18, 13, 3, 9, 8, 14}, 3, 8, 6));
    }

    /**
     * 时间 17 ms
     * 击败 75.56% 使用 Java 的用户
     * 内存 41.65 MB
     * 击败 41.11%使用 Java 的用户
     * <p>
     * 一开始忘记这个上限怎么求了，后来对着题解做也错了好几次，真是烦死了……
     * 一年前还用 C# 做过，真是吐了。
     *
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) {
            return 0;
        }
        int max = 0;
        for (int i : forbidden) {
            max = Math.max(max, i);
        }
        max += a;
        max = Math.max(max, x) + b;
        HashSet<Integer> leftVisited = new HashSet<>();
        HashSet<Integer> rightVisited = new HashSet<>();
        for (int i : forbidden) {
            leftVisited.add(i);
            rightVisited.add(i);
        }
        leftVisited.add(0);
        LinkedList<int[]> queue = new LinkedList<>();
        if (a <= max && !rightVisited.contains(a)) {
            queue.offer(new int[]{0, a});
            rightVisited.add(a);
        }
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 当前位置
                int posi = poll[0] + poll[1];
                if (posi == x) {
                    return step;
                }
                if (posi + a <= max && !rightVisited.contains(posi + a)) {
                    rightVisited.add(posi + a);
                    queue.offer(new int[]{posi, a});
                }
                // poll[1] 负数表示向左跳过一次，下次不能继续左跳
                if (poll[1] > 0 && !leftVisited.contains(posi - b) && posi - b > 0) {
                    leftVisited.add(posi - b);
                    queue.offer(new int[]{posi, -b});
                }
            }
        }
        return -1;
    }
}
