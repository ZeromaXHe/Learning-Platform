package leetcode.from5501to5550;

import java.util.Arrays;

/**
 * @author ZeromaXHe
 * @date 2020/11/14 22:32
 * @Description 5550.拆炸弹 | 难度：简单 | 标签：数组
 * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
 * <p>
 * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
 * <p>
 * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
 * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
 * 如果 k == 0 ，将第 i 个数字用 0 替换。
 * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
 * <p>
 * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 * <p>
 * 示例 1：
 * 输入：code = [5,7,1,4], k = 3
 * 输出：[12,10,16,13]
 * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
 * <p>
 * 示例 2：
 * 输入：code = [1,2,3,4], k = 0
 * 输出：[0,0,0,0]
 * 解释：当 k 为 0 时，所有数字都被 0 替换。
 * <p>
 * 示例 3：
 * 输入：code = [2,4,9,3], k = -2
 * 输出：[12,5,6,13]
 * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == code.length
 * 1 <= n <= 100
 * 1 <= code[i] <= 100
 * -(n - 1) <= k <= n - 1
 */
public class Solution5550 {
    /**
     * 通过
     * 执行用时: 2 ms
     * 内存消耗: 38.6 MB
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        if (k == 0) {
            Arrays.fill(code, 0);
            return code;
        }
        int[] decoder = new int[code.length];
        if (k > 0) {
//            想多了，这里k在-(n-1)到n-1之间
//            if (k >= code.length) {
//                int sum = 0;
//                for (int i = 0; i < code.length; i++) {
//                    sum += code[i];
//                    if (k % code.length != 0) {
//                        for (int j = i - 1; j >= i - 1 - k % code.length; j--) {
//                            int index = j < 0 ? code.length + j : j;
//                            decoder[index] += code[i];
//                        }
//                    }
//                }
//                int addSum = k / code.length * sum;
//                for (int i = 0; i < code.length; i++) {
//                    decoder[i] += addSum;
//                }
//            } else {
            for (int i = 0; i < code.length; i++) {
                for (int j = i - 1; j > i - 1 - k; j--) {
                    int index = j < 0 ? code.length + j : j;
                    decoder[index] += code[i];
                }
            }
        } else {
            // k < 0
            for (int i = 0; i < code.length; i++) {
                for (int j = i + 1; j < i + 1 - k; j++) {
                    int index = j >= code.length ? j - code.length : j;
                    decoder[index] += code[i];
                }
            }
        }
        return decoder;
    }

    public static void main(String[] args) {
        Solution5550 solution5550 = new Solution5550();
        // [12,10,16,13]
        System.out.println(Arrays.toString(solution5550.decrypt(
                new int[]{5,7,1,4}, 3)));
        // [0,0,0,0]
        System.out.println(Arrays.toString(solution5550.decrypt(
                new int[]{1,2,3,4}, 0)));
        // [12,5,6,13]
        System.out.println(Arrays.toString(solution5550.decrypt(
                new int[]{2,4,9,3}, -2)));
    }
}
