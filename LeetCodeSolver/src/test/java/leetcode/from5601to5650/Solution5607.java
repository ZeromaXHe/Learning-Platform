package leetcode.from5601to5650;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/22 10:41
 * @Description: 5607. 生成平衡数组的方案数
 * 给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
 * <p>
 * 比方说，如果 nums = [6,1,7,4,1] ，那么：
 * <p>
 * 选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
 * 选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
 * 选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
 * 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
 * <p>
 * 请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,1,6,4]
 * 输出：1
 * 解释：
 * 删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
 * 删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
 * 删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
 * 删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
 * 只有一种让剩余数组成为平衡数组的方案。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以删除任意元素，剩余数组都是平衡数组。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：不管删除哪个元素，剩下数组都不是平衡数组。
 * @Modified By: ZeromaXHe
 */
public class Solution5607 {
    /**
     * 105 / 105 个通过测试用例
     * 状态：通过
     * 执行用时: 17 ms
     * 内存消耗: 52 MB
     *
     * @param nums
     * @return
     */
    public int waysToMakeFair(int[] nums) {
        int[] leftSingularSum = new int[nums.length];
        int[] leftEvenSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i > 1) {
                if (i % 2 == 1) {
                    leftSingularSum[i] = nums[i] + leftSingularSum[i - 2];
                    leftEvenSum[i] = leftEvenSum[i - 1];
                }else{
                    leftEvenSum[i] = nums[i] + leftEvenSum[i - 2];
                    leftSingularSum[i] = leftSingularSum[i - 1];
                }
            } else if (i == 0) {
                leftEvenSum[i] = nums[i];
                leftSingularSum[i] = 0;
            } else {
                // i == 1
                leftSingularSum[i] = nums[i];
                leftEvenSum[i] = leftEvenSum[0];
            }
        }
//        System.out.println("leftSingularSum: " + Arrays.toString(leftSingularSum));
//        System.out.println("leftEvenSum: " + Arrays.toString(leftEvenSum));
//        System.out.println("sum: " + sum);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((sum - nums[i]) % 2 == 1) {
                continue;
            }
            if (i > 0) {
//                System.out.println("halfSum:" + (leftSingularSum[i - 1] + leftEvenSum[nums.length - 1] - leftEvenSum[i]));
                if ((leftSingularSum[i - 1] + leftEvenSum[nums.length - 1] - leftEvenSum[i]) == (sum - nums[i]) / 2) {
                    result++;
                }
            } else {
//                System.out.println("halfSum:" + (leftEvenSum[nums.length - 1] - leftEvenSum[0]));
                if (leftEvenSum[nums.length - 1] - leftEvenSum[0] == (sum - nums[i]) / 2) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution5607 solution5607 = new Solution5607();
        System.out.println(solution5607.waysToMakeFair(new int[]{2, 1, 6, 4}));
    }
}
