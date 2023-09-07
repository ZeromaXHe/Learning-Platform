package leetcode.from2551to2600;

/**
 * @author zhuxi
 * @apiNote 2594. 修车的最少时间 | 难度：中等 | 标签：数组、二分查找
 * 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
 * <p>
 * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。
 * <p>
 * 请你返回修理所有汽车 最少 需要多少时间。
 * <p>
 * 注意：所有机械工可以同时修理汽车。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：ranks = [4,2,3,1], cars = 10
 * 输出：16
 * 解释：
 * - 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
 * - 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
 * - 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
 * - 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * 16 分钟是修理完所有车需要的最少时间。
 * 示例 2：
 * <p>
 * 输入：ranks = [5,1,8], cars = 6
 * 输出：16
 * 解释：
 * - 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
 * - 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * - 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
 * 16 分钟时修理完所有车需要的最少时间。
 * <p>
 * 提示：
 * 1 <= ranks.length <= 105
 * 1 <= ranks[i] <= 100
 * 1 <= cars <= 106
 * @implNote
 * @since 2023/9/7 10:01
 */
public class Solution2594 {
    /**
     * 时间 29 ms
     * 击败 77.95% 使用 Java 的用户
     * 内存 51.58 MB
     * 击败 70.77% 使用 Java 的用户
     *
     * @param ranks
     * @param cars
     * @return
     */
    public long repairCars(int[] ranks, int cars) {
        long left = 1;
        long right = (long) ranks[0] * cars * cars;
        long result = right;
        loop:
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int rank : ranks) {
                count += (long) Math.sqrt(mid / rank);
                if (count >= cars) {
                    result = mid;
                    right = mid - 1;
                    continue loop;
                }
            }
            left = mid + 1;
        }
        return result;
    }
}
