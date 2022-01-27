package leetcode.from751to800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/30 17:35
 * @Description: 767. 重构字符串 | 难度：中等 | 标签：堆、贪心算法、排序、字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * <p>
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * <p>
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution767 {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 25.03% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了 67.94% 的用户
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        if (S.length() <= 1) {
            return S;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Map.Entry<Character, Integer>> sortList = new ArrayList<>(map.entrySet());
        sortList.sort(Comparator.comparingInt(Map.Entry<Character, Integer>::getValue).reversed());
        if (sortList.get(0).getValue() > (S.length() + 1) / 2) {
            return "";
        }
        char[] chars = new char[S.length()];
        int charsIndex = 0;
        int listIndex = 0;
        int entryCount = 0;
        while (charsIndex < chars.length) {
            chars[charsIndex] = sortList.get(listIndex).getKey();
            if (++entryCount == sortList.get(listIndex).getValue()) {
                listIndex++;
                entryCount = 0;
            }
            charsIndex += 2;
        }
        charsIndex = 1;
        while (charsIndex < chars.length) {
            chars[charsIndex] = sortList.get(listIndex).getKey();
            if (++entryCount == sortList.get(listIndex).getValue()) {
                listIndex++;
                entryCount = 0;
            }
            charsIndex += 2;
        }
        return new String(chars);
    }
}
