package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/7 18:49
 * @Description: 面试题 05.02. 二进制数转字符串 | 难度：中等 | 标签：位运算、数学、字符串
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * <p>
 * 示例1:
 * 输入：0.625
 * 输出："0.101"
 * <p>
 * 示例2:
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * <p>
 * 提示：
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bianry-number-to-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_02 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 88.42% 的用户
     * 通过测试用例： 33 / 33
     *
     * @param num
     * @return
     */
    public String printBin(double num) {
        if (num == 0 || num == 1) {
            return String.valueOf(num);
        }
        StringBuilder sb = new StringBuilder("0.");
        double test = 0.5;
        while (num > 0) {
            if (sb.length() > 32) {
                return "ERROR";
            }
            if (num >= test) {
                sb.append('1');
                num -= test;
            } else {
                sb.append('0');
            }
            test /= 2;
        }
        return sb.toString();
    }
}
