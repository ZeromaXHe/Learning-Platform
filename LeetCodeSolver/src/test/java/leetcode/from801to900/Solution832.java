package leetcode.from801to900;

/**
 * @Author: zhuxi
 * @Time: 2021/2/24 9:54
 * @Description: 832.翻转图像 | 难度：简单 | 标签：数组
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1：
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * <p>
 * 示例 2：
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * <p>
 * 提示：
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flipping-an-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution832 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 46.56% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 11.20% 的用户
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int temp;
        int rowLen = A.length;
        int colLen = A[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < (colLen + 1) / 2; j++) {
                temp = A[i][j] == 0 ? 1 : 0;
                A[i][j] = A[i][colLen - 1 - j] == 0 ? 1 : 0;
                A[i][colLen - 1 - j] = temp;
            }
        }
        return A;
    }
}
