package leetcode.from101to200;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 20:09
 * @Description: 168. Excel表列名称 | 难度：简单 | 标签：数学、字符串
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如：
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 * <p>
 * 示例 2：
 * 输入：columnNumber = 28
 * 输出："AB"
 * <p>
 * 示例 3：
 * 输入：columnNumber = 701
 * 输出："ZY"
 * <p>
 * 示例 4：
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 * <p>
 * 提示：
 * 1 <= columnNumber <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution168 {
    @Test
    public void convertToTitleTest() {
        Assert.assertEquals("AA", convertToTitle(27));
        // 通过测试用例： 14 / 18
        Assert.assertEquals("ZY", convertToTitle(701));
        // 通过测试用例： 16 / 18
        Assert.assertEquals("AZ", convertToTitle(52));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.4 MB , 在所有 Java 提交中击败了 78.66% 的用户
     * 通过测试用例： 18 / 18
     *
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            if (columnNumber % 26 >= 0) {
                sb.append((char) ('A' + columnNumber % 26));
            }
            if (columnNumber < 26) {
                break;
            }
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
