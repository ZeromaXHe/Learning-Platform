package leetcode.from1101to1150;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author zhuxi
 * @apiNote 1123. 最深叶节点的最近公共祖先 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、哈希表、二叉树
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * <p>
 * 回想一下：
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * <p>
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
 * <p>
 * 示例 3：
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
 * <p>
 * 提示：
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
 * <p>
 * 注意：本题与力扣 865 重复：https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * @implNote
 * @since 2023/9/6 9:58
 */
public class Solution1123 {
    /**
     * 时间 1 ms
     * 击败 36.32% 使用 Java 的用户
     * 内存 40.95 MB
     * 击败 66.04% 使用 Java 的用户
     * <p>
     * 官方题解
     *
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return f(root).getKey();
    }

    private Pair<TreeNode, Integer> f(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);
        }

        Pair<TreeNode, Integer> left = f(root.left);
        Pair<TreeNode, Integer> right = f(root.right);

        if (left.getValue() > right.getValue()) {
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }
        if (left.getValue() < right.getValue()) {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
        return new Pair<>(root, left.getValue() + 1);
    }

    /**
     * 时间 5 ms
     * 击败 3.30% 使用 Java 的用户
     * 内存 40.85 MB
     * 击败 83.02%使用 Java 的用户
     *
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves_parentMap(TreeNode root) {
        HashSet<TreeNode> lastLayer = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            lastLayer.clear();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                lastLayer.add(poll);
                if (poll.left != null) {
                    queue.offer(poll.left);
                    parentMap.put(poll.left, poll);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    parentMap.put(poll.right, poll);
                }
            }
        }
        HashSet<TreeNode> parentLayer = new HashSet<>();
        while (lastLayer.size() > 1) {
            parentLayer.clear();
            for (TreeNode treeNode : lastLayer) {
                parentLayer.add(parentMap.get(treeNode));
            }
            HashSet<TreeNode> temp = lastLayer;
            lastLayer = parentLayer;
            parentLayer = temp;
        }
        return lastLayer.iterator().next();
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
