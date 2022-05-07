package leetcode.from401to450;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/7 17:48
 * @Description: 433. 最小基因变化 | 难度：中等 | 标签：广度优先搜索、哈希表、字符串
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 * <p>
 * 提示：
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution433 {
    @Test
    public void minMutationTest() {
        Assert.assertEquals(4, minMutation("AAAACCCC", "CCCCCCCC",
                new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"}));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 47.59% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 90.99% 的用户
     * 通过测试用例： 14 / 14
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bank.length; i++) {
            if (start.equals(bank[i])) {
                continue;
            }
            for (int j = i + 1; j < bank.length; j++) {
                if (start.equals(bank[j])) {
                    continue;
                }
                if (oneCharDiff(bank[i], bank[j])) {
                    map.putIfAbsent(i, new LinkedList<>());
                    map.get(i).add(j);
                    map.putIfAbsent(j, new LinkedList<>());
                    map.get(j).add(i);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        boolean[] visited = new boolean[bank.length];
        for (int i = 0; i < bank.length; i++) {
            if (oneCharDiff(bank[i], start)) {
                if (end.equals(bank[i])) {
                    return 1;
                }
                visited[i] = true;
                int count = bfs(bank, i, end, map, visited, 1);
                if (count < min) {
                    min = count;
                }
                visited[i] = false;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int bfs(String[] bank, int from, String to, HashMap<Integer, List<Integer>> map, boolean[] visited, int count) {
        List<Integer> listTo = map.get(from);
        int min = Integer.MAX_VALUE;
        if (listTo == null) {
            return min;
        }
        for (Integer canTo : listTo) {
            if (visited[canTo]) {
                continue;
            }
            if (to.equals(bank[canTo])) {
                return count + 1;
            }
            visited[canTo] = true;
            int result = bfs(bank, canTo, to, map, visited, count + 1);
            if (result < min) {
                min = result;
            }
            visited[canTo] = false;
            // 提前剪枝
            if (min == count + 2) {
                return min;
            }
        }
        return min;
    }

    private boolean oneCharDiff(String s1, String s2) {
        boolean diff = false;
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff) {
                    return false;
                }
                diff = true;
            }
        }
        return diff;
    }
}
