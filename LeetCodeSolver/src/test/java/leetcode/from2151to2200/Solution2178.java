package leetcode.from2151to2200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 2178. 拆分成最多数目的正偶数之和 | 难度：中等 | 标签：贪心、数学、回溯
 * 给你一个整数 finalSum 。请你将它拆分成若干个 互不相同 的正偶数之和，且拆分出来的正偶数数目 最多 。
 * <p>
 * 比方说，给你 finalSum = 12 ，那么这些拆分是 符合要求 的（互不相同的正偶数且和为 finalSum）：(2 + 10) ，(2 + 4 + 6) 和 (4 + 8) 。
 * 它们中，(2 + 4 + 6) 包含最多数目的整数。注意 finalSum 不能拆分成 (2 + 2 + 4 + 4) ，因为拆分出来的整数必须互不相同。
 * 请你返回一个整数数组，表示将整数拆分成 最多 数目的正偶数数组。如果没有办法将 finalSum 进行拆分，请你返回一个 空 数组。你可以按 任意 顺序返回这些整数。
 * <p>
 * 示例 1：
 * 输入：finalSum = 12
 * 输出：[2,4,6]
 * 解释：以下是一些符合要求的拆分：(2 + 10)，(2 + 4 + 6) 和 (4 + 8) 。
 * (2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
 * [2,6,4] ，[6,2,4] 等等也都是可行的解。
 * <p>
 * 示例 2：
 * 输入：finalSum = 7
 * 输出：[]
 * 解释：没有办法将 finalSum 进行拆分。
 * 所以返回空数组。
 * <p>
 * 示例 3：
 * 输入：finalSum = 28
 * 输出：[6,8,2,12]
 * 解释：以下是一些符合要求的拆分：(2 + 26)，(6 + 8 + 2 + 12) 和 (4 + 24) 。
 * (6 + 8 + 2 + 12) 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
 * [10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
 * <p>
 * 提示：
 * 1 <= finalSum <= 1010
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-split-of-positive-even-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/6 9:37
 */
public class Solution2178 {
    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了 97.96% 的用户
     * 内存消耗：53.9 MB, 在所有 Java 提交中击败了 77.55% 的用户
     * 通过测试用例：56 / 56
     *
     * @param finalSum
     * @return
     */
    public List<Long> maximumEvenSplit(long finalSum) {
        ArrayList<Long> result = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return result;
        }
        long sum = 0;
        for (long i = 2; sum + i <= finalSum; i += 2) {
            sum += i;
            result.add(i);
        }
        result.set(result.size() - 1, result.get(result.size() - 1) + finalSum - sum);
        return result;
    }

    /**
     * 执行用时：23 ms, 在所有 Java 提交中击败了 8.16% 的用户
     * 内存消耗：59.4 MB, 在所有 Java 提交中击败了 18.37% 的用户
     * 通过测试用例：56 / 56
     *
     * @param finalSum
     * @return
     */
    public List<Long> maximumEvenSplit_slow(long finalSum) {
        LinkedList<Long> result = new LinkedList<>();
        if (finalSum % 2 == 1) {
            return result;
        }
        long sum = 2L;
        result.push(2L);
        while (sum != finalSum) {
            if (sum > finalSum) {
                sum -= result.pop();
                if (result.isEmpty()) {
                    break;
                }
                long pop = result.pop();
                sum += 2;
                result.push(pop + 2);
            } else {
                long test = result.peek() + 2;
                sum += test;
                result.push(test);
            }
        }
        return result;
    }
}
