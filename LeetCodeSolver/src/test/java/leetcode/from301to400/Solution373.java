package leetcode.from301to400;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: zhuxi
 * @Time: 2022/1/14 9:33
 * @Description: 373. 查找和最小的K对数字 | 难度：中等 | 标签：数组、堆（优先队列）
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * <p>
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * 提示:
 * 1 <= nums1.length, nums2.length <= 104
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution373 {
    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 89.85% 的用户
     * 内存消耗： 49.9 MB , 在所有 Java 提交中击败了 53.33% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 参考题解做的，加注释
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(k,
                (i1, i2) -> nums1[i1[0]] + nums2[i1[1]] - nums1[i2[0]] - nums2[i2[1]]);
        List<List<Integer>> ans = new ArrayList<>(k);
        int m = nums1.length;
        int n = nums2.length;
        // 将 nums1 的每个元素和 nums2 中的首个元素配对，放入候选堆中
        for (int i = 0; i < Math.min(m, k); i++) {
            heap.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !heap.isEmpty()) {
            // 取出当前候选堆中和最小的对
            int[] idxPair = heap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                // 将 nums2 的下一个元素作为配对，放入候选堆
                heap.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
