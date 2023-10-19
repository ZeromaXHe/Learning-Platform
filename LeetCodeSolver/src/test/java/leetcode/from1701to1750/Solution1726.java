package leetcode.from1701to1750;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 1726. 同积元组 | 难度：中等 | 标签：数组、哈希表
 * 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 104
 * nums 中的所有元素 互不相同
 * @implNote
 * @since 2023/10/19 10:01
 */
public class Solution1726 {
    @Test
    public void tupleSameProductTest() {
        Assert.assertEquals(8, tupleSameProduct(new int[]{2, 3, 4, 6}));
        Assert.assertEquals(16, tupleSameProduct(new int[]{1, 2, 4, 5, 10}));
    }

    /**
     * 时间 241 ms
     * 击败 28.00% 使用 Java 的用户
     * 内存 64.06 MB
     * 击败 37.33% 使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int prod = nums[i] * nums[j];
                if (map.containsKey(prod)) {
                    count += 8 * map.get(prod);
                    map.put(prod, map.get(prod) + 1);
                } else {
                    map.put(prod, 1);
                }
            }
        }
        return count;
    }
}
