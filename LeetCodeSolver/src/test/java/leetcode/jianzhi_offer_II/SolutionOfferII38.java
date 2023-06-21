package leetcode.jianzhi_offer_II;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 038. 每日温度 | 难度：中等 | 标签：栈、数组、单调栈
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * <p>
 * 注意：本题与主站 739 题相同： https://leetcode-cn.com/problems/daily-temperatures/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/iIQa4I
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/21 17:54
 */
public class SolutionOfferII38 {
    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了 90.38% 的用户
     * 内存消耗：55 MB, 在所有 Java 提交中击败了 43.75% 的用户
     * 通过测试用例：47 / 47
     * <p>
     * 提交完才反应过来最后把 stack 剩余的 pop 出来在 result 里面置 0 的步骤有点多余，不过就这样吧~
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        LinkedList<int[]> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                int idx = stack.pop()[1];
                result[idx] = i - idx;
            }
            stack.push(new int[]{temperatures[i], i});
        }
        while (!stack.isEmpty()) {
            result[stack.pop()[1]] = 0;
        }
        return result;
    }
}
