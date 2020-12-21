package leetcode.jianzhi_offer;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/21 13:36
 * @Description: 剑指 Offer 04. 二维数组中的查找 | 难度：中等 | 标签：数组、双指针
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * <p>
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 * <p>
 * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class SolutionOffer04 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 78.10% 的用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int i = 0;
        int j = matrix[0].length - 1;
        while (target != matrix[i][j]) {
            if (target > matrix[i][j]) {
                if (i >= matrix.length - 1) {
                    return false;
                } else {
                    i++;
                }
            } else {
                if (j <= 0) {
                    return false;
                } else {
                    j--;
                }
            }
        }
        return true;
    }
}
