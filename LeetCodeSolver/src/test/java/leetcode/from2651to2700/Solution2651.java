package leetcode.from2651to2700;

/**
 * @author zhuxi
 * @apiNote 2651. 计算列车到站时间 | 难度：简单 | 标签：
 * 给你一个正整数 arrivalTime 表示列车正点到站的时间（单位：小时），另给你一个正整数 delayedTime 表示列车延误的小时数。
 * <p>
 * 返回列车实际到站的时间。
 * <p>
 * 注意，该问题中的时间采用 24 小时制。
 * <p>
 * 示例 1：
 * 输入：arrivalTime = 15, delayedTime = 5
 * 输出：20
 * 解释：列车正点到站时间是 15:00 ，延误 5 小时，所以列车实际到站的时间是 15 + 5 = 20（20:00）。
 * <p>
 * 示例 2：
 * 输入：arrivalTime = 13, delayedTime = 11
 * 输出：0
 * 解释：列车正点到站时间是 13:00 ，延误 11 小时，所以列车实际到站的时间是 13 + 11 = 24（在 24 小时制中表示为 00:00 ，所以返回 0）。
 * <p>
 * 提示：
 * 1 <= arrivaltime < 24
 * 1 <= delayedTime <= 24
 * @implNote
 * @since 2023/9/8 9:59
 */
public class Solution2651 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 37.65 MB
     * 击败 25.53% 使用 Java 的用户
     *
     * @param arrivalTime
     * @param delayedTime
     * @return
     */
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
