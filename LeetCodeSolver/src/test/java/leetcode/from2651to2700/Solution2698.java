package leetcode.from2651to2700;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 2698. 求一个整数的惩罚数 | 难度：中等 | 标签：数学、回溯
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 * <p>
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 * <p>
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 * <p>
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 * <p>
 * 提示：
 * 1 <= n <= 1000
 * @implNote
 * @since 2023/10/25 9:57
 */
public class Solution2698 {
    @Test
    public void punishmentNumberTest() {
        Assert.assertEquals(182, punishmentNumber(10));
    }

    /**
     * 时间 47 ms
     * 击败 40.94% 使用 Java 的用户
     * 内存 37.38 MB
     * 击败 82.28% 使用 Java 的用户
     *
     * @param n
     * @return
     */
    public int punishmentNumber(int n) {
        int result = 0;
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int sq = i * i;
            digits.clear();
            while (sq > 0) {
                digits.add(sq % 10);
                sq /= 10;
            }
            if (testSum(digits, digits.size() - 1, 0, 0, i)) {
                result += i * i;
            }
        }
        return result;
    }

    private boolean testSum(List<Integer> digits, int idx, int sum, int temp, int target) {
        if (idx == -1) {
            return sum + temp == target;
        }
        if (testSum(digits, idx - 1, sum, temp * 10 + digits.get(idx), target)) {
            return true;
        }
        return testSum(digits, idx - 1, sum + temp * 10 + digits.get(idx), 0, target);
    }
}
