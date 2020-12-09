package leetcode.first100;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/1 17:53
 * @Description: 4. 寻找两个正序数组的中位数 | 难度：困难 | 标签：数组、二分查找、分治算法
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * <p>
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * <p>
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length == 0) {
            if (nums1.length % 2 == 0) {
                return ((double) nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2;
            } else {
                return nums1[nums1.length / 2];
            }
        }
        if (nums1.length == 0) {
            if (nums2.length % 2 == 0) {
                return ((double) nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2;
            } else {
                return nums2[nums2.length / 2];
            }
        }
        if (nums1[nums1.length - 1] <= nums2[0]) {
            // nums1全部小于等于nums2
            if ((nums1.length + nums2.length) % 2 == 1) {
                // 总数为奇数
                if (nums1.length <= (nums1.length + nums2.length) / 2) {
                    return nums2[(nums1.length + nums2.length) / 2 - nums1.length];
                } else {
                    return nums1[(nums1.length + nums2.length) / 2];
                }
            } else {
                // 总数为偶数
                if (nums1.length == nums2.length) {
                    return ((double) nums1[nums1.length - 1] + nums2[0]) / 2;
                } else if (nums1.length < nums2.length) {
                    return ((double) nums2[(nums2.length + nums1.length) / 2 - nums1.length] + nums2[(nums2.length + nums1.length) / 2 - 1 - nums1.length]) / 2;
                } else {
                    return ((double) nums1[(nums2.length + nums1.length) / 2] + nums1[(nums2.length + nums1.length) / 2 - 1]) / 2;
                }

            }
        }
        if (nums2[nums2.length - 1] <= nums1[0]) {
            // nums2全部小于等于nums1
            if ((nums1.length + nums2.length) % 2 == 1) {
                // 总数为奇数
                if (nums2.length <= (nums1.length + nums2.length) / 2) {
                    return nums1[(nums1.length + nums2.length) / 2 - nums2.length];
                } else {
                    return nums2[(nums1.length + nums2.length) / 2];
                }
            } else {
                // 总数为偶数
                if (nums1.length == nums2.length) {
                    return ((double) nums2[nums2.length - 1] + nums1[0]) / 2;
                } else if(nums2.length < nums1.length){
                    return ((double) nums1[(nums1.length + nums2.length) / 2 - nums2.length] + nums1[(nums2.length + nums1.length) / 2 - 1 - nums2.length]) / 2;
                } else{
                    return ((double) nums2[(nums2.length + nums1.length) / 2] + nums2[(nums2.length + nums1.length) / 2 - 1]) / 2;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.findMedianSortedArrays(new int[]{1}, new int[]{}) + " = 1");
        System.out.println(solution4.findMedianSortedArrays(new int[]{1, 2}, new int[]{}) + " = 1.5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{}, new int[]{1}) + " = 1");
        System.out.println(solution4.findMedianSortedArrays(new int[]{}, new int[]{1, 2}) + " = 1.5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) + " = 2.5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5}) + " = 3");
        System.out.println(solution4.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6}) + " = 3.5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{0, 1, 2}, new int[]{3, 4}) + " = 2");
        System.out.println(solution4.findMedianSortedArrays(new int[]{5, 6}, new int[]{3, 4}) + " = 4.5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{5, 6, 7}, new int[]{3, 4}) + " = 5");
        System.out.println(solution4.findMedianSortedArrays(new int[]{5, 6}, new int[]{2, 3, 4}) + " = 4");
        System.out.println(solution4.findMedianSortedArrays(new int[]{5, 6}, new int[]{1, 2, 3, 4}) + " = 3.5");
    }
}
