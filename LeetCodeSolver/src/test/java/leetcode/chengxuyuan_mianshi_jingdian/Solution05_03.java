package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/7 18:56
 * @Description: 面试题 05.03. 翻转数位 | 难度：简单 | 标签：位运算、动态规划
 * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 * <p>
 * 示例 1：
 * 输入: num = 1775(11011101111_2)
 * 输出: 8
 * <p>
 * 示例 2：
 * 输入: num = 7(0111_2)
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_03 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 23.14% 的用户
     * 通过测试用例： 77 / 77
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        if (num == -1) {
            // 不返回 33
            return 32;
        }
        int before = 0;
        int now = 0;
        int max = 1;
        while (num != 0) {
            if ((num & 1) == 1) {
                now++;
            } else {
                max = Math.max(max, now + 1 + before);
                before = now;
                now = 0;
            }
            // 会有负数，所以必须使用逻辑右移（一律用 0 填补最左边）
            num >>>= 1;
        }
        return Math.max(max, now + 1 + before);
    }
}
