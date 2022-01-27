package leetcode.from451to500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/10/26 9:50
 * @Description: 496. 下一个更大元素 I | 难度：简单 | 标签：栈、数组、哈希表、单调栈
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * <p>
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * <p>
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * <p>
 * 提示：
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution496 {

    @Test
    public void nextGreaterElementTest() {
        Assert.assertArrayEquals(new int[]{-1, 3, -1}, nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
        Assert.assertArrayEquals(new int[]{3, -1}, nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 98.82% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 100.00% 的用户
     * 通过测试用例： 15 / 15
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        int after = -1;
        LinkedList<Integer> afterStack = new LinkedList<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            if (nums2[i] > after) {
                while (!afterStack.isEmpty() && nums2[i] > afterStack.peek()) {
                    afterStack.pop();
                }
                if (!afterStack.isEmpty()) {
                    Integer index = map.get(nums2[i]);
                    if (index != null) {
                        result[index] = afterStack.peek();
                    }
                }
            } else {
                Integer index = map.get(nums2[i]);
                if (index != null) {
                    result[index] = after;
                }
                afterStack.push(after);
            }
            after = nums2[i];
        }
        return result;
    }
}
