package leetcode.lcr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote LCR 007. 三数之和 | 难度：中等 | 标签：数组、双指针、排序
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * 注意：本题与主站 15 题相同：https://leetcode-cn.com/problems/3sum/
 * @implNote
 * @since 2023/11/29 11:52
 */
public class Solution007 {
    /**
     * 执行用时分布 31 ms
     * 击败 16.19% 使用 Java 的用户
     * 消耗内存分布 46.84 MB
     * 击败 24.93% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                if (nums[l] + nums[r] == -nums[i]) {
                    ArrayList<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    while (l + 1 < n && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;
                    while (r - 1 > i && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                } else if (nums[l] + nums[r] < -nums[i]) {
                    while (l + 1 < n && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;
                } else {
                    while (r - 1 > i && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                }
            }
            while (i + 1 < n - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return result;
    }
}
