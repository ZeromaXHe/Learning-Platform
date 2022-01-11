package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/11 18:16
 * @Description: 40. 组合总和 II | 难度：中等 | 标签：数组、回溯
 * 给你一个由候选元素组成的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个元素在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。 
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * <p>
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution40 {
    @Test
    public void combinationSumTest() {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 97.97% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 46.57% 的用户
     * 通过测试用例： 175 / 175
     * <p>
     * 根据第39题代码改过来的
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        LinkedList<Integer> test = new LinkedList<>();
        backtrack(candidates, candidates.length - 1, target, test, 0, result);
        return result;
    }

    private void backtrack(int[] candidates, int i, int target, LinkedList<Integer> test, int sum, List<List<Integer>> result) {
        if (candidates[i] + sum < target) {
            if (i - 1 >= 0) {
                test.addLast(candidates[i]);
                backtrack(candidates, i - 1, target, test, sum + candidates[i], result);
                test.removeLast();
            }
        } else if (candidates[i] + sum == target) {
            test.addLast(candidates[i]);
            result.add(new ArrayList<>(test));
            test.removeLast();
        }
        while (i - 1 >= 0 && candidates[i - 1] == candidates[i]) {
            i--;
        }
        if (i - 1 >= 0) {
            backtrack(candidates, i - 1, target, test, sum, result);
        }
    }
}
