package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 11:14
 * @Description: 面试题 08.04. 幂集 | 难度：中等 | 标签：位运算、数组、回溯
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * 输入： nums = [1,2,3]
 * 输出：
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/power-set-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_04 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 59.68% 的用户
     * 通过测试用例： 10 / 10
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        backtrace(nums, 0, result, temp);
        return result;
    }

    private void backtrace(int[] nums, int index, List<List<Integer>> result, LinkedList<Integer> temp) {
        if (index == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        backtrace(nums, index + 1, result, temp);
        temp.addLast(nums[index]);
        backtrace(nums, index + 1, result, temp);
        temp.removeLast();
    }
}
