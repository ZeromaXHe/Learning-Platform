package leetcode.from201to250;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/3 9:49
 * @Description: 204. 计数质数 | 难度：简单 | 标签：哈希表、数学
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *  
 * 提示：
 * 0 <= n <= 5 * 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution204 {
    /**
     * 执行用时：15 ms, 在所有 Java 提交中击败了 83.24% 的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了 24.20% 的用户
     * 在埃拉托色尼筛的基础上，只计算奇数相关的数字是否是质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int count = 1;
        // isPrime的索引对应的是索引*2+3是否为质数。
        // 对于奇数n，我们使用(n-2)/2可以计算出对应的索引
        boolean[] isPrime = new boolean[(n - 2) / 2];
        Arrays.fill(isPrime, true);
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                count++;
                // (2*i+3)乘以比自己小的奇数的积都会被前面的质数的操作标记为非质数，所以从(2 * i + 3) * (2 * i + 3)往后计算
                if ((long) (2 * i + 3) * (2 * i + 3) < n) {
                    // 之所以j += 2 * (2 * i + 3)，是为了保证j是奇数，我们的计算过程不考虑偶数（偶数除了2不可能是质数）。
                    for (int j = (2 * i + 3) * (2 * i + 3); j < n && j > 0; j += 2 * (2 * i + 3)) {
                        isPrime[(j - 2) / 2] = false;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了 78.05% 的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了 33.13% 的用户
     *
     * @param n
     * @return
     */
    public int countPrimes_allCount(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i]) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 会超时
     *
     * @param n
     * @return
     */
    public int countPrimes_overtime(int n) {
        if (n <= 2) {
            return 0;
        }
        List<Integer> primeList = new LinkedList<>();
        primeList.add(2);
        loop:
        for (int i = 3; i < n; i += 2) {
            for (int prime : primeList) {
                if (i % prime == 0) {
                    continue loop;
                }
                if (prime > (i + 1) / 2) {
                    primeList.add(i);
                    continue loop;
                }
            }
            primeList.add(i);
        }
        return primeList.size();
    }

    public static void main(String[] args) {
        Solution204 solution204 = new Solution204();
        System.out.println(solution204.countPrimes(1000000));
    }
}
