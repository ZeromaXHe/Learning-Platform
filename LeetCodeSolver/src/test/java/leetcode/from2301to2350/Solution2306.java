package leetcode.from2301to2350;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuxi
 * @apiNote 2306. 公司命名 | 难度：困难 | 标签：位运算、数组、哈希表、字符串、枚举
 * 给你一个字符串数组 ideas 表示在公司命名过程中使用的名字列表。公司命名流程如下：
 * <p>
 * 从 ideas 中选择 2 个 不同 名字，称为 ideaA 和 ideaB 。
 * 交换 ideaA 和 ideaB 的首字母。
 * 如果得到的两个新名字 都 不在 ideas 中，那么 ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
 * 否则，不是一个有效的名字。
 * 返回 不同 且有效的公司名字的数目。
 * <p>
 * 示例 1：
 * 输入：ideas = ["coffee","donuts","time","toffee"]
 * 输出：6
 * 解释：下面列出一些有效的选择方案：
 * - ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
 * - ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
 * - ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
 * - ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
 * - ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
 * - ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
 * 因此，总共有 6 个不同的公司名字。
 * <p>
 * 下面列出一些无效的选择方案：
 * - ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
 * - ("time", "toffee")：在原数组中存在交换后形成的两个名字。
 * - ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
 * <p>
 * 示例 2：
 * 输入：ideas = ["lack","back"]
 * 输出：0
 * 解释：不存在有效的选择方案。因此，返回 0 。
 * <p>
 * 提示：
 * 2 <= ideas.length <= 5 * 104
 * 1 <= ideas[i].length <= 10
 * ideas[i] 由小写英文字母组成
 * ideas 中的所有字符串 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/naming-a-company
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/7/23 16:10
 */
public class Solution2306 {
    @Test
    public void testDistinctNames() {
        Assert.assertEquals(0L, distinctNames(new String[]{"lack", "back"}));
        Assert.assertEquals(6L, distinctNames(new String[]{"coffee", "donuts", "time", "toffee"}));
        Assert.assertEquals(10L,
                distinctNames(new String[]{"coffee", "donuts", "time", "toffee", "koffee", "dime", "conuts"}));
    }

    private int hashIndex = 0;

    /**
     * 执行用时：73 ms, 在所有 Java 提交中击败了 96.40% 的用户
     * 内存消耗：59.1 MB, 在所有 Java 提交中击败了 16.85% 的用户
     * 通过测试用例：89 / 89
     *
     * @param ideas
     * @return
     */
    public long distinctNames(String[] ideas) {
        hashIndex = 0;
        HashMap<String, Integer> hash = new HashMap<>();
        List<BitSet> bitsets = getBitSets(ideas, hash);
        long sum = 0;
        for (int i = 0; i < bitsets.size(); i++) {
            for (int j = 0; j < i; j++) {
                BitSet cloneI = (BitSet) bitsets.get(i).clone();
                cloneI.andNot(bitsets.get(j));
                BitSet cloneJ = (BitSet) bitsets.get(j).clone();
                cloneJ.andNot(bitsets.get(i));
                sum += (long) cloneI.cardinality() * cloneJ.cardinality();
            }
        }
        return sum * 2;
    }

    private List<BitSet> getBitSets(String[] ideas, HashMap<String, Integer> hash) {
        HashMap<Character, List<String>> startCharMap = new HashMap<>();
        for (String idea : ideas) {
            startCharMap.putIfAbsent(idea.charAt(0), new LinkedList<>());
            startCharMap.get(idea.charAt(0)).add(idea.substring(1));
        }
        Collection<List<String>> values = startCharMap.values();
        List<BitSet> result = new ArrayList<>(values.size());
        for (List<String> suffixes : values) {
            result.add(toBitSet(hash, suffixes));
        }
        return result;
        /**
         * 使用注释的 Streams 代码实现本方法的话，结果如下：
         * 执行用时：90 ms, 在所有 Java 提交中击败了 92.91% 的用户
         * 内存消耗：56.7 MB, 在所有 Java 提交中击败了 23.74% 的用户
         * 通过测试用例：89 / 89
         */
//        return Arrays.stream(ideas)
//                .collect(Collectors.groupingBy(s -> s.charAt(0)))
//                .values()
//                .stream()
//                .map(v -> toBitSet(hash, v.stream().map(s -> s.substring(1)).collect(Collectors.toList())))
//                .collect(Collectors.toList());
    }

    private BitSet toBitSet(HashMap<String, Integer> hash, List<String> strs) {
        BitSet result = new BitSet();
        for (String s : strs) {
            int digit = hash.containsKey(s) ? hash.get(s) : hashIndex;
            if (digit == hashIndex) {
                hash.put(s, hashIndex);
                hashIndex++;
            }
            result.set(digit);
        }
        return result;
    }
}
