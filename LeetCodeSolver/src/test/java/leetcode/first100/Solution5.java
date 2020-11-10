package leetcode.first100;

import com.zerox.entity.Tester;

/**
 * @Author: zhuxi
 * @Time: 2020/11/10 14:07
 * @Description: 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution5 {
    public String longestPalindrome(String s) {
        return "";
    }

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        Tester<String, String> tester = new Tester<>();
        tester.addCase("babad", "bab");
        tester.addCase("cbbd", "bb");
        tester.setFunction(solution5::longestPalindrome);
        System.out.println(tester.testAll());
    }
}