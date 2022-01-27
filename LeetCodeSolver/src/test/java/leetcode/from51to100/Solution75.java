package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/13 10:50
 * @Description: 75. 颜色分类 | 难度：中等 | 标签：数组、双指针、排序
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution75 {
    @Test
    public void sortColorsTest() {
        int[] ints = {2, 0, 1};
        sortColors(ints);
        Assert.assertArrayEquals(new int[]{0, 1, 2}, ints);
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 37 MB , 在所有 Java 提交中击败了 43.65% 的用户
     * 通过测试用例： 87 / 87
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int i = 0;
        // 之所以需要在 i==r 的情况下继续 while，是因为 r 处的数字是 2 的边界（不是 2）；
        while (i <= r) {
            while (r > i && nums[r] == '2') {
                r--;
                if (r == i) {
                    return;
                }
            }
            if (nums[i] == 0) {
                nums[i++] = nums[l];
                nums[l++] = 0;
            } else if (nums[i] == 2) {
                // 交换后，i 位置上可能是 0 也可能是 1，所以不进行++
                nums[i] = nums[r];
                nums[r--] = 2;
            } else {
                i++;
            }
        }
    }
}
