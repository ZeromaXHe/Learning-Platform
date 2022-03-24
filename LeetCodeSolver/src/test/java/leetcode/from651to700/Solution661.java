package leetcode.from651to700;

/**
 * @Author: zhuxi
 * @Time: 2022/3/24 9:50
 * @Description: 661. 图片平滑器 | 难度：简单 | 标签：数组、矩阵
 * 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * <p>
 * 每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
 * <p>
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
 * <p>
 * 给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。
 * <p>
 * 示例 1:
 * 输入:img = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * <p>
 * 示例 2:
 * 输入: img = [[100,200,100],[200,50,200],[100,200,100]]
 * 输出: [[137,141,137],[141,138,141],[137,141,137]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 * 对于点 (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 * 对于点 (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 * <p>
 * 提示:
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/image-smoother
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution661 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 90.44% 的用户
     * 内存消耗： 42.3 MB , 在所有 Java 提交中击败了 22.12% 的用户
     * 通过测试用例： 203 / 203
     *
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int[][] result = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                int sum = img[i][j];
                int count = 1;
                if (j > 0) {
                    sum += img[i][j - 1];
                    count++;
                }
                if (j < img[0].length - 1) {
                    sum += img[i][j + 1];
                    count++;
                }
                if (i > 0) {
                    sum += img[i - 1][j];
                    count++;
                    if (j > 0) {
                        sum += img[i - 1][j - 1];
                        count++;
                    }
                    if (j < img[0].length - 1) {
                        sum += img[i - 1][j + 1];
                        count++;
                    }
                }
                if (i < img.length - 1) {
                    sum += img[i + 1][j];
                    count++;
                    if (j > 0) {
                        sum += img[i + 1][j - 1];
                        count++;
                    }
                    if (j < img[0].length - 1) {
                        sum += img[i + 1][j + 1];
                        count++;
                    }
                }
                result[i][j] = sum / count;
            }
        }
        return result;
    }
}
