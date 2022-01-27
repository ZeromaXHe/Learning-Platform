package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/4 9:49
 * @Description: 509.斐波那契数 | 难度：简单 | 标签：数组
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *  
 * 提示：
 * 0 <= n <= 30
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution509 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 34.9 MB , 在所有 Java 提交中击败了 96.13% 的用户
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int fib1 = 0;
        int fib2 = 1;
        int temp = 1;
        for (int i = 0; i < n - 1; i++) {
            temp = fib1 + fib2;
            fib1 = fib2;
            fib2 = temp;
        }
        return fib2;
    }

    @Test
    public void fibTest(){
        Assert.assertEquals(fib(2),1);
        Assert.assertEquals(fib(3),2);
        Assert.assertEquals(fib(4),3);
    }
}
