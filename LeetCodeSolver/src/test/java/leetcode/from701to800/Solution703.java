package leetcode.from701to800;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/2/11 10:07
 * @Description: 703.数据流中的第K大元素 | 难度：简单 | 标签：栈、设计
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * <p>
 * 提示：
 * 1 <= k <= 10^4
 * 0 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * -10^4 <= val <= 10^4
 * 最多调用 add 方法 10^4 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution703 {
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     * <p>
     * 执行用时： 34 ms , 在所有 Java 提交中击败了 15.87% 的用户
     * 内存消耗： 43.9 MB , 在所有 Java 提交中击败了 23.78% 的用户
     * 待优化，有点慢
     */
    class KthLargest {
        private int count;
        private int k;
        private ArrayList<Integer> nums;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.nums = new ArrayList<>(k + 1);
            for (int i : nums) {
                add(i);
            }
        }

        public int add(int val) {
            int range = Math.min(count + 1, nums.size());
            int index = Collections.binarySearch(nums, val, Comparator.reverseOrder());
            if (index < 0) {
                index = -index - 1;
            }
            nums.add(index, val);
            if (nums.size() > k) {
                nums.remove(nums.size() - 1);
            }
            return nums.get(nums.size() - 1);
        }
    }

    @Test
    public void KthLargestTest() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        Assert.assertEquals(4, kthLargest.add(3));
        Assert.assertEquals(5, kthLargest.add(5));
        Assert.assertEquals(5, kthLargest.add(10));
        Assert.assertEquals(8, kthLargest.add(9));
        Assert.assertEquals(8, kthLargest.add(4));
    }
}
