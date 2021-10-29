package leetcode.from301to400;

/**
 * @Author: zhuxi
 * @Time: 2021/10/29 14:02
 * @Description: 335. 路径交叉 | 难度：困难 | 标签：几何、数组、数学
 * 给你一个整数数组 distance 。
 * <p>
 * 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。
 * 也就是说，每次移动后你的方位会发生逆时针变化。
 * <p>
 * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：distance = [2,1,1,2]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：distance = [1,2,3,4]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：distance = [1,1,1,1]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= distance.length <= 105
 * 1 <= distance[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-crossing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution335 {
    /**
     * 参考题解，感觉这道题主要还是要找到这个规律比较难
     * <p>
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 44.83% 的用户
     * 内存消耗： 43.4 MB , 在所有 Java 提交中击败了 16.38% 的用户
     * 通过测试用例： 29 / 29
     *
     * @param distance
     * @return
     */
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n < 4) {
            return false;
        }
        for (int i = 3; i < n; i++) {
            if (distance[i] >= distance[i - 2]
                    && distance[i - 1] <= distance[i - 3]) {
                return true;
            } else if (i >= 4 && distance[i - 1] == distance[i - 3]
                    && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            } else if (i >= 5 && distance[i - 1] <= distance[i - 3]
                    && distance[i - 2] > distance[i - 4]
                    && distance[i] + distance[i - 4] >= distance[i - 2]
                    && distance[i - 1] + distance[i - 5] >= distance[i - 3]) {
                return true;
            }
        }
        return false;
    }
}
