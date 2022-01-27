package leetcode.from5601to5650;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/6 10:33
 * @Description: 5618. K 和数对的最大数目
 * 给你一个整数数组 nums 和一个整数 k 。
 *
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 *
 * 返回你可以对数组执行的最大操作数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：2
 * 解释：开始时 nums = [1,2,3,4]：
 * - 移出 1 和 4 ，之后 nums = [2,3]
 * - 移出 2 和 3 ，之后 nums = []
 * 不再有和为 5 的数对，因此最多执行 2 次操作。
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,3], k = 6
 * 输出：1
 * 解释：开始时 nums = [3,1,3,4,3]：
 * - 移出前两个 3 ，之后nums = [1,4,3]
 * 不再有和为 6 的数对，因此最多执行 1 次操作。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-number-of-k-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution5618 {
    /**
     * 51 / 51 个通过测试用例
     * 状态：通过
     * 执行用时: 26 ms
     * 内存消耗: 51.5 MB
     * @param nums
     * @param k
     * @return
     */
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (i < k) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (key < k / 2) {
                if (map.containsKey(k - key)) {
                    count += Math.min(entry.getValue(), map.get(k - key));
                }
            } else if (key == k / 2) {
                if(k%2==0){
                    count+= entry.getValue()/2;
                }else{
                    if (map.containsKey(k - key)) {
                        count += Math.min(entry.getValue(), map.get(k - key));
                    }
                }
            }
        }
        return count;
    }
}
