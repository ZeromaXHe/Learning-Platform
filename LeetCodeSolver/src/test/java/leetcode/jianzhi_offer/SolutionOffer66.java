package leetcode.jianzhi_offer;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 66. 构建乘积数组 | 难度：中等 | 标签：数组、前缀和
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * <p>
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * <p>
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 15:32
 */
public class SolutionOffer66 {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 26.62% 的用户
     * 内存消耗：53.1 MB, 在所有 Java 提交中击败了 26.31% 的用户
     * 通过测试用例：44 / 44
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);
        int prodL = 1, prodR = 1;
        for (int i = 0; i < n; i++) {
            result[i] *= prodL;
            result[n - 1 - i] *= prodR;
            prodL *= a[i];
            prodR *= a[n - 1 - i];
        }
        return result;
    }
}
