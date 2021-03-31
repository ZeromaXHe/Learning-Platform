package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2021/3/31 9:39
 * @Description: 90.子集II | 难度：中等 | 标签：数组、回溯算法
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution90 {
    private List<Integer> temp;
    private Set<List<Integer>> result;

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 24.13% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 5.28% 的用户
     * <p>
     * 效率比较低，因为完全依赖哈希集合做的去重，每次重复的子集也都在重建ArrayList。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        temp = new ArrayList<>(nums.length);
        result = new HashSet<>();
        Arrays.sort(nums);
        result.add(new ArrayList<>(temp));
        dfs(nums, 0);
        return new ArrayList<>(result);
    }

    private void dfs(int[] nums, int from) {
        for (int i = from; i < nums.length; i++) {
            temp.add(nums[i]);
            result.add(new ArrayList<>(temp));
            if (from < nums.length - 1) {
                dfs(nums, i + 1);
            }
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void subsetsWithDupTest() {
        System.out.println(subsetsWithDup(new int[]{0, 1, 1}));
    }
}
