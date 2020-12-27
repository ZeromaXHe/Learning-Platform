package leetcode.from201to300;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/27 16:57
 * @Description: 205.同构字符串 | 难度：简单 | 标签：哈希表
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution205 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 77.05% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 83.98% 的用户
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapStoT = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (mapStoT.containsKey(charS[i])) {
                if (charT[i] != mapStoT.get(charS[i])) {
                    return false;
                }
            } else {
                if (set.contains(charT[i])) {
                    return false;
                }
                mapStoT.put(charS[i], charT[i]);
                set.add(charT[i]);
            }
        }
        return true;
    }
}
