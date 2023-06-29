package leetcode.from1251to1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 1253. 重构 2 行二进制矩阵 | 难度：中等 | 标签：贪心、数组、矩阵
 * 给你一个 2 行 n 列的二进制数组：
 * <p>
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
 * 第 0 行的元素之和为 upper。
 * 第 1 行的元素之和为 lower。
 * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
 * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
 * <p>
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 * <p>
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 * <p>
 * 示例 1：
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 * <p>
 * 示例 2：
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * <p>
 * 提示：
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/29 10:07
 */
public class Solution1253 {
    /**
     * 看题解的评论区有人提到：
     * 遇到 colsum 是 1 的时候，选择 upper 和 lower 中大的那个赋 1 就行了。
     * 因为这样做可以最大限度地避免 upper 和 lower 不够用。如果有解，这样一定可以找到解。
     * <p>
     * 感觉确实是这样，这样一次遍历就可以了。但自己做的时候还是两次遍历做的。
     * <p>
     * 执行用时：10 ms, 在所有 Java 提交中击败了 61.96% 的用户
     * 内存消耗：57.4 MB, 在所有 Java 提交中击败了 71.74% 的用户
     * 通过测试用例：69 / 69
     *
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> result = new ArrayList<>(2);
        int n = colsum.length;
        List<Integer> upperList = new ArrayList<>(n);
        List<Integer> lowerList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                upper--;
                lower--;
                upperList.add(1);
                lowerList.add(1);
            } else {
                upperList.add(0);
                lowerList.add(0);
            }
        }
        if (upper < 0 || lower < 0) {
            return result;
        }
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1) {
                if (upper > 0) {
                    upperList.set(i, 1);
                    upper--;
                } else {
                    lowerList.set(i, 1);
                    lower--;
                }
            }
        }
        if (upper != 0 || lower != 0) {
            return result;
        }
        result.add(upperList);
        result.add(lowerList);
        return result;
    }
}
