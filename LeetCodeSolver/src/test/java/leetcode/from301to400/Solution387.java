package leetcode.from301to400;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/23 10:08
 * @Description: 387. 字符串中的第一个唯一字符 | 难度：简单 | 标签：哈希表、字符串
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 示例：
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution387 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 72.75% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 85.84% 的用户
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
