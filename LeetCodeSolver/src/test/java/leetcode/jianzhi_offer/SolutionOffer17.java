package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 17. 打印从1到最大的n位数 | 难度：简单 | 标签：数组、数学
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 * <p>
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 11:17
 */
public class SolutionOffer17 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 71.68% 的用户
     * 内存消耗：50.9 MB, 在所有 Java 提交中击败了 19.81% 的用户
     * 通过测试用例：5 / 5
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int count = 1;
        for (int i = 0; i < n; i++) {
            count *= 10;
        }
        count -= 1;
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = i + 1;
        }
        return result;
    }
}
