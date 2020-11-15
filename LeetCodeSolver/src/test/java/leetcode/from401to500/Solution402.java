package leetcode.from401to500;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author ZeromaXHe
 * @date 2020/11/15 12:25
 * @Description 402. 移掉K位数字 | 难度：中等 | 标签：栈、贪心算法
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * <p>
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * <p>
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution402 {
    /**
     * 执行用时： 8 ms, 在所有 Java 提交中击败了 74.97% 的用户
     * 内存消耗： 38.5 MB, 在所有 Java 提交中击败了 84.43% 的用户
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (num == null || k == num.length() || num.length() == 0) {
            return "0";
        }
        LinkedList<Character> stack = new LinkedList<>();
        int poped = 0;
        stack.addLast(num.charAt(0));
        // 将不符合递增规律的数字弹出，弹出的是较大的
        for (int i = 1; i < num.length(); i++) {
            while (poped < k && !stack.isEmpty() && stack.peekLast() > num.charAt(i)) {
                stack.removeLast();
                poped++;
            }
            stack.addLast(num.charAt(i));
            //System.out.println("stack:" + stack + " poped: " + poped);
        }
        // 清理没有弹出的数字
        while(poped<k){
            stack.removeLast();
            poped++;
        }
        // 清除首位0
        while (!stack.isEmpty() && stack.peekFirst() == '0') {
            stack.removeFirst();
        }
        // stack为空保证有返回
        if (stack.isEmpty()) {
            return "0";
        }
        // 输出stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution402 solution402 = new Solution402();
        // 1219
        System.out.println(solution402.removeKdigits("1432219", 3));
        // 200
        System.out.println(solution402.removeKdigits("10200", 1));
        // 0
        System.out.println(solution402.removeKdigits("10", 2));
        // 11
        System.out.println(solution402.removeKdigits("112", 1));
    }
}
