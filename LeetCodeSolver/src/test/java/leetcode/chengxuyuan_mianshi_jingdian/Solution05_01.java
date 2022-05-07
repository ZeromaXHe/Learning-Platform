package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/7 18:37
 * @Description: 面试题 05.01. 插入 | 难度：简单 | 标签：位运算
 * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 * <p>
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
 * <p>
 * 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
 * <p>
 * 示例1:
 * 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 * 输出：N = 1100(10001001100)
 * <p>
 * 示例2:
 * 输入： N = 0, M = 31(11111), i = 0, j = 4
 * 输出：N = 31(11111)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-bits-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution05_01 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 28.04% 的用户
     * 通过测试用例： 29 / 29
     *
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     */
    public int insertBits(int N, int M, int i, int j) {
        int multiN = 1;
        for (int n = 0; n < i; n++) {
            multiN <<= 1;
        }
        int multiM = 1;
        for (int n = i; n <= j; n++) {
            if ((N & multiN) > 0) {
                N -= multiN;
            }
            if ((M & multiM) > 0) {
                N += multiN;
            }
            multiN <<= 1;
            multiM <<= 1;
        }
        return N;
    }

    /**
     * 题解里面看到的，这个是真的妙
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 79.75% 的用户
     * 通过测试用例： 29 / 29
     *
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     */
    public int insertBits_bitCalc(int N, int M, int i, int j) {
        //left in (,j+1]; middle in [i,j]; right in [i-1,0];
        int left = N >> j >> 1; //把最左边的部分调整好了，即抛弃了替换部分和低位部分
        left = left << j << 1;  //因此最后要进行或运算，所以把他再移到原来的高位上。
        int middle = M << i;  //替换N的j<-----i位，那么只需要将M左移i位即可
        int right = N & ((1 << i) - 1);//只需要N的低位，将高位置零,(1<<2)-1 = (11)2
        return left | middle | right;
        // 可以简写成下面一行：
//        return (N >> j >> 1 << j << 1) | (M << i) | (N & ((1 << i) - 1));
    }
}
