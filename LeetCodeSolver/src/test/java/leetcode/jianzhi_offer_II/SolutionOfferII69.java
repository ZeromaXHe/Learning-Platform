package leetcode.jianzhi_offer_II;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 10:14
 * @Description: 剑指 Offer II 069. 山峰数组的顶部 | 难度：简单 | 标签：数组、二分查找
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * <p>
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * <p>
 * 示例 5：
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 * <p>
 * 提示：
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 * <p>
 * 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
 * <p>
 * 注意：本题与主站 852 题相同：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/B1IidL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class SolutionOfferII69 {
    @Test
    public void testPeakIndexInMountainArray() {
        Assert.assertEquals(1, peakIndexInMountainArray(new int[]{0, 1, 0}));
        Assert.assertEquals(2, peakIndexInMountainArray(new int[]{1, 3, 5, 4, 2}));
        Assert.assertEquals(1, peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
        Assert.assertEquals(2, peakIndexInMountainArray(new int[]{3, 4, 5, 1}));
        Assert.assertEquals(2, peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 5.74% 的用户
     * 通过测试用例： 34 / 34
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int len = arr.length;
        int mid = len / 2;
        int left = 1;
        int right = len - 2;
        while (left < right) {
            boolean leftLess = arr[mid - 1] < arr[mid];
            boolean rightLess = arr[mid] > arr[mid + 1];
            if (leftLess && rightLess) {
                break;
            } else if (leftLess) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }
        return mid;
    }
}
