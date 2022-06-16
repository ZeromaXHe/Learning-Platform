package leetcode.from501to550;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhuxi
 * @apiNote 532. 数组中的 k-diff 数对 | 难度：中等 | 标签：数组、哈希表、双指针、二分查找、排序
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * <p>
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 * <p>
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * <p>
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * <p>
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 * <p>
 * 提示：
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/16 11:14
 */
public class Solution532 {
    @Test
    public void findPairsTest() {
        Assert.assertEquals(2, findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        Assert.assertEquals(4, findPairs(new int[]{1, 2, 3, 4, 5}, 1));
        Assert.assertEquals(1, findPairs(new int[]{1, 3, 1, 5, 4}, 0));
        Assert.assertEquals(1, findPairs(new int[]{1, 1, 1, 1, 1}, 0));
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了 84.28% 的用户
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了 9.33% 的用户
     * 通过测试用例： 60 / 60
     *
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        int result = 0;
        if (k == 0) {
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (entry.getValue() > 1) {
                    result++;
                }
            }
        } else {
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            for (int num : set) {
                if (set.contains(num + k)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 执行用时：12 ms, 在所有 Java 提交中击败了 31.08% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了 21.76% 的用户
     * 通过测试用例： 60 / 60
     *
     * @param nums
     * @param k
     * @return
     */
    public int findPairs_stream(int[] nums, int k) {
        if (k == 0) {
            // boxed 将 IntStream 转为 Stream<Integer>
            Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            return (int) map.entrySet().stream().filter(entry -> entry.getValue() > 1).count();
        } else {
            Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
            return (int) set.stream().filter(num -> set.contains(num + k)).count();
        }
    }
}
