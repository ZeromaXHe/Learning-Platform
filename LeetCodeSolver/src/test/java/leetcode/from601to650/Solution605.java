package leetcode.from601to650;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/1 19:46
 * @Description: 605.种花问题 | 难度：简单 | 标签：贪心算法、数组
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * <p>
 * 注意:
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-place-flowers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution605 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 78.37% 的用户
     * 逻辑写的优点太啰嗦了，题解其实很简洁的
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n > (flowerbed.length + 1) / 2) {
            return false;
        }
        int toBePlanted = n;
        int index = 0;
        int empty = 0;
        while (index < flowerbed.length && flowerbed[index] == 0) {
            empty++;
            index++;
        }
        if (empty != 0) {
            if (index < flowerbed.length) {
                toBePlanted -= empty / 2;
                if (toBePlanted <= 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        empty = 0;
        while (index < flowerbed.length) {
            if (flowerbed[index] == 1) {
                toBePlanted -= empty > 1 ? (empty - 1) / 2 : 0;
                if (toBePlanted <= 0) {
                    return true;
                }
                if (toBePlanted > (flowerbed.length - index - 1) / 2) {
                    return false;
                }
                empty = 0;
            } else {
                empty++;
            }
            index++;
        }
        if (empty != 0) {
            toBePlanted -= empty / 2;
            if (toBePlanted <= 0) {
                return true;
            }
        }
        return toBePlanted <= 0;
    }

    @Test
    public void canPlaceFlowersTest() {
        Assert.assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        Assert.assertFalse(canPlaceFlowers(new int[]{1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, 2));
        Assert.assertTrue(canPlaceFlowers(new int[]{0, 0, 1, 0, 1}, 1));
        Assert.assertTrue(canPlaceFlowers(new int[]{1, 0, 1, 0, 1, 1, 0, 0, 0, 0}, 2));
        Assert.assertTrue(canPlaceFlowers(new int[]{0}, 1));
    }
}
