package leetcode.from901to1000;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/29 12:41
 * @Description: 976. 三角形的最大周长 | 难度：简单 | 标签：排序、数学
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * <p>
 * 如果不能形成任何面积不为零的三角形，返回 0。
 * <p>
 * 示例 1：
 * 输入：[2,1,2]
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：[1,2,1]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：[3,2,3,4]
 * 输出：10
 * <p>
 * 示例 4：
 * 输入：[3,6,2,3]
 * 输出：8
 *  
 * 提示：
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-perimeter-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution976 {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了 97.44% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了 76.07% 的用户
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 1] + A[i - 2] > A[i]) {
                return A[i - 1] + A[i - 2] + A[i];
            }
        }
        return 0;
    }
}
