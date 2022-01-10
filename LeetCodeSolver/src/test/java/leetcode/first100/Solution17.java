package leetcode.first100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 11:43
 * @Description: 17. 电话号码的字母组合 | 难度：中等 | 标签：哈希表、字符串、回溯
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution17 {
    String[] phone = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 59.30% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 93.23% 的用户
     * 通过测试用例： 25 / 25
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        int count = 1;
        boolean digitEmpty = digits == null || "".equals(digits);
        if (!digitEmpty) {
            for (int i = 0; i < digits.length(); i++) {
                if (digits.charAt(i) == '9' || digits.charAt(i) == '7') {
                    count *= 4;
                } else {
                    count *= 3;
                }
            }
        }
        if (digitEmpty) {
            return new ArrayList<>(0);
        }
        return letterCombinations(digits, count);
    }

    private List<String> letterCombinations(String digits, int count) {
        List<String> result = new ArrayList<>(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int code = i;
            for (int j = 0; j < digits.length(); j++) {
                String str = phone[digits.charAt(j) - '2'];
                sb.append(str.charAt(code % str.length()));
                code /= str.length();
            }
            result.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return result;
    }
}
