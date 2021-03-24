/**
 * 456.132模式 | 难度：中等 | 标签：栈
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 84 ms , 在所有 JavaScript 提交中击败了 92.31% 的用户
 * 内存消耗： 39.8 MB , 在所有 JavaScript 提交中击败了 63.08% 的用户
 *
 * @param {number[]} nums
 * @return {boolean}
 */
var find132pattern = function (nums) {
    const n = nums.length;
    var candidateK = [nums[n - 1]];
    // JS取最小值的方法： let max_k = -Number.MAX_SAFE_INTEGER;
    let maxK = -1000000001;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] < maxK) {
            return true;
        }
        // candidateK.length > 0 可以简化为 candidateK.length
        // JS 没有 peek 操作，写为 candidateK[candidateK.length-1] （栈push在最后）
        while (candidateK.length && nums[i] > candidateK[candidateK.length - 1]) {
            maxK = candidateK.pop();
        }
        if (nums[i] > maxK) {
            candidateK.push(nums[i]);
        }
    }
    return false;
};