package leetcode.from1801to1900;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/9/10 10:05
 * @Description: 1894. 找到需要补充粉笔的学生编号 | 难度：中等 | 标签：数组、二分查找、前缀和、模拟
 * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，
 * 以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
 * <p>
 * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。
 * 当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
 * <p>
 * 请你返回需要 补充 粉笔的学生 编号 。
 * <p>
 * 示例 1：
 * 输入：chalk = [5,1,5], k = 22
 * 输出：0
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
 * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
 * <p>
 * 示例 2：
 * 输入：chalk = [3,4,1,2], k = 25
 * 输出：1
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
 * 编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
 * <p>
 * 提示：
 * chalk.length == n
 * 1 <= n <= 105
 * 1 <= chalk[i] <= 105
 * 1 <= k <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1894 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 12.67% 的用户
     * 内存消耗： 51.2 MB , 在所有 Java 提交中击败了 89.95% 的用户
     * 通过测试用例： 72 / 72
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] sum = new long[n];
        sum[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }
        k = (int) (k % sum[n - 1]);
        int index = Arrays.binarySearch(sum, k);
        if (index < 0) {
            return -index - 1;
        } else {
            return (index + 1) % n;
        }
    }

    /**
     * 使用自己实现的binarySearch的话，可以快一点点：
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 28.80% 的用户
     * 内存消耗： 51 MB , 在所有 Java 提交中击败了 95.92% 的用户
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer2(int[] chalk, int k) {
        int n = chalk.length;
        /*
         * 这里if分支和下面if分支一起添加后，效率提高：
         * 执行用时： 2 ms , 在所有 Java 提交中击败了 84.92% 的用户
         * 内存消耗： 51.1 MB , 在所有 Java 提交中击败了 93.40% 的用户
         * 通过测试用例： 72 / 72
         *
         * 其实既然现在在k就会提前跳出，sum就不需要使用long[]了，可以直接用int[]。
         * （之所以用long是因为倒数几个用例里面有和特别大的）
         * 然后如果把sum直接改成在chalk上操作估计还能更加优化一下空间，题解就是这样做的
         */
        if (chalk[0] > k) {
            return 0;
        }
        long[] sum = new long[n];
        sum[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + chalk[i];
            // 这个if分支和上面if一起添加
            if (sum[i] > k) {
                return i;
            }
        }
        k = (int) (k % sum[n - 1]);
        return binarySearch(sum, k);
    }

    private int binarySearch(long[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
