package leetcode.from751to800;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/4 12:58
 * @Description: 781.森林中的兔子 | 难度：中等 | 标签：哈希表、数学
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * <p>
 * 返回森林中兔子的最少数量。
 * <p>
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 * <p>
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 * <p>
 * 输入: answers = []
 * 输出: 0
 * <p>
 * 说明:
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution781 {
    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 6.44% 的用户
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 45.07% 的用户
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            /// 替换为普通if-else后：
            // 执行用时： 3 ms , 在所有 Java 提交中击败了 68.18% 的用户
            // 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 71.97% 的用户
//            if (map.containsKey(answer)) {
//                map.put(answer, map.get(answer) + 1);
//            } else {
//                map.put(answer, 1);
//            }
            map.merge(answer, 1, Integer::sum);
        }
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            num += (key + 1) * (1 + (value - 1) / (key + 1));
        }
        return num;
    }
}
