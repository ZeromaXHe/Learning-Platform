package leetcode.from401to450;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 14:47
 * @Description: 440. 字典序的第K小数字 | 难度：困难 | 标签：字典树
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * <p>
 * 示例 1:
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * <p>
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: 1
 * <p>
 * 提示:
 * 1 <= k <= n <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution440 {
    @Test
    public void findKthNumberTest() {
        Assert.assertEquals(9, findKthNumber(100, 90));
        Assert.assertEquals(278, findKthNumber(1000, 200));
        Assert.assertEquals(416126219, findKthNumber(681692778, 351251360));
        Assert.assertEquals(288990744, findKthNumber(719885387, 209989719));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 67.90% 的用户
     * 通过测试用例： 69 / 69
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        // 作为一个指针，指向当前所在位置，当 p == k 时，也就是到了排位第 k 的数
        int p = 1;
        // 前缀
        int prefix = 1;
        while (p < k) {
            // 获得当前前缀下所有子节点的和
            int count = getNumber(prefix, n);
            if (p + count > k) {
                // 第 k 个数在当前前缀下
                prefix *= 10;
                // 把指针指向了第一个子节点的位置，比如 11 乘 10 后变成 110，指针从 11 指向了 110
                p++;
            } else if (p + count <= k) {
                // 第 k 个数不在当前前缀下
                prefix++;
                // 注意这里的操作，把指针指向了下一前缀的起点
                p += count;
            }
        }
        return prefix;
    }

    /**
     * @param prefix 前缀
     * @param n      上界
     * @return
     */
    private int getNumber(int prefix, int n) {
        long cur = prefix;
        // 下一个前缀
        long next = prefix + 1;
        int count = 0;
        // 当前的前缀当然不能大于上界
        while (cur <= n) {
            // 下一个前缀的起点减去当前前缀的起点
            count += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
            // 如果说刚刚 prefix 是 1，next 是 2，那么现在分别变成 10 和 20
            // 1 为前缀的子节点增加 10 个，十叉树增加一层, 变成了两层

            // 如果说现在 prefix 是 10，next 是 20，那么现在分别变成 100 和 200，
            // 1 为前缀的子节点增加 100 个，十叉树又增加了一层，变成了三层
        }
        //把当前前缀下的子节点和返回去。
        return count;
    }

    public int findKthNumber_timeout(int n, int k) {
        long result = 1;
//        List<Long> list = new ArrayList<>(k);
//        list.add(result);
        for (int i = 1; i < k; i++) {
            if (result * 10 <= n) {
                result *= 10;
            } else if (result + 1 <= n) {
                result += 1;
                while (result % 10 == 0) {
                    result /= 10;
                }
            } else {
                result = result / 10 + 1;
            }
//            list.add(result);
        }
//        System.out.println(list);
        return (int) result;
    }
}
