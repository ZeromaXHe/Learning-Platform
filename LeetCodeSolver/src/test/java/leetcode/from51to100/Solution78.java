package leetcode.from51to100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/13 13:39
 * @Description: 78. 子集 | 难度：中等 | 标签：位运算、数组、回溯
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution78 {
    @Test
    public void subsetsTest() {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{0}));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 25.05% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 13.02% 的用户
     * 通过测试用例： 10 / 10
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> perm = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            perm.add(nums[i]);
        }
        subsets(perm, result, 0, nums.length);
        return result;
    }

    private void subsets(List<Integer> perm, List<List<Integer>> result, int i, int k) {
        result.add(new ArrayList<>(perm.subList(0, i)));
        if (i == k) {
            return;
        }
        if (i == 0 || perm.get(i - 1) < perm.get(i)) {
            subsets(perm, result, i + 1, k);
        }
        for (int j = i + 1; j < perm.size(); j++) {
            if (i == 0 || perm.get(i - 1) < perm.get(j)) {
                Collections.swap(perm, i, j);
                subsets(perm, result, i + 1, k);
                Collections.swap(perm, i, j);
            }
        }
    }
}
