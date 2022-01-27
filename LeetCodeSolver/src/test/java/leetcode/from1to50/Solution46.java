package leetcode.from1to50;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/12 10:58
 * @Description: 46. 全排列 | 难度：中等 | 标签：数组、回溯
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution46 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.23% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 22.56% 的用户
     * 通过测试用例： 26 / 26
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> perm = new ArrayList<>(nums.length);
        for (int num : nums) {
            perm.add(num);
        }

        backtrack(perm, result, 0);
        return result;
    }

    private void backtrack(List<Integer> perm, List<List<Integer>> result, int index) {
        // 所有数都填完了
        if (index == perm.size()) {
            // 注意需要重新建个List来add，不然放的就是引用了
            result.add(new ArrayList<>(perm));
        }
        for (int i = index; i < perm.size(); i++) {
            // 动态维护数组
            Collections.swap(perm, index, i);
            // 继续递归填下一个数
            backtrack(perm, result, index + 1);
            // 撤销操作
            Collections.swap(perm, index, i);
        }
    }
}
