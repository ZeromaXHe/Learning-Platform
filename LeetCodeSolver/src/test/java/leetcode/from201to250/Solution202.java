package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: zhuxi
 * @Time: 2022/1/27 18:22
 * @Description: 202. 快乐数 | 难度：简单 | 标签：哈希表、数学、双指针
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」 定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * <p>
 * 示例 1：
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution202 {
    @Test
    public void isHappyTest() {
        Assert.assertTrue(isHappy(19));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 86.03% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 5.01% 的用户
     * 通过测试用例： 402 / 402
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            int test = n;
            int next = 0;
            while (test != 0) {
                next += (test % 10) * (test % 10);
                test /= 10;
            }
            n = next;
        }
        return n == 1;
    }
}
