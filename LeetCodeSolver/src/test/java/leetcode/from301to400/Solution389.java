package leetcode.from301to400;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/18 9:27
 * @Description: 389.找不同 | 难度：简单 | 标签：位运算、哈希表
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * <p>
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 * <p>
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *  
 * <p>
 * 提示：
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution389 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37.2 MB , 在所有 Java 提交中击败了 22.83% 的用户
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int count = 0;
        for (char c : t.toCharArray()) {
            count += (c - 'a');
        }
        for (char c : s.toCharArray()) {
            count -= (c - 'a');
        }
        return (char) (count + 'a');
    }
}
