package leetcode.from5601to5700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/22 11:04
 * @Description: 5608.完成所有任务的最少初始能量
 * 给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：
 * <p>
 * actuali 是完成第 i 个任务 需要耗费 的实际能量。
 * minimumi 是开始第 i 个任务前需要达到的最低能量。
 * 比方说，如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
 * <p>
 * 你可以按照 任意顺序 完成任务。
 * <p>
 * 请你返回完成所有任务的 最少 初始能量。
 * <p>
 * 示例 1：
 * 输入：tasks = [[1,2],[2,4],[4,8]]
 * 输出：8
 * 解释：
 * 一开始有 8 能量，我们按照如下顺序完成任务：
 * - 完成第 3 个任务，剩余能量为 8 - 4 = 4 。
 * - 完成第 2 个任务，剩余能量为 4 - 2 = 2 。
 * - 完成第 1 个任务，剩余能量为 2 - 1 = 1 。
 * 注意到尽管我们有能量剩余，但是如果一开始只有 7 能量是不能完成所有任务的，因为我们无法开始第 3 个任务。
 * <p>
 * 示例 2：
 * 输入：tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
 * 输出：32
 * 解释：
 * 一开始有 32 能量，我们按照如下顺序完成任务：
 * - 完成第 1 个任务，剩余能量为 32 - 1 = 31 。
 * - 完成第 2 个任务，剩余能量为 31 - 2 = 29 。
 * - 完成第 3 个任务，剩余能量为 29 - 10 = 19 。
 * - 完成第 4 个任务，剩余能量为 19 - 10 = 9 。
 * - 完成第 5 个任务，剩余能量为 9 - 8 = 1 。
 * <p>
 * 示例 3：
 * 输入：tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]]
 * 输出：27
 * 解释：
 * 一开始有 27 能量，我们按照如下顺序完成任务：
 * - 完成第 5 个任务，剩余能量为 27 - 5 = 22 。
 * - 完成第 2 个任务，剩余能量为 22 - 2 = 20 。
 * - 完成第 3 个任务，剩余能量为 20 - 3 = 17 。
 * - 完成第 1 个任务，剩余能量为 17 - 1 = 16 。
 * - 完成第 4 个任务，剩余能量为 16 - 4 = 12 。
 * - 完成第 6 个任务，剩余能量为 12 - 6 = 6 。
 * <p>
 * 提示：
 * 1 <= tasks.length <= 105
 * 1 <= actual​i <= minimumi <= 104
 * @Modified By: ZeromaXHe
 */
public class Solution5608 {
    /**
     * 34 / 34 个通过测试用例
     * 状态：通过
     * 执行用时: 35 ms
     * 内存消耗: 98.2 MB
     * <p>
     * 思路一开始错了，浪费好多时间……
     * 想成要分情况讨论，最后发现直接用gap（就是阈值和消耗量的差）排序，这就是判断任务处理顺序特征值
     * <p>
     * 思考过程大致如下：
     * 先是想到了gap，但是自己的思路限制于只考虑最大的正值了。
     * 下面注释的只是一部分之前的思路，其实之前还加了好多判断最大gap值的判断。
     * 然后想到这里应该不能只是判断最大的，还要考虑历史数据，就想是不是要用栈保存一下数据。
     * 最后快12：00结束前的时候，突然灵光一闪，直接排序，才做出来了。
     * <p>
     * 最后答案居然如此简单，怪不得很多人好早就做完了……
     *
     * @param tasks
     * @return
     */
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(o -> (o[1] - o[0])));
//        int energyConsume = 0;
//        int positiveSum = 0;
//        int negativeSum = 0;
//        for (int i = 0; i < tasks.length; i++) {
//            energyConsume += tasks[i][0];
//            int gap = tasks[i][1] - tasks[i][0];
//            if (gap > 0) {
//                positiveSum += gap;
//            } else if (gap < 0) {
//                negativeSum += gap;
//            }
//        }
//        // 全是负的gap
//        if (tasks[tasks.length - 1][1] - tasks[tasks.length - 1][0] <= 0) {
//            return energyConsume;
//        }
//        // 全是正的gap
//        if (tasks[0][1] - tasks[0][0] >= 0) {
//            return energyConsume + tasks[0][1] - tasks[0][0];
//        }
        // 对于有正有负的情况，我们把正的（阈值大的）都先处理，负的(消耗多的)最后处理
        int energy = 0;
        for (int i = 0; i < tasks.length; i++) {
            energy += tasks[i][0];
            if (energy < tasks[i][1]) {
                energy = tasks[i][1];
            }
        }
        return energy;
    }

    //[[1,1],[1,3]] 预期3 输出2。 这个案例才让我想到要注释掉之前特殊情况分析
}
