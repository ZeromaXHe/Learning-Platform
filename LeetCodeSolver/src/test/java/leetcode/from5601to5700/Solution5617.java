package leetcode.from5601to5700;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/6 10:31
 * @Description: 5617. 设计 Goal 解析器
 * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
 *
 * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
 *
 * 示例 1：
 * 输入：command = "G()(al)"
 * 输出："Goal"
 * 解释：Goal 解析器解释命令的步骤如下所示：
 * G -> G
 * () -> o
 * (al) -> al
 * 最后连接得到的结果是 "Goal"
 *
 * 示例 2：
 * 输入：command = "G()()()()(al)"
 * 输出："Gooooal"
 *
 * 示例 3：
 * 输入：command = "(al)G(al)()()G"
 * 输出："alGalooG"
 *
 * 提示：
 * 1 <= command.length <= 100
 * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/goal-parser-interpretation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5617 {
    /**
     * 105 / 105 个通过测试用例
     * 状态：通过
     * 执行用时: 5 ms
     * 内存消耗: 37.9 MB
     * @param command
     * @return
     */
    public String interpret(String command) {
        return command.replaceAll("\\(al\\)","al")
                .replaceAll("\\(\\)","o");
    }
}
