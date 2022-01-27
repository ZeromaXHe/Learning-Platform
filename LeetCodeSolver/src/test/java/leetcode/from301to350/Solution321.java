package leetcode.from301to350;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/2 15:55
 * @Description: 321.拼接最大数 | 难度：困难 | 标签：贪心算法、动态规划
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * <p>
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * <p>
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution321 {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了 97.00% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了 32.32% 的用户
     * 参考了题解的思路和单调栈的实现
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        for (int count1 = 0; count1 <= k; count1++) {
            // count1 为 nums1 提供元素的个数
            if (nums2.length < k - count1 || nums1.length < count1) {
                continue;
            }
            int[] nums1max = findMaxArr(nums1, count1);
            int[] nums2max = findMaxArr(nums2, k - count1);
            int[] temp = mergeMax(nums1max, nums2max);
            if (compareArr(temp, 0, max, 0) > 0) {
                max = temp;
            }
        }
        return max;
    }

    private int[] mergeMax(int[] arr1, int[] arr2) {
        if (arr1.length == 0) {
            return arr2;
        }
        if (arr2.length == 0) {
            return arr1;
        }
        int[] result = new int[arr1.length + arr2.length];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
//            if (arr1[index1] > arr2[index2]) {
//                result[index++] = arr1[index1++];
//            } else if (arr1[index1] < arr2[index2]) {
//                result[index++] = arr2[index2++];
//            } else {
//                // 相等
            int compare = compareArr(arr1, index1, arr2, index2);
            if (compare == 1) {
                result[index++] = arr1[index1++];
            } else {
                result[index++] = arr2[index2++];
            }
//            }
        }
        // 下面两个while只会执行一个
        while (index1 < arr1.length) {
            result[index++] = arr1[index1++];
        }
        while (index2 < arr2.length) {
            result[index++] = arr2[index2++];
        }
        return result;
    }

    /**
     * 题解中拿过来的，注释是自己看懂后加的
     *
     * @param arr 可供选择元素的数组
     * @param len 最大序列的长度
     * @return 指定长度的由arr中顺序的元素排列的最大序列
     */
    private int[] findMaxArr(int[] arr, int len) {
        if (len == 0) {
            return new int[0];
        }
        int length = arr.length;
        // 单调栈
        // 感觉原理就是从左到右，把逆序的元素从栈中弹出，弹出的数量达到指定的数量也就不再处理了。
        int[] stack = new int[len];
        int top = -1;
        int remain = length - len;
        for (int i = 0; i < length; i++) {
            int num = arr[i];
            // 单调栈中非空，且栈顶元素小于num，且待弹出的元素数量大于0
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            // 如果单调栈没满，将num压入栈
            if (top < len - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    @Test
    public void findMaxArr_tester() {
        System.out.println(Arrays.toString(findMaxArr(new int[]{9, 1, 2, 6, 3, 8}, 5)));
        System.out.println(Arrays.toString(findMaxArr(new int[]{6, 0, 4}, 3)));
    }

    private int compareArr(int[] arr1, int index1, int[] arr2, int index2) {
        int index = 0;
        while (arr1.length > index1 + index && arr2.length > index2 + index) {
            if (arr1[index1 + index] > arr2[index2 + index]) {
                return 1;
            } else if (arr1[index1 + index] < arr2[index2 + index]) {
                return -1;
            }
            index++;
        }
        if (arr1.length == index1 + index && arr2.length == index2 + index) {
            return 0;
        } else if (arr1.length == index1 + index) {
            return -1;
        } else {
            // arr2.length == index
            return 1;
        }
    }

    public static void main(String[] args) {
        Solution321 solution321 = new Solution321();
        System.out.println(Arrays.toString(solution321.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));
        //[6,7]
        //[6,0,4]
        //5
        System.out.println(Arrays.toString(solution321.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));
    }
}
