package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/11 17:20
 * @Description: 39. 组合总和 | 难度：中等 | 标签：数组、回溯
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * <p>
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * <p>
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution39 {
    @Test
    public void combinationSumTest() {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 58.60% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 87.06% 的用户
     * 通过测试用例： 170 / 170
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        LinkedList<Integer> test = new LinkedList<>();
        backtrack(candidates, candidates.length - 1, target, test, 0, result);
        return result;
    }

    private void backtrack(int[] candidates, int i, int target, LinkedList<Integer> test, int sum, List<List<Integer>> result) {
        if (candidates[i] + sum < target) {
            test.addLast(candidates[i]);
            backtrack(candidates, i, target, test, sum + candidates[i], result);
            test.removeLast();
        } else if (candidates[i] + sum == target) {
            test.addLast(candidates[i]);
            result.add(new ArrayList<>(test));
            test.removeLast();
        }
        if (i - 1 >= 0) {
            backtrack(candidates, i - 1, target, test, sum, result);
        }
    }
}
