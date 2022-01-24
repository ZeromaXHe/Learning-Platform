package leetcode.from101to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 21:11
 * @Description: 171. Excel表列序号 | 难度：简单 | 标签：数学、字符串
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * <p>
 * 例如，
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
 * 示例 1:
 * 输入: columnTitle = "A"
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: columnTitle = "AB"
 * 输出: 28
 * <p>
 * 示例 3:
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * <p>
 * 示例 4:
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 * <p>
 * 提示：
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution171 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 59.39% 的用户
     * 通过测试用例： 1002 / 1002
     *
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result *= 26;
            result += columnTitle.charAt(i) - 'A' + 1;
        }
        return result;
    }
}
