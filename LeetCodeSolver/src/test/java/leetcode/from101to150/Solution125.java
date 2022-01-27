package leetcode.from101to150;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 19:19
 * @Description: 125. 验证回文串 | 难度：简单 | 标签：双指针、字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * <p>
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * 提示：
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution125 {

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 66.90% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 36.77% 的用户
     * 通过测试用例： 480 / 480
     * <p>
     * 看别人答案，实现一下判断是否字符和数字同时把大写转换成小写的逻辑会比Character类库方法快一些
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int right = s.length() - 1;
        int left = 0;
        while (left < right) {
            while (left < right && !Character.isAlphabetic(s.charAt(left)) && !Character.isDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isAlphabetic(s.charAt(right)) && !Character.isDigit(s.charAt(right))) {
                right--;
            }
            if (left >= right) {
                break;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    public void characterMethodTest() {
        System.out.println("isAlphabetic");
        System.out.println("a:" + Character.isAlphabetic('a'));
        System.out.println("A:" + Character.isAlphabetic('A'));
        System.out.println("1:" + Character.isAlphabetic('1'));
        System.out.println("-:" + Character.isAlphabetic('-'));
        System.out.println("~:" + Character.isAlphabetic('~'));
        System.out.println("哈:" + Character.isAlphabetic('哈'));
        System.out.println("。:" + Character.isAlphabetic('。'));
        System.out.println("\\n:" + Character.isAlphabetic('\n'));
        System.out.println("isLetter");
        System.out.println("a:" + Character.isLetter('a'));
        System.out.println("A:" + Character.isLetter('A'));
        System.out.println("1:" + Character.isLetter('1'));
        System.out.println("-:" + Character.isLetter('-'));
        System.out.println("~:" + Character.isLetter('~'));
        System.out.println("哈:" + Character.isLetter('哈'));
        System.out.println("。:" + Character.isLetter('。'));
        System.out.println("\\n:" + Character.isLetter('\n'));
    }
}
