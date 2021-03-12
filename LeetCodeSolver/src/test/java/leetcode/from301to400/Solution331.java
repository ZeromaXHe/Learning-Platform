package leetcode.from301to400;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/3/12 16:12
 * @Description: 331.验证二叉树的前序序列化 | 难度：中等 | 标签：栈
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * |      _9_
 * |     /   \
 * |    3     2
 * |   / \   / \
 * |  4   1  #  6
 * | / \ / \   / \
 * | # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * <p>
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * <p>
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "1,#"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: "9,#,#,1"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution331 {
    /**
     * 没必要真实模拟前序遍历的过程，直接计算剩余可填充的叶子节点数量即可。效率大大提升
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 89.05% 的用户
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        int count = 1;
        char[] chars = preorder.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',') {
                continue;
            }
            if (chars[i] == '#') {
                count--;
                if (count == 0) {
                    return i == chars.length - 1;
                }
            } else {
                while (i + 1 < chars.length && chars[i + 1] != '#' && chars[i + 1] != ',') {
                    i++;
                }
                count += 1;
            }
        }
        return count == 0;
    }

    /**
     * 模拟了叶子节点的匹配过程，导致效率不高
     * <p>
     * 执行用时： 15 ms , 在所有 Java 提交中击败了 5.77% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 31.36% 的用户
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_slow(String preorder) {
        if ("#".equals(preorder)) {
            return true;
        }
        LinkedList<int[]> stack = new LinkedList<>();
        String[] strs = preorder.split(",");
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            if ("#".equals(s)) {
                if (stack.isEmpty()) {
                    return false;
                }
                while (!stack.isEmpty()) {
                    int[] peek = stack.peek();
                    if (peek[1] == 1) {
                        stack.pop();
                        if (stack.isEmpty()) {
                            return i == strs.length - 1;
                        }
                    } else {
                        peek[1] = 1;
                        break;
                    }
                }
            } else {
                stack.push(new int[]{Integer.parseInt(s), 0});
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void isValidSerializationTest() {
        Assert.assertTrue(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        Assert.assertFalse(isValidSerialization("1,#"));
        Assert.assertFalse(isValidSerialization("9,#,#,1"));
        Assert.assertFalse(isValidSerialization("9,#,#,1,#,#"));
        Assert.assertTrue(isValidSerialization("#"));
    }
}
