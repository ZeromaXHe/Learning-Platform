package leetcode.from301to350;

/**
 * @author zhuxi
 * @apiNote 307. 区域和检索 - 数组可修改 | 难度：中等 | 标签：设计、树状数组、线段树、数组
 * 给你一个数组 nums ，请你完成两类查询。
 * <p>
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * <p>
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104
 * @implNote
 * @since 2023/11/13 9:58
 */
public class Solution307 {
    /**
     * 树状数组，官方题解
     * <p>
     * 时间 74 ms
     * 击败 82.21% 使用 Java 的用户
     * 内存 70.16 MB
     * 击败 42.93% 使用 Java 的用户
     */
    class NumArray {
        private int[] tree;
        private int[] nums;

        public NumArray(int[] nums) {
            this.tree = new int[nums.length + 1];
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }

        private int lowBit(int x) {
            return x & -x;
        }

        private void add(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += lowBit(index);
            }
        }

        private int prefixSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= lowBit(index);
            }
            return sum;
        }
    }
}
