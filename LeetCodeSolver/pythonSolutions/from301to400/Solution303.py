from typing import List


class NumArray:
    """
    303. 区域和检索 - 数组不可变 | 难度：简单 | 标签：动态规划
    给定一个整数数组 nums，求出数组从索引i到j（i≤j）范围内元素的总和，包含i、j两点。

    实现 NumArray 类：
    NumArray(int[] nums) 使用数组 nums 初始化对象
    int sumRange(int i, int j) 返回数组 nums 从索引i到j（i≤j）范围内元素的总和，包含i、j两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）

    示例：
    输入：
    ["NumArray", "sumRange", "sumRange", "sumRange"]
    [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
    输出：
    [null, 1, -1, -3]

    解释：
    NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
    numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
    numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
    numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))

    提示：

    0 <= nums.length <= 104
    -10^5 <= nums[i] <= 10^5
    0 <= i <= j < nums.length
    最多调用 104 次 sumRange 方法

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/range-sum-query-immutable
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    """
    执行用时： 64 ms , 在所有 Python3 提交中击败了 91.28% 的用户
    内存消耗： 18.2 MB , 在所有 Python3 提交中击败了 18.85% 的用户

    Your NumArray object will be instantiated and called as such:
    obj = NumArray(nums)
    param_1 = obj.sumRange(i,j)
    """

    def __init__(self, nums: List[int]):
        N = len(nums)
        # Python [0] * n 描述的意思:
        # list * int 意思是将数组重复 int 次并依次连接形成一个新数组
        self.preSum = [0] * (N + 1)
        for i in range(N):
            self.preSum[i + 1] = self.preSum[i] + nums[i]

    def sumRange(self, i: int, j: int) -> int:
        return self.preSum[j + 1] - self.preSum[i]


if __name__ == '__main__':
    numArray = NumArray([-2, 0, 3, -5, 2, -1])
    print("" + str(numArray.sumRange(0, 2)) + " should be 1 ")
    print("" + str(numArray.sumRange(2, 5)) + " should be -1 ")
    print("" + str(numArray.sumRange(0, 5)) + " should be -3 ")
