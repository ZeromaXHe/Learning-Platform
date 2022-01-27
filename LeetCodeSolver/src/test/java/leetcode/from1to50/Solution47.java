package leetcode.from1to50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 11:50
 * @Description: 47. 全排列 II | 难度：中等 | 标签：数组、回溯
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution47 {
    @Test
    public void permuteUniqueTest() {
        // [[1,1,2],[1,2,1],[2,1,1]]
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
        // 通过测试用例： 14 / 33
        // [[1,1,2,2],[1,2,1,2],[1,2,2,1],[2,1,1,2],[2,1,2,1],[2,2,1,1]]
        System.out.println(permuteUnique(new int[]{2, 2, 1, 1}));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.32% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 23.93% 的用户
     * 通过测试用例： 33 / 33
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visit = new boolean[nums.length];
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> perm = new ArrayList<>(nums.length);
        Arrays.sort(nums);
        backtrack(perm, result, nums, visit);
        return result;
    }

    private void backtrack(List<Integer> perm, List<List<Integer>> result, int[] nums, boolean[] visit) {
        // 所有数都填完了
        if (perm.size() == nums.length) {
            // 注意需要重新建个List来add，不然放的就是引用了
            result.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            visit[i] = true;
            backtrack(perm, result, nums, visit);
            visit[i] = false;
            perm.remove(perm.size() - 1);
        }
    }
}
