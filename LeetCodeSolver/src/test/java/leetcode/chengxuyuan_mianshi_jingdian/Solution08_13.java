package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 18:51
 * @Description: 面试题 08.13. 堆箱子 | 难度：困难 | 标签：数组、动态规划、排序
 * 堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
 * <p>
 * 输入使用数组[wi, di, hi]表示每个箱子。
 * <p>
 * 示例1:
 * 输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 * 输出：6
 * <p>
 * 示例2:
 * 输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
 * 输出：10
 * <p>
 * 提示:
 * 箱子的数目不大于3000个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pile-box-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_13 {
    /**
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 98.85% 的用户
     * 内存消耗： 40.7 MB , 在所有 Java 提交中击败了 74.42% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 没看懂为啥这样按所有维度都排序一下就更快……
     *
     * @param box
     * @return
     */
    public int pileBox(int[][] box) {
        /**
         * 执行用时： 32 ms , 在所有 Java 提交中击败了 77.19% 的用户
         * 内存消耗： 40.8 MB , 在所有 Java 提交中击败了 64.75% 的用户
         * 通过测试用例： 30 / 30
         */
//        Arrays.sort(box, (box1, box2) -> box1[0] - box2[0]);
        Arrays.sort(box, (box1, box2) ->
                box1[0] != box2[0] ? box1[0] - box2[0] : (box1[1] != box2[1] ? box1[1] - box2[1] : box1[2] - box2[2]));
        int[] dp = new int[box.length];
        int result = 0;
        for (int i = 0; i < box.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[i][0] == box[j][0]) {
                    break;
                }
                // i 的三维都要比 j 大
                if (box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    // 在 0 <= j < i 范围内找到最大的 dp[j], 贪心
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            // 最后加上最底端箱子的高度
            dp[i] += box[i][2];
            result = Math.max(dp[i], result);
        }
        return result;
    }

    /**
     * 执行用时： 39 ms , 在所有 Java 提交中击败了 49.54% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 8.76% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 参考题解做的
     *
     * @param box
     * @return
     */
    public int pileBox_slow(int[][] box) {
        /**
         * 执行用时： 255 ms , 在所有 Java 提交中击败了 5.07% 的用户
         * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 13.37% 的用户
         * 通过测试用例： 30 / 30
         */
//        Arrays.sort(box, Comparator.comparingInt((int[] x) -> x[0])
//                .thenComparingInt((int[] x) -> x[1])
//                .thenComparingInt(x -> x[2]));
        Arrays.sort(box, Comparator.comparingInt(x -> x[0]));
        int[] dp = new int[box.length];
        int result = 0;
        for (int i = 0; i < box.length; ++i) {
            for (int j = 0; j < i; ++j) {
                // i 的三维都要比 j 大
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    // 在 0 <= j < i 范围内找到最大的 dp[j], 贪心
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            // 最后加上最底端箱子的高度
            dp[i] += box[i][2];
            result = Math.max(dp[i], result);
        }
        return result;
    }
}
