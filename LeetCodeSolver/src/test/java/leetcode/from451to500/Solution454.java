package leetcode.from451to500;

import java.util.HashMap;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/27 19:36
 * @Description: 454.四数之和 | 难度：中等 | 标签：哈希表、二分查找
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution454 {
    /**
     * 执行用时：72 ms, 在所有 Java 提交中击败了 80.87% 的用户
     * 内存消耗：58.8 MB, 在所有 Java 提交中击败了 36.11% 的用户
     * 看到标签里的二分查找还愣了一下，后来才想明白是指最一开始把4个数组二分了……
     * 
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                if (map.containsKey(i + j)) {
                    map.put(i + j, map.get(i + j) + 1);
                } else {
                    map.put(i + j, 1);
                }
            }
        }
        int result = 0;
        for (int i : C) {
            for (int j : D) {
                if (map.containsKey(-i - j)) {
                    result += map.get(-i - j);
                }
            }
        }
        return result;
    }
}
