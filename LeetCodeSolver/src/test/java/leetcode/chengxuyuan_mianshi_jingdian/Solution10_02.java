package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/13 17:08
 * @Description: 面试题 10.02. 变位词组 | 难度：中等 | 标签：哈希表、字符串、排序
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * <p>
 * 注意：本题相对原题稍作修改
 * <p>
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-anagrams-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_02 {
    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 98.65% 的用户
     * 内存消耗： 43.8 MB , 在所有 Java 提交中击败了 98.50% 的用户
     * 通过测试用例： 101 / 101
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            map.putIfAbsent(key, new LinkedList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String getKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
