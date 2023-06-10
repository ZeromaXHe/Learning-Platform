package leetcode.jianzhi_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 34. 二叉树中和为某一值的路径 | 难度：中等 | 标签：树、深度优先搜索、回溯、二叉树
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/10 18:06
 */
public class SolutionOffer34 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.84% 的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了 22.33% 的用户
     * 通过测试用例：114 / 114
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Integer> temp = new LinkedList<>();
        pathSum(root, temp, 0, target, result);
        return result;
    }

    private void pathSum(TreeNode root, LinkedList<Integer> temp, int sum, int target, List<List<Integer>> result) {
        if (root.left == null && root.right == null) {
            if (sum + root.val == target) {
                temp.add(root.val);
                result.add(new ArrayList<>(temp));
                temp.removeLast();
            }
            return;
        }
        if (root.left != null) {
            temp.add(root.val);
            pathSum(root.left, temp, sum + root.val, target, result);
            temp.removeLast();
        }
        if (root.right != null) {
            temp.add(root.val);
            pathSum(root.right, temp, sum + root.val, target, result);
            temp.removeLast();
        }
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
