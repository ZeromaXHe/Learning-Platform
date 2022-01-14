package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/14 10:42
 * @Description: 89. 格雷编码 | 难度：中等 | 标签:位运算、数学、回溯
 * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
 * 第一个整数是 0
 * 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：[0,1,3,2]
 * 解释：
 * [0,1,3,2] 的二进制表示是 [00,01,11,10] 。
 * - 00 和 01 有一位不同
 * - 01 和 11 有一位不同
 * - 11 和 10 有一位不同
 * - 10 和 00 有一位不同
 * [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
 * - 00 和 10 有一位不同
 * - 10 和 11 有一位不同
 * - 11 和 01 有一位不同
 * - 01 和 00 有一位不同
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[0,1]
 * <p>
 * 提示：
 * 1 <= n <= 16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution89 {
    @Test
    public void grayCodeTest() {
        System.out.println(grayCode(1));
        System.out.println(grayCode(2));
        // 通过测试用例： 2 / 16
        // [0,1,3,2,6,7,5,4]
        // 0, 1, 11, 10, 110, 111, 101, 100
        System.out.println(grayCode(3));
    }

    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 61.07% 的用户
     * 内存消耗： 45.8 MB , 在所有 Java 提交中击败了 16.22% 的用户
     * 通过测试用例： 16 / 16
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(1);
        if (n == 1) {
            return result;
        }
        int round = 1;
        int add = 2;
        while (round++ < n) {
            for (int i = add - 1; i >= 0; i--) {
                result.add(result.get(i) + add);
            }
            add <<= 1;
        }
        return result;
    }
}
