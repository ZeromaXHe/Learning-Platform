package leetcode.from801to850;

/**
 * @author zhuxi
 * @apiNote 849. 到最近的人的最大距离 | 难度：中等 | 标签：数组
 * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
 * <p>
 * 至少有一个空座位，且至少有一人已经坐在座位上。
 * <p>
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * <p>
 * 返回他到离他最近的人的最大距离。
 * <p>
 * 示例 1：
 * 输入：seats = [1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。
 * <p>
 * 示例 2：
 * 输入：seats = [1,0,0,0]
 * 输出：3
 * 解释：
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
 * 这是可能的最大距离，所以答案是 3 。
 * <p>
 * 示例 3：
 * 输入：seats = [0,1]
 * 输出：1
 * <p>
 * 提示：
 * 2 <= seats.length <= 2 * 104
 * seats[i] 为 0 或 1
 * 至少有一个 空座位
 * 至少有一个 座位上有人
 * @implNote
 * @since 2023/8/22 9:56
 */
public class Solution849 {
    /**
     * 时间 2 ms
     * 击败 91.01% 使用 Java 的用户
     * 内存 42.61 MB
     * 击败 58.43% 使用 Java 的用户
     *
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int max = -1;
        int dist = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (max == -1) {
                    max = dist;
                } else {
                    max = Math.max(max, (dist + 1) / 2);
                }
                dist = 0;
            } else {
                dist++;
            }
        }
        max = Math.max(max, dist);
        return max;
    }
}
