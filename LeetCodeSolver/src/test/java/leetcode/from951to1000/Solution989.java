package leetcode.from951to1000;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/22 10:03
 * @Description: 989.数组形式的整数加法 | 难度：简单 | 标签：数组
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * <p>
 * 示例 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * <p>
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * <p>
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * <p>
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution989 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 86.63% 的用户
     * 内存消耗： 40.5 MB , 在所有 Java 提交中击败了 21.25% 的用户
     *
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        int carry = 0;
        int digitK = digit(K);
        int k = K;
        LinkedList<Integer> sum = new LinkedList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            int sumDigit = A[i] + k % 10 + carry;
            carry = sumDigit / 10;
            sum.addFirst(sumDigit % 10);
            k /= 10;
        }
        while (k > 0) {
            int sumDigit = k % 10 + carry;
            carry = sumDigit / 10;
            sum.addFirst(sumDigit % 10);
            k /= 10;
        }
        if (carry != 0) {
            sum.addFirst(carry);
        }
        return sum;
    }

    private int digit(int k) {
        int result = 0;
        while (k > 0) {
            k /= 10;
            result++;
        }
        return result;
    }
}
