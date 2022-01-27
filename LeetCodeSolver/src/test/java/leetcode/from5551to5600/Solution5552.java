package leetcode.from5551to5600;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author ZeromaXHe
 * @date 2020/11/14 23:21
 * @Description 5552. 到家的最少跳跃次数 | 难度：中等 | 标签： 广度优先搜索、动态规划
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
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x 的可行方案，请你返回 -1 。
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
 */
public class Solution5552 {
    /**
     * 未完成
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
        if (a == b) {
            if (x % a == 0) {
                for (int i : forbidden) {
                    if (i % a == 0) {
                        return -1;
                    }
                }
                return x / a;
            }
            return -1;
        }

        HashSet<Integer> forbiddenSet = new HashSet<>();
        for (int i : forbidden) {
            forbiddenSet.add(i);
        }

        // 所有当前步数位置作为关键点存起来，数组第一位为index，第二位为是否可以回退（1可以，0不可以）
        LinkedList<int[]> positionList = new LinkedList<>();
        // 记录计算过的节点
        HashSet<Integer> computedPosition = new HashSet<>();

        int step = 0;
        positionList.add(new int[]{0, 1});
        while (!positionList.isEmpty()) {
            for (int[] arr : positionList) {
                if (arr[1] == 0) {
                    if (arr[0] + a == x) {
                        return step + 1;
                    }
                    if (computedPosition.contains(arr[0] + a)) {
                        // 删除多的话估计耗时很长
                        positionList.remove(arr);
                    }
                    computedPosition.add(arr[0]);
                    arr[0] += a;
                    arr[1] = 1;
                    step++;
                } else {
                    // 可回跳
                    if (arr[0] + a == x || arr[0] - a == x) {
                        return step + 1;
                    }
                    int now = arr[0];
                    boolean computedForward = computedPosition.contains(arr[0] + a);
                    boolean computedBackward = computedPosition.contains(arr[0] - a);
                    if (computedForward && computedBackward) {
                        positionList.remove(arr);
                    } else if (computedForward) {

                    } else if (computedBackward) {

                    } else {

                    }
                }
            }
        }

        return -1;
    }
}
