package leetcode.from851to900;

import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/7 18:47
 * @Description: 861. 翻转矩阵后的得分 | 难度：中等 | 标签：贪心算法
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * <p>
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * <p>
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * <p>
 * 返回尽可能高的分数。
 * <p>
 * 示例：
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *  
 * 提示：
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-after-flipping-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution861 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 56.78% 的用户
     * 保证第一列为1，然后保证后面每一列的1的数量大于0
     *
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < A[0].length; i++) {
            result <<= 1;
            int countOne = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j][0] == A[j][i]) {
                    countOne++;
                }
            }
            if (countOne > A.length / 2) {
                result += countOne;
            } else {
                result += A.length - countOne;
            }
            // System.out.println("i:" + i + " result:" + result + " countOne:" + countOne);
        }
        return result;
    }

    @Test
    public void matrixScoreTest() {
        System.out.println(matrixScore(new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}}));
    }
}
