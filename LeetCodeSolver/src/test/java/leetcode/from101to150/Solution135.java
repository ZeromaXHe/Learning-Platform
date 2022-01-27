package leetcode.from101to150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/24 9:48
 * @Description: 135.分发糖果 | 难度：困难 | 标签：贪心算法
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution135 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 66.08% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 82.88% 的用户
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 1) {
            return 1;
        } else if (len == 0) {
            return 0;
        }
        int inc = 1;
        int dec = 1;
        int sum = 0;
        for (int i = 1; i < len; i++) {
            int diff = ratings[i] - ratings[i - 1];
            if (diff == 0) {
                if (inc != 1 || dec != 1) {
                    sum += tempSum(inc, dec);
                } else {
                    sum += 1;
                }
                inc = 1;
                dec = 1;
            } else if (diff > 0) {
                if (inc != 1 || dec != 1) {
                    if (dec == 0) {
                        inc++;
                    } else {
                        sum += tempSum(inc, dec) - 1;
                        inc = 2;
                        dec = 0;
                    }
                } else {
                    inc = 2;
                    dec = 0;
                }
            } else {
                // diff < 0
                if (inc != 1 || dec != 1) {
                    if (dec == 0 && inc != 0) {
                        dec = 2;
                    } else {
                        dec++;
                    }
                } else {
                    dec = 2;
                    inc = 0;
                }
            }
        }
        sum += tempSum(inc, dec);
        return sum;
    }

    /**
     * 根据inc和dec计算局部和
     *
     * @param inc
     * @param dec
     * @return
     */
    private int tempSum(int inc, int dec) {
        if (inc == 0) {
            return countSum(dec);
        } else if (dec == 0) {
            return countSum(inc);
        } else if (inc > dec) {
            return countSum(inc) + countSum(dec - 1);
        } else {
            return countSum(dec) + countSum(inc - 1);
        }
    }

    /**
     * 计算连续n个递增或递减得分的孩子要多少糖果
     *
     * @param n
     * @return
     */
    private int countSum(int n) {
        return (1 + n) * n / 2;
    }

    /**
     * 很难调试，放弃思路
     *
     * @param ratings
     * @return
     */
    public int candy_wrongThinking(int[] ratings) {
        int len = ratings.length;
        if (len <= 1) {
            return 0;
        }
        int[] rightIncrCount = new int[len];
        int[] rightDecrCount = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i + 1] > ratings[i]) {
                rightIncrCount[i] = rightIncrCount[i + 1] + 1;
            }
            if (ratings[i + 1] < ratings[i]) {
                rightDecrCount[i] = rightDecrCount[i + 1] + 1;
            }
        }
        int index = 0;
        int sum = 0;
        int nowCount = 0;
        while (index < len) {
            if (rightDecrCount[index] > 0) {
                sum += countSum(rightDecrCount[index]) - nowCount;
                index += rightDecrCount[index];
                nowCount = 0;
            } else if (rightIncrCount[index] > 0) {
                sum += countSum(rightIncrCount[index]);
                nowCount = rightIncrCount[index];
                index += rightIncrCount[index];
            } else {
                index++;
                nowCount = 0;
            }
        }
        return sum + len;
    }

    @Test
    public void candyTest() {
        //                [1,0,2,2,3,4,3,7,0,0,1,2,3,2,1,1,4,4,2,2]
        // rightDecrCount [1,0,0,0,0,1,0,1,0,0,0,0,2,1,0,0,0,1,0,0]
        // rightIncrCount [0,1,0,2,1,0,1,0,0,3,2,1,0,0,0,1,0,0,0,0]
        // 算法糖果分布    []
        // 预计糖果分布-1  [1,0,1,0,1,2,0,1,0,0,1,2,3,1,0,0,1,1,0,0]
        // 预估糖果分布    [2,1,2,1,2,3,1,2,1,1,2,3,4,2,1,1,2,2,1,1]
        // 预期结果 35
//        int[] candy = new int[]{2, 1, 2, 1, 2, 3, 1, 2, 1, 1, 2, 3, 4, 2, 1, 1, 2, 2, 1, 1};
//        int sum = 0;
//        for (int i : candy) {
//            sum += i;
//        }
//        System.out.println(sum);

        // 5
        Assert.assertEquals(5, candy(new int[]{1, 0, 2}));
        // 4
        Assert.assertEquals(4, candy(new int[]{1, 2, 2}));
        // 4
        Assert.assertEquals(4, candy(new int[]{1, 2, 1}));
        // 35
        Assert.assertEquals(35, candy(new int[]{1, 0, 2, 2, 3, 4, 3, 7, 0, 0, 1, 2, 3, 2, 1, 1, 4, 4, 2, 2}));
    }
}
