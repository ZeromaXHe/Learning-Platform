package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 17:49
 * @Description: 面试题 08.10. 颜色填充 | 难度：简单 | 标签：深度优先搜索、广度优先搜索、数组、矩阵
 * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
 * <p>
 * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
 * <p>
 * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
 * <p>
 * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[[2,2,2],[2,2,0],[2,0,1]]
 * 解释:
 * 初始坐标点位于图像的正中间，坐标 (sr,sc)=(1,1) 。
 * 初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成 2 。
 * 注意，右下角的像素没有更改为 2 ，因为它不属于初始坐标点的周围区域。
 * <p>
 * 提示：
 * image 和 image[0] 的长度均在范围 [1, 50] 内。
 * 初始坐标点 (sr,sc) 满足 0 <= sr < image.length 和 0 <= sc < image[0].length 。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535] 内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/color-fill-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_10 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.3 MB , 在所有 Java 提交中击败了 31.41% 的用户
     * 通过测试用例： 277 / 277
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        fill(image, image[sr][sc], sr, sc, newColor);
        return image;
    }

    private void fill(int[][] image, int color, int x, int y, int newColor) {
        if (image[x][y] == newColor || image[x][y] != color) {
            return;
        }
        image[x][y] = newColor;
        if (x > 0) {
            fill(image, color, x - 1, y, newColor);
        }
        if (y > 0) {
            fill(image, color, x, y - 1, newColor);
        }
        if (x < image.length - 1) {
            fill(image, color, x + 1, y, newColor);
        }
        if (y < image[0].length - 1) {
            fill(image, color, x, y + 1, newColor);
        }
    }
}
