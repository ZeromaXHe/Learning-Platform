package leetcode.first100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/23 11:02
 * @Description: 11. 盛最多水的容器 | 难度：中等 | 标签：数组、双指针
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * <p>
 * 示例 4：
 * 输入：height = [1,2,1]
 * 输出：2
 *  
 * <p>
 * 提示：
 * n = height.length
 * 2 <= n <= 3 * 10^4
 * 0 <= height[i] <= 3 * 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution11 {
    /**
     * 执行用时: 5 ms
     * 内存消耗: 40.1 MB
     * 试了一下，速度反而慢了……
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = Math.min(height[l], height[r]) * (r - l);
        while (l < r) {
            while (l < r && height[l] <= height[r]) {
                l++;
                if (height[l] > height[l - 1]) {
                    max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
                    break;
                }
            }

            while (l < r && height[l] > height[r]) {
                r--;
                if (height[r] > height[r + 1]) {
                    max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
                    break;
                }
            }
        }
        return max;
    }
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 66.92% 的用户
     * 内存消耗： 39.7 MB , 在所有 Java 提交中击败了 69.81% 的用户
     * 相比于下面detailed的方法，可以看出，实际上left、right的height相等的情况是可以简化到left或right任意一个移动一下的情况
     * 原因就是如果left找到了更大的，下一步就会移动right了。反之同理，所以没必要单独分情况讨论height[left] = height[right]
     *
     * @param height
     * @return
     */
    public int maxArea_simple(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Math.min(height[left], height[right]) * (right - left);
        while (left < right) {
            if (height[left] <= height[right]) {
                left++;
                if (height[left] > height[left - 1]) {
                    max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
                }
            } else {
                right--;
                if (height[right] > height[right + 1]) {
                    max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
                }
            }
        }
        return max;
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 27.41% 的用户
     * 内存消耗： 40.1 MB , 在所有 Java 提交中击败了 35.67% 的用户
     *
     * @param height
     * @return
     */
    public int maxArea_detailed(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Math.min(height[left], height[right]) * (right - left);
        while (left < right) {
            while (left < right && height[left] < height[right]) {
                left++;
                if (height[left] > height[left - 1]) {
                    max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
//                    System.out.println("max:" + max + " left:" + left + " right:" + right);
                    break;
                }
            }

            while (left < right && height[left] > height[right]) {
                right--;
                if (height[right] > height[right + 1]) {
                    max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
//                    System.out.println("max:" + max + " left:" + left + " right:" + right);
                    break;
                }
            }

            if (height[left] == height[right]) {
                int higherLeft = left + 1;
                int higherRight = right - 1;
                while (higherLeft < right && height[higherLeft] <= height[left]) {
                    higherLeft++;
                }
                left = higherLeft;
                while (higherRight > left && height[higherRight] <= height[right]) {
                    higherRight--;
                }
                right = higherRight;
                if (left >= right) {
                    break;
                }
                max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
//                System.out.println("max:" + max + " left:" + left + " right:" + right);
            }
        }
        return max;
    }

    @Test
    public void maxAreaTest() {
        // 49
        Assert.assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        // 1
        Assert.assertEquals(1, maxArea(new int[]{1, 1}));
        // 16
        Assert.assertEquals(16, maxArea(new int[]{4, 3, 2, 1, 4}));
        // 4
        Assert.assertEquals(4, maxArea(new int[]{1, 2, 4, 3}));
        // 42
        Assert.assertEquals(42, maxArea(new int[]{4, 6, 4, 4, 6, 2, 6, 7, 11, 2}));
    }
}