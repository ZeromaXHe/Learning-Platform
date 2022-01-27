package leetcode.from201to250;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/10 17:28
 * @Description: 228.汇总区间 | 难度：简单 | 标签：数组
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 * <p>
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 * <p>
 * 提示：
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution228 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 30.83% 的用户
     * 内存消耗： 37.2 MB , 在所有 Java 提交中击败了 8.80% 的用户
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new LinkedList<>();
        if (nums.length == 0) {
            return list;
        }
        if (nums.length == 1) {
            list.add("" + nums[0]);
            return list;
        }
        int preStart = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i] - 1) {
                if (preStart == nums[i - 1]) {
                    list.add("" + preStart);
                } else {
                    list.add("" + preStart + "->" + nums[i - 1]);
                }
                preStart = nums[i];
            }
        }
        if (preStart == nums[nums.length - 1]) {
            list.add("" + preStart);
        } else {
            list.add("" + preStart + "->" + nums[nums.length - 1]);
        }
        return list;
    }
}