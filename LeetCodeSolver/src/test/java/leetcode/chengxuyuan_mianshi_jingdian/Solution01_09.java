package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 12:01
 * @Description: 面试题 01.09. 字符串轮转 | 难度：简单 | 标签：字符串、字符串匹配
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * <p>
 * 示例2:
 * 输入：s1 = "aa", s2 = "aba"
 * 输出：False
 * <p>
 * 提示：
 * 字符串长度在[0, 100000]范围内。
 * <p>
 * 说明:
 * 你能只调用一次检查子串的方法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-rotation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_09 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.6 MB , 在所有 Java 提交中击败了 24.89% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }
}
