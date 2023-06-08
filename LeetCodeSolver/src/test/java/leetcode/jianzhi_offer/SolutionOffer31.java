package leetcode.jianzhi_offer;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 31. 栈的压入、弹出序列 | 难度：中等 | 标签：栈、数组、模拟
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * <p>
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * <p>
 * 提示：
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/8 14:35
 */
public class SolutionOffer31 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 96.36% 的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了 10.68% 的用户
     * 通过测试用例：151 / 151
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int idxPop = 0;
        for (int i = 0; i < pushed.length; i++) {
            if (pushed[i] == popped[idxPop]) {
                idxPop++;
                // 连续弹出
                while (!stack.isEmpty() && idxPop < popped.length && stack.peek() == popped[idxPop]) {
                    stack.pop();
                    idxPop++;
                }
            } else {
                stack.push(pushed[i]);
            }
        }
        while (!stack.isEmpty()) {
            if (popped[idxPop] != stack.pop()) {
                return false;
            }
            idxPop++;
        }
        return true;
    }
}
