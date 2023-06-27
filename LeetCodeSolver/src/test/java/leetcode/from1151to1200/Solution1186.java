package leetcode.from1151to1200;

/**
 * @author zhuxi
 * @apiNote 1186. 删除一次得到子数组最大和 | 难度：中等 | 标签：数组、动态规划
 * 给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * <p>
 * 注意，删除一个元素后，子数组 不能为空。
 * <p>
 * 示例 1：
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 * <p>
 * 示例 2：
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 * <p>
 * 示例 3：
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 * 我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 * <p>
 * 提示：
 * 1 <= arr.length <= 105
 * -104 <= arr[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/27 9:57
 */
public class Solution1186 {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 67.22% 的用户
     * 内存消耗：50.7 MB, 在所有 Java 提交中击败了 48.89% 的用户
     * 通过测试用例：36 / 36
     *
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;
        // 代表到 i 为止连续最大和
        int dp0 = arr[0];
        // 代表到 i 为止删除一个的最大和
        int dp1 = 0;
        int max = dp0;
        for (int i = 1; i < n; i++) {
            dp1 = Math.max(dp1 + arr[i], dp0);
            dp0 = Math.max(dp0, 0) + arr[i];
            max = Math.max(dp0, max);
            max = Math.max(dp1, max);
        }
        return max;
    }
}
