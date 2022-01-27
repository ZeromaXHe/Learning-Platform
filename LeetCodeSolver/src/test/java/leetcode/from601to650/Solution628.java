package leetcode.from601to650;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/20 10:44
 * @Description: 628.三个数的最大乘积 | 难度：简单 | 标签：数组、数学
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution628 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 72.75% 的用户
     * 内存消耗： 40 MB , 在所有 Java 提交中击败了 29.86% 的用户
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        int[] biggestPositive = new int[3];
        int[] biggestNegative = new int[3];
//        int[] smallestPositive = new int[2];
        int[] smallestNegative = new int[2];
        int countZero = 0;
        int countPositive = 0;
        int countNegative = 0;
        for (int num : nums) {
            if (num == 0) {
                countZero++;
            } else if (num > 0) {
                countPositive++;
                // 记录最大的正数
                if (num > biggestPositive[0]) {
                    biggestPositive[2] = biggestPositive[1];
                    biggestPositive[1] = biggestPositive[0];
                    biggestPositive[0] = num;
                } else if (num > biggestPositive[1]) {
                    biggestPositive[2] = biggestPositive[1];
                    biggestPositive[1] = num;
                } else if (num > biggestPositive[2]) {
                    biggestPositive[2] = num;
                }
//                // 记录最小的正数
//                if (num < smallestPositive[0] || smallestPositive[0] == 0) {
//                    smallestPositive[1] = smallestPositive[0];
//                    smallestPositive[0] = num;
//                } else if (num < smallestPositive[1] || smallestPositive[1] == 0) {
//                    smallestPositive[1] = num;
//                }
            } else {
                countNegative++;
                // 记录最小的负数
                if (num < smallestNegative[0]) {
                    smallestNegative[1] = smallestNegative[0];
                    smallestNegative[0] = num;
                } else if (num < smallestNegative[1]) {
                    smallestNegative[1] = num;
                }
                // 记录最大的负数
                if (num > biggestNegative[0] || biggestNegative[0] == 0) {
                    biggestNegative[2] = biggestNegative[1];
                    biggestNegative[1] = biggestNegative[0];
                    biggestNegative[0] = num;
                } else if (num > biggestNegative[1] || biggestNegative[1] == 0) {
                    biggestNegative[2] = biggestNegative[1];
                    biggestNegative[1] = num;
                } else if (num > biggestNegative[2] || biggestNegative[2] == 0) {
                    biggestNegative[2] = num;
                }
            }
        }
        int positive1Negative2Product = biggestPositive[0] * smallestNegative[0] * smallestNegative[1];
        if (countPositive >= 3) {
            int allPositiveProduct = biggestPositive[0] * biggestPositive[1] * biggestPositive[2];
            if (countNegative < 2) {
                return allPositiveProduct;
            } else {
                return Math.max(allPositiveProduct, positive1Negative2Product);
            }
        }
        if (countPositive == 0) {
            if (countZero > 0) {
                return 0;
            }
            return biggestNegative[0] * biggestNegative[1] * biggestNegative[2];
        }
        if (countPositive == 1) {
            // 负数不足两个的情况也可以直接计算，因为smallestNegative默认值为0
            return positive1Negative2Product;
        }
        // countPositive == 2
        if (countNegative >= 2) {
            return positive1Negative2Product;
        } else if (countNegative == 1) {
            if (countZero > 0) {
                return 0;
            }
            return biggestPositive[0] * biggestPositive[1] * smallestNegative[0];
        } else {
            // countNegative == 0
            // 剩下一个数肯定是0了
            return 0;
        }
    }

    @Test
    public void maximumProductTest() {
        Assert.assertEquals(300, maximumProduct(new int[]{-100, -2, -3, 1}));
        Assert.assertEquals(39200, maximumProduct(new int[]{-100, -98, -1, 2, 3, 4}));
    }
}
