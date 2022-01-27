package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/9/29 9:40
 * @Description: 517. 超级洗衣机 | 难度：困难 | 标签：贪心、数组
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * <p>
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * <p>
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：machines = [1,0,5]
 * 输出：3
 * 解释：
 * 第一步:    1     0 <-- 5    =>    1     1     4
 * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
 * 第三步:    2     1 <-- 3    =>    2     2     2
 * <p>
 * 示例 2：
 * 输入：machines = [0,3,0]
 * 输出：2
 * 解释：
 * 第一步:    0 <-- 3     0    =>    1     2     0
 * 第二步:    1     2 --> 0    =>    1     1     1
 * <p>
 * 示例 3：
 * 输入：machines = [0,2,0]
 * 输出：-1
 * 解释：
 * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
 * <p>
 * 提示：
 * n == machines.length
 * 1 <= n <= 104
 * 0 <= machines[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-washing-machines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution517 {
    @Test
    public void testFindMinMoves() {
        Assert.assertEquals(3, findMinMoves(new int[]{1, 0, 5}));
        Assert.assertEquals(2, findMinMoves(new int[]{0, 3, 0}));
        Assert.assertEquals(-1, findMinMoves(new int[]{0, 2, 0}));
        // move需要绝对值，因为绝对值才代表在这个节点经手的件数
        Assert.assertEquals(8, findMinMoves(new int[]{0, 0, 11, 5}));
        // machine - avg不需要取绝对值，小于平均的可以从两边拿，但多于平均才必须一件一件给出去
        Assert.assertEquals(4, findMinMoves(new int[]{9, 1, 8, 8, 9}));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 25.00% 的用户
     * 通过测试用例： 120 / 120
     *
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }
        int len = machines.length;
        if (sum % len != 0) {
            return -1;
        }
        int avg = sum / len;
        int result = 0;
        int move = 0;
        for (int machine : machines) {
            move += machine - avg;
            result = Math.max(Math.abs(move), Math.max(machine - avg, result));
        }
        return result;
    }
}
