package leetcode.from1to50;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/8 10:31
 * @Description: 42.接雨水 | 难度：困难 | 标签：栈、数组、双指针
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution42 {
    /**
     * 双指针法，官方题解太难理解了，按照下面评论区的python代码改的
     * 这个思路很清晰：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/327718
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.81% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 41.25% 的用户
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 定理一：在某个位置i处，它能存的水，取决于它左右两边的最大值中较小的一个。
        // 定理二：当我们从左往右处理到l下标时，左边的最大值maxL对它而言是可信的，但maxR对它而言是不可信的。
        // （由于中间状况未知，对于l下标而言，maxR未必就是它右边最大的值）
        // 定理三：当我们从右往左处理到r下标时，右边的最大值maxR对它而言是可信的，但maxL对它而言是不可信的。
        int l = 0;
        int r = height.length - 1;
        int result = 0;
        int maxL = 0;
        int maxR = 0;
        while (l <= r) {
            if (maxL < maxR) {
                result += Math.max(0, maxL - height[l]);
                maxL = Math.max(maxL, height[l]);
                l++;
            } else {
                result += Math.max(0, maxR - height[r]);
                maxR = Math.max(maxR, height[r]);
                r--;
            }
        }
        return result;
    }
}
