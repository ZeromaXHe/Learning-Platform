package leetcode.from5701to5800;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/3 22:30
 * @Description: 5705.判断国际象棋棋盘中一个格子的颜色 | 难度：简单 | 标签：
 * 给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。
 * <p>
 * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
 * <p>
 * 给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。
 * <p>
 * 示例 1：
 * 输入：coordinates = "a1"
 * 输出：false
 * 解释：如上图棋盘所示，"a1" 坐标的格子是黑色的，所以返回 false 。
 * <p>
 * 示例 2：
 * 输入：coordinates = "h3"
 * 输出：true
 * 解释：如上图棋盘所示，"h3" 坐标的格子是白色的，所以返回 true 。
 * <p>
 * 示例 3：
 * 输入：coordinates = "c7"
 * 输出：false
 * <p>
 * 提示：
 * coordinates.length == 2
 * 'a' <= coordinates[0] <= 'h'
 * '1' <= coordinates[1] <= '8'
 * @Modified By: ZeromaXHe
 */
public class Solution5705 {
    /**
     * 203 / 203 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 36.8 MB
     *
     * @param coordinates
     * @return
     */
    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) - 'a' + coordinates.charAt(1) - '1') % 2 == 1;
    }
}
