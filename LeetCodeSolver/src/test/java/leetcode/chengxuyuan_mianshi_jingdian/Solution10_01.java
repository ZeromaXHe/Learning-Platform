package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/13 16:55
 * @Description: 面试题 10.01. 合并排序的数组 | 难度：简单 | 标签：数组、双指针、排序
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * <p>
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * <p>
 * 示例:
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 说明:
 * A.length == n + m
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sorted-merge-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_01 {
    @Test
    public void mergeTest() {
        int[] A = {1, 2, 3, 0, 0, 0};
        int[] B = {2, 5, 6};
        merge(A, 3, B, 3);
        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, A);
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 43.28% 的用户
     * 通过测试用例： 59 / 59
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int indexA = m - 1;
        int index = m + n - 1;
        while (indexA >= 0) {
            A[index--] = A[indexA--];
        }
        indexA = n;
        int indexB = 0;
        index = 0;
        while (indexB < n && indexA < m + n) {
            if (A[indexA] < B[indexB]) {
                A[index++] = A[indexA++];
            } else {
                A[index++] = B[indexB++];
            }
        }
        while (indexB < n) {
            A[index++] = B[indexB++];
        }
        while (indexA < m + n) {
            A[index++] = A[indexA++];
        }
    }
}
