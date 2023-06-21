package leetcode.jianzhi_offer_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 033. 变位词组 | 难度：中等 | 标签：数组、字符串、哈希表、排序
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * <p>
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * <p>
 * 注意：本题与主站 49 题相同： https://leetcode-cn.com/problems/group-anagrams/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sfvd7V
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 16:14
 */
public class SolutionOfferII33 {
    /**
     * 执行用时：14 ms, 在所有 Java 提交中击败了 19.28% 的用户
     * 内存消耗：45.4 MB, 在所有 Java 提交中击败了 43.68% 的用户
     * 通过测试用例：114 / 114
     * <p>
     * 答案里面有个用质数相乘来代表字符数量的，是真的妙。
     * 大概原理就是一个字符对应一个质数，然后变位词的乘积就会相等。
     * 不过答案里面是用 MOD 取余了，能通过不知道会不会受用例影响。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        for (String str : strs) {
            sb.delete(0, sb.length());
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]).append('|');
            }
            String key = sb.toString();
            map.putIfAbsent(key, new LinkedList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
