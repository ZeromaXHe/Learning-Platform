package leetcode.from51to100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/13 11:30
 * @Description: 77. 组合 | 难度：中等 | 标签：数组、回溯
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution77 {
    /**
     * 不剪枝的话会很慢（j < perm.size()）
     * <p>
     * 执行用时： 106 ms , 在所有 Java 提交中击败了 5.20% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 43.07% 的用户
     * 通过测试用例： 27 / 27
     * <p>
     * 终止条件使用剪枝后耗时好多了（j < perm.size() - k + i + 1）
     * <p>
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 59.77% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 41.38% 的用户
     * 通过测试用例： 27 / 27
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> perm = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            perm.add(i + 1);
        }
        combine(perm, result, 0, k);
        return result;
    }

    private void combine(List<Integer> perm, List<List<Integer>> result, int i, int k) {
        if (i == k) {
            result.add(new ArrayList<>(perm.subList(0, k)));
            return;
        }
        if (i == 0 || perm.get(i - 1) < perm.get(i)) {
            combine(perm, result, i + 1, k);
        }
        // j 的终止条件需要剪枝，j < perm.size()的话会慢很多
        for (int j = i + 1; j < perm.size() - k + i + 1; j++) {
            if (i == 0 || perm.get(i - 1) < perm.get(j)) {
                Collections.swap(perm, i, j);
                combine(perm, result, i + 1, k);
                Collections.swap(perm, i, j);
            }
        }
    }
}
