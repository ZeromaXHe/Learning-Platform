package leetcode.from451to500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/2/3 14:21
 * @Description: 480.滑动窗口中位数 | 难度：困难 | 标签：滑动窗口
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * <p>
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 * 提示：
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution480 {
    /**
     * 执行用时： 281 ms , 在所有 Java 提交中击败了 14.15% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 47.99% 的用户
     * 待优化，目前是直接排序，所以复杂度达到O((n-k) * k * log k)
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        MedianArray medianArray = new MedianArray(Arrays.copyOf(nums, k));
        for (int i = k; i <= nums.length; i++) {
            result[i - k] = medianArray.getMedian();
            if (i != nums.length) {
                medianArray.removeAndInsert(nums[i - k], nums[i]);
            }
        }
        return result;
    }

    class MedianArray {
        private int[] array;

        MedianArray(int[] arr) {
            array = arr;
            Arrays.sort(array);
        }

        public double getMedian() {
            if (array.length % 2 == 0) {
                return ((double) array[array.length / 2] + array[array.length / 2 - 1]) / 2.0;
            } else {
                return array[array.length / 2];
            }
        }

        public void removeAndInsert(int remove, int insert) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == remove) {
                    array[i] = insert;
                    Arrays.sort(array);
                    break;
                }
            }
        }
    }

    @Test
    public void medianSlidingWindowTest() {
        Assert.assertArrayEquals(new double[]{1.0, -1.0, -1.0, 3.0, 5.0, 6.0}, medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), 0.001);
        Assert.assertArrayEquals(new double[]{2.5}, medianSlidingWindow(new int[]{1, 4, 2, 3}, 4), 0.001);
        Assert.assertArrayEquals(new double[]{2147483647}, medianSlidingWindow(new int[]{2147483647, 2147483647}, 2), 0.001);
    }
}
