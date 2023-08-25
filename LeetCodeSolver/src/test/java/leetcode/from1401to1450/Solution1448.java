package leetcode.from1401to1450;

import java.util.LinkedList;

/**
 * @author zhuxi
 * @apiNote 1448. 统计二叉树中好节点的数目 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * <p>
 * 示例 1：
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 * <p>
 * 示例 2：
 * 输入：root = [3,3,null,4,2]
 * 输出：3
 * 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * 解释：根节点是好节点。
 * <p>
 * 提示：
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 * @implNote
 * @since 2023/8/25 9:54
 */
public class Solution1448 {
    /**
     * 时间 4 ms
     * 击败 10.96% 使用 Java 的用户
     * 内存 51.43 MB
     * 击败 5.02% 使用 Java 的用户
     *
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        LinkedList<Integer> maxStack = new LinkedList<>();
        return goodNodes(maxStack, root);
    }

    public int goodNodes(LinkedList<Integer> maxStack, TreeNode root) {
        int count = 0;
        if (root == null) {
            return count;
        }
        if (maxStack.isEmpty()) {
            count++;
            maxStack.push(root.val);
        } else {
            if (root.val >= maxStack.peek()) {
                count++;
            }
            maxStack.push(Math.max(root.val, maxStack.peek()));
        }
        count += goodNodes(maxStack, root.left);
        count += goodNodes(maxStack, root.right);
        maxStack.pop();
        return count;
    }

    /**
     * 时间 2 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 47.85 MB
     * 击败 82.11% 使用 Java 的用户
     *
     * @param root
     * @return
     */
    public int goodNodes_2(TreeNode root) {
        return goodNodes_2(root.val, root);
    }

    public int goodNodes_2(int max, TreeNode root) {
        int count = 0;
        if (root == null) {
            return count;
        }
        if (root.val >= max) {
            count++;
        }
        max = Math.max(root.val, max);
        count += goodNodes_2(max, root.left);
        count += goodNodes_2(max, root.right);
        return count;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
