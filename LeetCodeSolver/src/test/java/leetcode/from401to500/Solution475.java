package leetcode.from401to500;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/12/20 10:52
 * @Description: 475. 供暖器 | 难度：中等 | 标签：数组、双指针、二分查找、排序
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * <p>
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * <p>
 * 示例 1:
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * <p>
 * 示例 2:
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * <p>
 * 示例 3：
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 * <p>
 * 提示：
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/heaters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution475 {
    @Test
    public void findRadiusTest() {
        Assert.assertEquals(1, findRadius(new int[]{1, 2, 3}, new int[]{2}));
        Assert.assertEquals(1, findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
        Assert.assertEquals(3, findRadius(new int[]{1, 5}, new int[]{2}));
    }

    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 91.77% 的用户
     * 内存消耗： 41.6 MB , 在所有 Java 提交中击败了 68.65% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        if (heaters.length == 1) {
            return Math.max(heaters[0] - houses[0], houses[houses.length - 1] - heaters[0]);
        }
        Arrays.sort(heaters);
        int result = 0;
        int iHeater = 0;
        for (int iHouse = 0; iHouse < houses.length; iHouse++) {
            if (iHeater == 0 && houses[iHouse] <= heaters[iHeater]) {
                result = Math.max(result, heaters[iHeater] - houses[iHouse]);
                while (iHouse < houses.length - 1 && houses[iHouse + 1] <= heaters[iHeater]) {
                    iHouse++;
                }
            } else {
                while (iHeater < heaters.length - 1 && houses[iHouse] > heaters[iHeater + 1]) {
                    iHeater++;
                }
                if (iHeater < heaters.length - 1) {
                    result = Math.max(
                            Math.min(houses[iHouse] - heaters[iHeater], heaters[iHeater + 1] - houses[iHouse]),
                            result);
                } else {
                    result = Math.max(houses[iHouse] - heaters[iHeater], result);
                }
            }
        }
        return result;
    }
}
