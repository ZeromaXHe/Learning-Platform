package leetcode.from701to750;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/3/31 15:04
 * @Description: 728. 自除数 | 难度：简单 | 标签：数学
 * 自除数 是指可以被它包含的每一位数整除的数。
 * <p>
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * <p>
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * <p>
 * 示例 1：
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * <p>
 * 示例 2:
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 * <p>
 * 提示：
 * 1 <= left <= right <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-dividing-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution728 {
    @Test
    public void selfDividingNumbersTest() {
        System.out.println(selfDividingNumbers(1, 22));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 67.50% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 22.91% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new LinkedList<>();
        loop:
        for (int i = left; i <= right; i++) {
            int num = i;
            while (num > 0) {
                if (num % 10 == 0) {
                    continue loop;
                }
                if (i % (num % 10) != 0) {
                    continue loop;
                }
                num /= 10;
            }
            result.add(i);
        }
        return result;
    }
}
