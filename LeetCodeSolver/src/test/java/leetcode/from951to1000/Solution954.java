package leetcode.from951to1000;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/4/1 14:53
 * @Description: 954. 二倍数对数组 | 难度：中等 | 标签：贪心、数组、哈希表、排序
 * 给定一个长度为偶数的整数数组 arr，
 * 只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；
 * 否则，返回 false。
 * <p>
 * 示例 1：
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 * <p>
 * 提示：
 * 0 <= arr.length <= 3 * 104
 * arr.length 是偶数
 * -105 <= arr[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution954 {
    @Test
    public void canReorderDoubledTest() {
        Assert.assertFalse(canReorderDoubled(new int[]{3, 1, 3, 6}));
        Assert.assertFalse(canReorderDoubled(new int[]{2, 1, 2, 6}));
        Assert.assertTrue(canReorderDoubled(new int[]{4, -2, 2, -4}));
    }

    /**
     * 执行用时： 30 ms , 在所有 Java 提交中击败了 84.02% 的用户
     * 内存消耗： 49.2 MB , 在所有 Java 提交中击败了 5.67% 的用户
     * 通过测试用例： 102 / 102
     *
     * @param arr
     * @return
     */
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.putIfAbsent(i, 0);
            map.put(i, map.get(i) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, Comparator.comparingInt(Math::abs));
        for (int i : list) {
            if (map.get(i) == 0) {
                continue;
            }
            if (map.get(i * 2) == null || map.get(i) > map.get(i * 2)) {
                return false;
            }
            map.put(i * 2, map.get(i * 2) - map.get(i));
        }
        return true;
    }
}
