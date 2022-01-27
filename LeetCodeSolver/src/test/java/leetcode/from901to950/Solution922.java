package leetcode.from901to950;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2020/11/12 12:45
 * @Description: 922.按奇偶排序数组 II | 难度：简单 | 标签：排序、数组
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * 提示：
 * 1. 2 <= A.length <= 20000
 * 2. A.length % 2 == 0
 * 3. 0 <= A[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution922 {
    /**
     * 执行用时： 3 ms, 在所有 Java 提交中击败了 78.68% 的用户
     * 内存消耗： 39.7 MB, 在所有 Java 提交中击败了 88.88% 的用户
     * 双指针加swap
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1;
        while (i < A.length && j < A.length) {
            while (i < A.length && A[i] % 2 == 0) {
                i += 2;
            }
            if (i >= A.length) {
                break;
            }
            while (j < A.length && A[j] % 2 == 1) {
                j += 2;
            }
            // 按题目来说，一半奇数一半偶数的话，这个if只要能走到，永远为false。
            if (j >= A.length) {
                break;
            }
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
        return A;
    }

    public static void main(String[] args) {
        Solution922 solution922 = new Solution922();
        System.out.println(Arrays.toString(solution922.sortArrayByParityII(new int[]{4, 2, 5, 7})));
    }
}
