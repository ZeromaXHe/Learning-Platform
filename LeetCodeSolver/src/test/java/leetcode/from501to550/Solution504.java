package leetcode.from501to550;

/**
 * @Author: zhuxi
 * @Time: 2022/3/7 9:59
 * @Description: 504. 七进制数 | 难度：简单 | 标签：数学
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 * <p>
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * 提示：
 * -107 <= num <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution504 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 17.21% 的用户
     * 通过测试用例： 241 / 241
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        int num7 = num > 0 ? num : -num;
        StringBuilder sb = new StringBuilder();
        while (num7 > 0) {
            sb.append(num7 % 7);
            num7 /= 7;
        }
        if (num < 0) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}
