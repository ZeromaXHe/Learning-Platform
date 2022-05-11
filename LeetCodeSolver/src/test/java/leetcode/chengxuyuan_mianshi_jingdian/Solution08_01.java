package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/10 20:23
 * @Description: 面试题 08.01. 三步问题 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 * <p>
 * 示例1:
 * 输入：n = 3
 * 输出：4
 * 说明: 有四种走法
 * <p>
 * 示例2:
 * 输入：n = 5
 * 输出：13
 * <p>
 * 提示:
 * n范围在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/three-steps-problem-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_01 {
    @Test
    public void waysToStepTest() {
        Assert.assertEquals(4, waysToStep(3));
        Assert.assertEquals(13, waysToStep(5));
    }

    /**
     * 执行用时： 14 ms , 在所有 Java 提交中击败了 80.25% 的用户
     * 内存消耗： 38.2 MB , 在所有 Java 提交中击败了 78.93% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 还有个快速幂的解法，可以更快。
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        int[] arr = new int[3];
        arr[2] = 1;
        for (int i = 0; i < n; i++) {
            int temp = (arr[0] + arr[1]) % 1000000007;
            temp = (temp + arr[2]) % 1000000007;
            arr[0] = arr[1];
            arr[1] = arr[2];
            arr[2] = temp;
        }
        return arr[2];
    }

    /**
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 69.12% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 98.11% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param n
     * @return
     */
    public int waysToStep_long(int n) {
        int[] arr = new int[3];
        arr[2] = 1;
        for (int i = 0; i < n; i++) {
            int temp = (int) (((long) arr[0] + arr[1] + arr[2]) % 1000000007);
            arr[0] = arr[1];
            arr[1] = arr[2];
            arr[2] = temp;
        }
        return arr[2];
    }
}
