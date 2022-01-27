package leetcode.from1101to1150;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/1/26 10:06
 * @Description: 1128.等价多米诺骨牌对的数量 | 难度：简单 | 标签：数组
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * <p>
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * <p>
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * <p>
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * <p>
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1128 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 85.51% 的用户
     * 内存消耗： 48 MB , 在所有 Java 提交中击败了 9.05% 的用户
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        for (int[] domino : dominoes) {
            int num = domino[0] * 10 + domino[1];
            if (domino[0] > domino[1]) {
                num = domino[1] * 10 + domino[0];
            }
            count[num]++;
        }
        int result = 0;
        for (int countNum : count) {
            if (countNum > 1) {
                result += countNum * (countNum - 1) / 2;
            }
        }
        return result;
    }

    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 31.63% 的用户
     * 内存消耗： 47.4 MB , 在所有 Java 提交中击败了 80.86% 的用户
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs_mapMerge(int[][] dominoes) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] domino : dominoes) {
            int num = domino[0] * 10 + domino[1];
            if (domino[0] > domino[1]) {
                num = domino[1] * 10 + domino[0];
            }
            map.merge(num, 1, Integer::sum);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                count += entry.getValue() * (entry.getValue() - 1) / 2;
            }
        }
        return count;
    }

    /**
     * 执行用时： 33 ms , 在所有 Java 提交中击败了 6.73% 的用户
     * 内存消耗： 48.1 MB , 在所有 Java 提交中击败了 6.79% 的用户
     * 没利用 1 <= dominoes[i][j] <= 9 的条件，所以比较慢
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs_not1to9(int[][] dominoes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int[] domino : dominoes) {
            int less = domino[0];
            int more = domino[1];
            if (domino[0] > domino[1]) {
                less = domino[1];
                more = domino[0];
            }
            String key = less + "#" + more;
            map.merge(key, 1, Integer::sum);
        }
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                count += entry.getValue() * (entry.getValue() - 1) / 2;
            }
        }
        return count;
    }
}
