package leetcode.from201to300;

/**
 * @Author: zhuxi
 * @Time: 2021/10/11 9:51
 * @Description: 273. 整数转换英文表示 | 难度：困难 | 标签：递归、数学、字符串
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 * 示例 1：
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * <p>
 * 示例 2：
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * <p>
 * 示例 3：
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * <p>
 * 示例 4：
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * <p>
 * 提示：
 * 0 <= num <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution273 {
    private String[] english = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private String[] english10 = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] affix = new String[]{"Billion", "Million", "Thousand"};

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 92.33% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 93.95% 的用户
     * 通过测试用例： 601 / 601
     *
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int[] threeDigits = new int[4];
        threeDigits[0] = num / 1_000_000_000;
        threeDigits[1] = (num / 1_000_000) % 1000;
        threeDigits[2] = (num / 1_000) % 1000;
        threeDigits[3] = num % 1000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (threeDigits[i] == 0) {
                continue;
            }
            transformThreeDigitsToEnglish(sb, threeDigits[i]);
            if (i < 3) {
                sb.append(' ').append(affix[i]);
            }
        }
        return sb.toString();
    }

    private void transformThreeDigitsToEnglish(StringBuilder sb, int threeDigit) {
        int[] digits = new int[3];
        digits[0] = threeDigit / 100;
        digits[1] = threeDigit / 10 % 10;
        digits[2] = threeDigit % 10;
        if (sb.length() > 0) {
            sb.append(' ');
        }
        if (digits[0] != 0) {
            sb.append(english[digits[0]]).append(' ').append("Hundred");
        }
        if (digits[1] <= 1) {
            if (digits[2] == digits[1] && digits[1] == 0) {
                return;
            }
            if (digits[0] != 0) {
                sb.append(' ');
            }
            sb.append(english[threeDigit % 100]);
            return;
        } else {
            if (digits[0] != 0) {
                sb.append(' ');
            }
            sb.append(english10[digits[1]]);
        }
        if (digits[2] != 0) {
            sb.append(' ').append(english[digits[2]]);
        }
    }
}
