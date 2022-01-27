package leetcode.from1to50;

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
    /**
     * 官方题解，划分数组的方法
     * 添加理解后的注释，但还是不怎么理解
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保证之后运行中，nums1.length <= nums2.length
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // m <= n
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            // i是nums1的中位（如果数组元素为偶数就是偏大的那个）
            int i = (left + right) / 2;
            // j是nums2的中位（如果数组元素为偶数就是偏小的那个）
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 22.92% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 59.06% 的用户
     * 参考别人的评论写的，递归寻找第K个数
     * 添加解题相关的注释
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays_recursive(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        // 对于m+n为奇数时，findKth(nums1, 0, nums2, 0, left)和findKth(nums1, 0, nums2, 0, right)两者相等
        // 对于m+n为偶数时，两者正好为中位数计算中的最中间两个数
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     *
     * @param nums1
     * @param start1 nums1的起始位置
     * @param nums2
     * @param start2 nums2的起始位置
     * @param k 希望寻找的组合数组第k个数（k从1开始）
     * @return
     */
    private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
        if( start1 >= nums1.length) {
            // nums1为空数组
            return nums2[start2 + k - 1];
        }
        if( start2 >= nums2.length) {
            // nums2为空数组
            return nums1[start1 + k - 1];
        }
        if(k == 1){
            // 只需比较nums1，nums2的起始元素
            return Math.min(nums1[start1], nums2[start2]);
        }
        // 为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素
        // 注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字
        // 如果存在就取出来，否则就赋值上一个整型最大值。
        int midVal1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
        // 比较这两个数组的第K/2小的数字midVal1和midVal2的大小，如果第一个数组的第K/2个数字小的话
        // 那么说明我们要找的数字肯定不在nums1中的前K/2个数字
        // 所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
        // 如果某个数组没有第K/2个数字（midVal = Integer.MAX_VALUE），那么我们就淘汰另一个数组的前K/2个数字即可。
        if(midVal1 < midVal2){
            return findKth(nums1, start1 + k / 2, nums2, start2 , k - k / 2);
        }else{
            return findKth(nums1, start1, nums2, start2 + k / 2 , k - k / 2);
        }
    }

    public double findMedianSortedArrays_wrongThinking(int[] nums1, int[] nums2) {
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
