package leetcode.from1to50;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/23 14:36
 * @Description: 13. 罗马数字转整数 | 难度：简单 | 标签：数字、字符串
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
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
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 * <p>
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 * <p>
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * <p>
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 提示：
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution13 {
    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 51.07% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 68.28% 的用户
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'I':
                    // 1
                    if (i + 1 < chars.length) {
                        if (chars[i + 1] == 'V') {
                            // 4 = IV
                            i++;
                            sum += 4;
                        } else if (chars[i + 1] == 'X') {
                            // 9 = IX
                            i++;
                            sum += 9;
                        } else {
                            sum++;
                        }
                    } else {
                        sum++;
                    }
                    break;
                case 'V':
                    // 5
                    sum += 5;
                    break;
                case 'X':
                    // 10
                    if (i + 1 < chars.length) {
                        if (chars[i + 1] == 'L') {
                            // 40 = XL
                            i++;
                            sum += 40;
                        } else if (chars[i + 1] == 'C') {
                            // 90 = XC
                            i++;
                            sum += 90;
                        } else {
                            sum += 10;
                        }
                    } else {
                        sum += 10;
                    }
                    break;
                case 'L':
                    // 50
                    sum += 50;
                    break;
                case 'C':
                    // 100
                    if (i + 1 < chars.length) {
                        if (chars[i + 1] == 'D') {
                            // 40 = CD
                            i++;
                            sum += 400;
                        } else if (chars[i + 1] == 'M') {
                            // 90 = CM
                            i++;
                            sum += 900;
                        } else {
                            sum += 100;
                        }
                    } else {
                        sum += 100;
                    }
                    break;
                case 'D':
                    // 500
                    sum += 500;
                    break;
                case 'M':
                    // 1000
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }
}
