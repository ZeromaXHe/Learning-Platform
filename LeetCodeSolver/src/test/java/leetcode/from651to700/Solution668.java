package leetcode.from651to700;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/18 15:46
 * @Description: 668. 乘法表中第k小的数 | 难度：困难 | 标签：二分查找
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * <p>
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * <p>
 * 例 1：
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * <p>
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * <p>
 * 例 2：
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * <p>
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * <p>
 * 注意：
 * m 和 n 的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution668 {
    @Test
    public void findKthNumberTest() {
        Assert.assertEquals(3, findKthNumber(3, 3, 5));
        Assert.assertEquals(2, findKthNumber(1, 3, 2));
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 99.14% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 74.57% 的用户
     * 通过测试用例： 70 / 70
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int m, int n, int k) {
        int l = 1;
        int r = m * n;

        while (l < r) {
            int mid = (l + r) / 2;
            /**
             * 执行用时： 11 ms , 在所有 Java 提交中击败了 40.95% 的用户
             * 内存消耗： 38 MB , 在所有 Java 提交中击败了 84.05% 的用户
             * 通过测试用例： 70 / 70
             *
             * 使用注释掉的代码的话，会更慢
             */
//            int count = 0;
//            for (int i = 1; i <= m; i++) {
//                count += Math.min(mid / i, n);
//            }
            int count = mid / n * n;
            for (int i = mid / n + 1; i <= m; ++i) {
                count += mid / i;
            }
            if (count >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 执行用时： 15 ms , 在所有 Java 提交中击败了 14.22% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 10.35% 的用户
     * 通过测试用例： 70 / 70
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber_twoCount(int m, int n, int k) {
        int l = 1;
        int r = m * n;
        int min = Math.min(m, n);
        int max = Math.max(m, n);

        while (l < r) {
            int mid = (l + r) / 2;
            int minCount = 0;
            int equalCount = 0;
            for (int i = 1; i <= min; i++) {
                minCount += Math.min(mid / i, max);
                if (mid / i <= max && mid % i == 0) {
                    equalCount++;
                    minCount--;
                }
            }
            if (minCount < k && minCount + equalCount >= k) {
                return mid;
            } else if (minCount >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
