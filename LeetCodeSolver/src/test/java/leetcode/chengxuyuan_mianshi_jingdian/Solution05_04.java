package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/9 18:18
 * @Description: 面试题 05.04. 下一个数 | 难度：中等 | 标签：位运算
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 * <p>
 * 示例1:
 * 输入：num = 2（或者0b10）
 * 输出：[4, 1] 或者（[0b100, 0b1]）
 * <p>
 * 示例2:
 * 输入：num = 1
 * 输出：[2, -1]
 * <p>
 * 提示:
 * num的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closed-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_04 {
    @Test
    public void findClosedNumbersTest() {
        Assert.assertArrayEquals(new int[]{4, 1}, findClosedNumbers(2));
        Assert.assertArrayEquals(new int[]{2, -1}, findClosedNumbers(1));
        Assert.assertArrayEquals(new int[]{-1, -1}, findClosedNumbers(Integer.MAX_VALUE));
        Assert.assertArrayEquals(new int[]{0b101, -1}, findClosedNumbers(0b11));
        Assert.assertArrayEquals(new int[]{0b1011, -1}, findClosedNumbers(0b111));
        Assert.assertArrayEquals(new int[]{-1, 0x68000000}, findClosedNumbers(0x70000000));
        System.out.println(Integer.toBinaryString(67));
        System.out.println("-----------------");
        System.out.println(Integer.toBinaryString(69));
        System.out.println("-----------------");
        System.out.println(Integer.toBinaryString(56));
        System.out.println(Integer.toBinaryString(35));
        Assert.assertArrayEquals(new int[]{69, 56}, findClosedNumbers(67));
        Assert.assertArrayEquals(new int[]{2048, 512}, findClosedNumbers(1024));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 42.68% 的用户
     * 通过测试用例： 43 / 43
     *
     * @param num
     * @return
     */
    public int[] findClosedNumbers(int num) {
        int[] result = new int[]{num, num};
        int multi0 = 1;
        int multi1 = 1;
        while (result[0] > 0) {
            if (multi0 == 1 << 30) {
                result[0] = -1;
                break;
            }
            if ((result[0] & 3) == 1) {
                result[0] = num + multi0;
                // 将所有右侧的 1 移到最低位
                int multi = 1;
                multi0 >>= 1;
                while (multi0 > multi) {
                    while (multi0 > 0 && (result[0] & multi0) == 0) {
                        multi0 >>= 1;
                    }
                    while (multi < multi0 && (result[0] & multi) > 0) {
                        multi <<= 1;
                    }
                    if (multi0 > multi) {
                        result[0] = result[0] - multi0 + multi;
                    }
                    multi0 >>= 1;
                    multi <<= 1;
                }
                break;
            }
            multi0 <<= 1;
            result[0] >>= 1;
        }
        if (result[0] == 0) {
            result[0] = -1;
        }
        while (result[1] > 0) {
            if (multi1 == 1 << 30) {
                result[1] = -1;
                break;
            }
            if ((result[1] & 3) == 2) {
                result[1] = num - multi1;
                // 将所有右侧的 1 移到最高位
                int multi = 1;
                multi1 >>= 1;
                while (multi1 > multi) {
                    while (multi1 > 0 && (result[1] & multi1) > 0) {
                        multi1 >>= 1;
                    }
                    while (multi < multi1 && (result[1] & multi) == 0) {
                        multi <<= 1;
                    }
                    if (multi1 > multi) {
                        result[1] = result[1] + multi1 - multi;
                    }
                    multi1 >>= 1;
                    multi <<= 1;
                }
                break;
            }
            multi1 <<= 1;
            result[1] >>= 1;
        }
        if (result[1] == 0) {
            result[1] = -1;
        }
        return result;
    }
}
