package leetcode.from201to250;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/22 12:47
 * @Description: 242.有效的字母异位词 | 难度：简单 | 标签：排序、哈希表
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution242 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 99.91% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了 45.46% 的用户
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
