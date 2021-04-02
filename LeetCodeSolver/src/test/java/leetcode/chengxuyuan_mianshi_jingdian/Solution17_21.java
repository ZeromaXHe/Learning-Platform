package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2021/4/2 10:11
 * @Description: 面试题17.21 直方图的水量 | 难度：困难 | 标签：栈、数组、双指针
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution17_21 {
    /**
     * 同力扣第42题 接雨水
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.90% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 16.78% 的用户
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
