package leetcode.from451to500;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/28 15:42
 * @Description: 493.翻转对 | 难度：困难 | 标签：排序、树状数组、线段树、二分查找、分治算法
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * <p>
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution493 {
    /**
     * 归并排序解法
     * 自己是没想到这种解法的，想到的只有树形解法，而且只想得到平衡二叉搜索树。
     * 但是自己又懒得实现红黑树这些了，所以先复制题解看懂思路吧
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-pairs/solution/fan-zhuan-dui-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 执行用时：57 ms, 在所有 Java 提交中击败了 69.06% 的用户
     * 内存消耗：47.6 MB, 在所有 Java 提交中击败了 77.17% 的用户
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    private int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            // 在归并排序的过程中，假设对于数组 nums[l..r] 而言，
            // 我们已经分别求出了子数组 nums[l..m] 与 nums[m+1..r] 的翻转对数目，并已将两个子数组分别排好序，
            //
            // 则 nums[l..r] 中的翻转对数目，
            // 就等于两个子数组的翻转对数目之和，加上左右端点分别位于两个子数组的翻转对数目。
            int mid = (left + right) / 2;
            int n1 = reversePairsRecursive(nums, left, mid);
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            // 两个子数组的翻转对数目之和
            int ret = n1 + n2;

            // 接下来算左右端点分别位于两个子数组的翻转对数目
            // 首先统计下标对的数量
            int i = left;
            int j = mid + 1;
            // 我们可以为两个数组分别维护指针 i,j。
            // 对于任意给定的 i 而言，我们不断地向右移动 j，直到 nums[i]≤2⋅nums[j]。
            // 此时，意味着以 i 为左端点的翻转对数量为 j−m−1。
            // 随后，我们再将 i 向右移动一个单位，并用相同的方式计算以 i 为左端点的翻转对数量。
            // 不断重复这样的过程，就能够求出所有左右端点分别位于两个子数组的翻转对数目。
            while (i <= mid) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                ret += j - mid - 1;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            // 双指针排序
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = nums[p2++];
                } else if (p2 > right) {
                    sorted[p++] = nums[p1++];
                } else {
                    if (nums[p1] < nums[p2]) {
                        sorted[p++] = nums[p1++];
                    } else {
                        sorted[p++] = nums[p2++];
                    }
                }
            }
            // 把排好序的数组数据刷入nums
            for (int k = 0; k < sorted.length; k++) {
                nums[left + k] = sorted[k];
            }
            return ret;
        }
    }

    /**
     * 树形思想的解法
     *
     * @param nums
     * @return
     */
    public int reversePairs_treeVersion(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            // 计算"特殊数据结构"内存在大于2*nums[i]的数量，加在result中。
            // 将nums[i]放入"特殊数据结构"，该结构会将放入的数字排序。
        }

        // ”特殊数据结构“需要实现的功能：
        // （1）查询其中元素大于某个值的数量（时间复杂度需为O(logN）)
        // （2）对插入的元素进行排序（时间复杂度O(logN)）
        // 可见，ArrayList和LinkedList都不符合要求，ArrayList插入O(N),LinkedList查询O(N)
        // 那我们就可以想到应该要用树型结构来存储
        // 这里显然是需要一个二叉搜索树，而且需要在插入时有自平衡功能，以保证不会出现特别偏的情况导致O(N)
        // 其次需要在插入过程中在节点上记录有多少个大于自己的节点。
        //
        // 可以看出几种题解出现的思路：平衡二叉搜索树、树状数组、线段树都是在往树形的思想上靠。
        // TODO：真正实现一下
        return result;
    }

}
