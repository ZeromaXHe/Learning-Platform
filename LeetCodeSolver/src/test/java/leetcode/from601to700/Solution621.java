package leetcode.from601to700;

import java.util.Arrays;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/5 12:35
 * @Description: 621. 任务调度器 | 难度：中等 | 标签：贪心算法、队列、数组
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * <p>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * <p>
 * 示例 2：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *  
 * <p>
 * 提示：
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution621 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 97.81% 的用户
     * 内存消耗： 40.1 MB , 在所有 Java 提交中击败了 37.30% 的用户
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        int[] count = new int[26];
        // 记录每个任务对应有多少个
        for (int i = 0; i < tasks.length; i++) {
            count[tasks[i] - 'A']++;
        }
        // 任务具体是哪个字母并不重要，重要的是它有多少个
        // 所以我们就不保留对应tasks的名字了
        Arrays.sort(count);
        int noZeroIndex = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                noZeroIndex = i;
                break;
            }
        }
        // 最多的任务数
        int max = count[count.length - 1];
        // 和max相等的任务有多少个
        int maxCount = 1;
        // max-1大小可以填几层
        int subMaxLayer = 0;
        // 填的层数下一个可供填充的空位索引
        int subMaxLayerEmptyIndex = 0;
        for (int i = count.length - 2; i >= noZeroIndex; i--) {
            // 如果任务数
            if (count[i] == max) {
                maxCount++;
                if (maxCount > n) {
                    return tasks.length;
                }
                continue;
            }
            if (subMaxLayerEmptyIndex + count[i] < max - 1) {
                subMaxLayerEmptyIndex += count[i];
            } else {
                subMaxLayerEmptyIndex = (subMaxLayerEmptyIndex + count[i]) % (max - 1);
                subMaxLayer++;
            }
            if (subMaxLayer + maxCount > n) {
                return tasks.length;
            }
        }
        return (max - 1) * (n + 1) + maxCount;
    }
}
