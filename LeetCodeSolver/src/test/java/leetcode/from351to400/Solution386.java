package leetcode.from351to400;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/4/18 9:45
 * @Description: 386. 字典序排数 | 难度：中等 | 标签：深度优先搜索、字典树
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * 1 <= n <= 5 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution386 {
    @Test
    public void lexicalOrderTest() {
        List<Integer> list = lexicalOrder(13);
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        Assert.assertArrayEquals(new int[]{1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9}, result);
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 72.17% 的用户
     * 内存消耗： 44.3 MB , 在所有 Java 提交中击败了 89.47% 的用户
     * 通过测试用例： 26 / 26
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        int i = 1;
        while (i > 0) {
            if (i > n) {
                i /= 10;
                while (i % 10 == 9) {
                    i /= 10;
                }
                if (i == 0) {
                    break;
                }
                i++;
                continue;
            }
            result.add(i);
            i *= 10;
        }
        return result;
    }
}
