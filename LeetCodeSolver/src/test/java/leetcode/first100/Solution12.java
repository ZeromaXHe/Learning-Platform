package leetcode.first100;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/23 14:24
 * @Description: 12. 整数转罗马数字 | 难度：中等 | 标签：数学、字符串
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * <p>
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * <p>
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * <p>
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution12 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.79% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 86.33% 的用户
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append('M');
            num -= 1000;
        }
        if (num >= 900) {
            sb.append("CM");
            num -= 900;
        }
        if (num >= 500) {
            sb.append('D');
            num -= 500;
        }
        if (num >= 400) {
            sb.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            sb.append('C');
            num -= 100;
        }
        if (num >= 90) {
            sb.append("XC");
            num -= 90;
        }
        if (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        if (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        if (num >= 9) {
            sb.append("IX");
            num -= 9;
        }
        if (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        if (num >= 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num--;
        }
        return sb.toString();
    }
}
