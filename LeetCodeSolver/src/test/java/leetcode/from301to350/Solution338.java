package leetcode.from301to350;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/3/3 9:45
 * @Description: 338. 比特位计数 | 难度：中等 | 标签：位运算、动态规划
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution338 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 60.00% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 67.30% 的用户
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        if (num == 0) {
            return result;
        }
        result[1] = 1;
        for (int i = 2; i < result.length; i++) {
            result[i] = result[i / 2] + (i % 2 == 0 ? 0 : 1);
        }
        return result;
    }

    @Test
    public void countBitsTest() {
        Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, countBits(5));
    }
}
