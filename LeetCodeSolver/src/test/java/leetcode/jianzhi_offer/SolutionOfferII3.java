package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数 | 难度：简单 | 标签：位运算、动态规划
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 * <p>
 * 示例 1:
 * 输入: n = 2
 * 输出: [0,1,1]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * <p>
 * 示例 2:
 * 输入: n = 5
 * 输出: [0,1,1,2,1,2]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * 说明 :
 * 0 <= n <= 105
 * <p>
 * 进阶:
 * 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
 * 要求算法的空间复杂度为 O(n) 。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。
 * <p>
 * 注意：本题与主站 338 题相同：https://leetcode-cn.com/problems/counting-bits/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/w3tCBm
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/13 17:51
 */
public class SolutionOfferII3 {
    /**
     * 参考了题解思路
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.28% 的用户
     * 内存消耗：45.5 MB, 在所有 Java 提交中击败了 45.83% 的用户
     * 通过测试用例：15 / 15
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i / 2] + (i & 1);
        }
        return result;
    }
}
