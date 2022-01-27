package leetcode.from851to900;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/2/28 18:43
 * @Description: 896. 单调数列 | 难度：简单 | 标签：数组
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：[1,2,2,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：[6,5,4,4]
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：[1,3,2]
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：[1,2,4,5]
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：[1,1,1]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution896 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 46.7 MB , 在所有 Java 提交中击败了 56.78% 的用户
     *
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        boolean incre = false;
        boolean decre = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                if (decre) {
                    return false;
                }
                incre = true;
            } else if (A[i] < A[i - 1]) {
                if (incre) {
                    return false;
                }
                decre = true;
            }
        }
        return true;
    }
}
