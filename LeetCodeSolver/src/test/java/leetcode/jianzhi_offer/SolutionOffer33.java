package leetcode.jianzhi_offer;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 33. 二叉搜索树的后序遍历序列 | 难度：中等 | 标签：栈、树、二叉搜索树、递归、二叉树、单调栈
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * |     5
 * |    / \
 * |   2   6
 * |  / \
 * | 1   3
 * <p>
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 * <p>
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 * <p>
 * 提示：
 * 数组长度 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 17:01
 */
public class SolutionOffer33 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 21.22% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了 21.20% 的用户
     * 通过测试用例：23 / 23
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(postorder[postorder.length - 1]);
        int idx = postorder.length - 2;
        int max = Integer.MAX_VALUE;
        while (idx >= 0) {
            if (postorder[idx] < stack.peek()) {
                max = stack.pop();
                while (!stack.isEmpty() && postorder[idx] < stack.peek()) {
                    max = stack.pop();
                }
            } else if (postorder[idx] > max) {
                return false;
            }
            stack.push(postorder[idx]);
            idx--;
        }
        return true;
    }
}
