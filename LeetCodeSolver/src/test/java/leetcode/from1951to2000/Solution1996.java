package leetcode.from1951to2000;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/1/28 11:54
 * @Description: 1996. 游戏中弱角色的数量 | 难度：中等 | 标签：栈、贪心、数组、排序、单调栈
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * <p>
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * <p>
 * 返回 弱角色 的数量。
 * <p>
 * 示例 1：
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 * <p>
 * 示例 2：
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * <p>
 * 示例 3：
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * <p>
 * 提示：
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1996 {
    /**
     * 执行用时： 168 ms , 在所有 Java 提交中击败了 5.65% 的用户
     * 内存消耗： 86.9 MB , 在所有 Java 提交中击败了 5.24% 的用户
     * 通过测试用例： 44 / 44
     * <p>
     * 使用Comparator.comparingInt比较慢
     * Arrays.sort(properties, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt((int[] o) -> o[1]).reversed());
     * <p>
     * 执行用时： 95 ms , 在所有 Java 提交中击败了 52.82% 的用户
     * 内存消耗： 87 MB , 在所有 Java 提交中击败了 5.24% 的用户
     * 通过测试用例： 44 / 44
     *
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o2[1] - o1[1]) : (o2[0] - o1[0]));
        int preMaxDef = Integer.MIN_VALUE;
        int maxDef = Integer.MIN_VALUE;
        int minAtk = Integer.MAX_VALUE;
        int count = 0;
        for (int[] property : properties) {
            if (minAtk != property[0]) {
                minAtk = property[0];
                preMaxDef = maxDef;
            }
            maxDef = Math.max(property[1], maxDef);
            if (preMaxDef > property[1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 执行用时： 92 ms , 在所有 Java 提交中击败了 84.68% 的用户
     * 内存消耗： 87.1 MB , 在所有 Java 提交中击败了 5.24% 的用户
     * 通过测试用例： 44 / 44
     *
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters2(int[][] properties) {
        // 让防御力从小到大排列可以省掉preMaxDef、minAtk的逻辑
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]));
        int maxDef = Integer.MIN_VALUE;
        int count = 0;
        for (int[] property : properties) {
            if (maxDef > property[1]) {
                count++;
            } else {
                maxDef = property[1];
            }
        }
        return count;
    }

    @Test
    public void arraysSortTest() {
        int[][] properties = {{1, 2}, {1, 3}, {2, 2}, {2, 3}};
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o2[1] - o1[1]) : (o2[0] - o1[0]));
        for (int[] property : properties) {
            System.out.println("[" + property[0] + "," + property[1] + "]");
        }
    }
}
