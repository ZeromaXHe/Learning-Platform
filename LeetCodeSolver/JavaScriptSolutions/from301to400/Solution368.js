/**
 * 368.最大整除子集 | 难度：中等 | 标签：数学、动态规划
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 128 ms , 在所有 JavaScript 提交中击败了 43.75% 的用户
 * 内存消耗： 40.2 MB , 在所有 JavaScript 提交中击败了 75.00% 的用户
 *
 * @param {number[]} nums
 * @return {number[]}
 */
var largestDivisibleSubset = function (nums) {
    // js 排序如果不传递排序函数的话，默认按字符串顺序排
    nums.sort(sortNumber);
    // js 二维数组
    var n = nums.length;
    let dp = new Array(n);
    for (let i = 0; i < n; i++) {
        dp[i] = [0, 0];
    }
    dp[0][0] = 1;
    let maxMaxCount = 1;
    let maxMaxIndex = 0;
    for (let i = 1; i < dp.length; i++) {
        let maxCount = 1;
        let maxIndex = i;
        for (let j = 0; j < i; j++) {
            if (nums[i] % nums[j] === 0) {
                if (dp[j][0] + 1 > maxCount) {
                    maxCount = dp[j][0] + 1;
                    maxIndex = j;
                }
            }
        }
        dp[i][0] = maxCount;
        dp[i][1] = maxIndex;
        if (maxCount > maxMaxCount) {
            maxMaxCount = maxCount;
            maxMaxIndex = i;
        }
    }
    let result = new Array(maxMaxCount);
    let index = maxMaxIndex;
    for (let i = maxMaxCount - 1; i >= 0; i--) {
        result[i] = nums[index];
        index = dp[index][1];
    }
    return result;
};

function sortNumber(a, b) {
    return a - b
}
