package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/10 16:02
 * @Description: 面试题 05.07. 配对交换 | 难度：简单 | 标签：位运算
 * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
 * <p>
 * 示例1:
 * 输入：num = 2（或者0b10）
 * 输出 1 (或者 0b01)
 * <p>
 * 示例2:
 * 输入：num = 3
 * 输出：3
 * <p>
 * 提示:
 * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/exchange-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_07 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 75.55% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param num
     * @return
     */
    public int exchangeBits(int num) {
        return ((num & 0xAAAAAAAA) >>> 1) | ((num & 0x55555555) << 1);
    }
}
