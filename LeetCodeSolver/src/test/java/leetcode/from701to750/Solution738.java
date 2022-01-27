package leetcode.from701to750;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/15 9:36
 * @Description: 738. 单调递增的数字 | 难度：中等 | 标签：贪心算法
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * <p>
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * <p>
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution738 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.96% 的用户
     * 内存消耗： 35.4 MB , 在所有 Java 提交中击败了 57.84% 的用户
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chars = Integer.toString(N).toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < chars[i - 1]) {
                int j = i - 1;
                while (j - 1 >= 0 && chars[j] == chars[j - 1]) {
                    j--;
                }
                chars[j] = (char) (chars[j] - 1);
                while (j + 1 < chars.length) {
                    chars[++j] = '9';
                }
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
