package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 10:07
 * @Description: 面试题 01.02. 判定是否互为字符重排 | 难度：简单 | 标签：哈希表、字符串、排序
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * <p>
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * <p>
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * <p>
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_02 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 75.55% 的用户
     * 通过测试用例： 23 / 23
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s2.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            int nextVal = map.get(c) - 1;
            if (nextVal < 0) {
                return false;
            }
            map.put(c, nextVal);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}
