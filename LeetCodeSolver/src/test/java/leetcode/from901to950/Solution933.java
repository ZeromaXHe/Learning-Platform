package leetcode.from901to950;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 10:19
 * @Description: 933. 最近的请求次数 | 难度：简单 | 标签：设计、队列、数据流
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * <p>
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
 * 确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 * 示例 1：
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * <p>
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 * <p>
 * 提示：
 * 1 <= t <= 10^9
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 10^4 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution933 {
    /**
     * 执行用时： 18 ms , 在所有 Java 提交中击败了 96.32% 的用户
     * 内存消耗： 49.8 MB , 在所有 Java 提交中击败了 20.48% 的用户
     * 通过测试用例： 68 / 68
     */
    class RecentCounter {
        private LinkedList<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            queue.offer(t);
            return queue.size();
        }
    }

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
}
