package leetcode.from701to800;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/4/13 11:39
 * @Description: 783.二叉搜索树节点最小距离 | 难度：简单 | 标签：树、深度优先搜索、递归
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 * <p>
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * <p>
 * 提示：
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution783 {
    /**
     * Definition for a binary tree node.
     * |public class TreeNode {
     * |    int val;
     * |    TreeNode left;
     * |    TreeNode right;
     * |    TreeNode() {}
     * |    TreeNode(int val) { this.val = val; }
     * |    TreeNode(int val, TreeNode left, TreeNode right) {
     * |        this.val = val;
     * |        this.left = left;
     * |        this.right = right;
     * |    }
     * |}
     */

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 6.55% 的用户
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode index = root;
        while (index != null) {
            stack.push(index);
            index = index.left;
        }
        int pre = Integer.MIN_VALUE / 2;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.val - pre < min) {
                min = pop.val - pre;
            }
            pre = pop.val;
            index = pop.right;
            while (index != null) {
                stack.push(index);
                index = index.left;
            }
        }
        return min;
    }

    /**
     * 超时了
     *
     * @param root
     * @return
     */
    public int minDiffInBST_outOfTime(TreeNode root) {
        return traverse(root)[0];
    }

    private int[] traverse(TreeNode root) {
        int min = Integer.MAX_VALUE;
        int first = root.val;
        int last = root.val;
        if (root.left != null) {
            int[] minLastLeft = traverse(root.left);
            min = Math.min(min, root.val - minLastLeft[2]);
            min = Math.min(min, minLastLeft[0]);
            first = minLastLeft[1];
        }
        if (root.right != null) {
            int[] minLastRight = traverse(root.right);
            min = Math.min(min, minLastRight[1] - root.val);
            min = Math.min(min, traverse(root.right)[0]);
            last = minLastRight[2];
        }
        return new int[]{min, first, last};
    }

    // [90,69,null,49,89,null,52]
    // 预期 1，输出 3
    // [234,null,591,null,2571,null,3837,null,5926,null,8465,null,9471,null,9681,null,10263,null,11403,null,13906,null,14201,null,15931,null,16397,null,17296,null,17449,null,17752,null,18558,null,18669,null,20879,null,23064,null,24890,null,25388,null,25914,null,28273,null,29457,null,30932,null,31279,null,34138,null,34230,null,34356,null,34486,null,34958,null,36677,null,38658,null,40226,null,40244,null,42026,null,43184,null,43371,null,45496,null,46024,null,46417,null,47309,null,49787,null,50275,null,51843,null,52050,null,53901,null,54220,null,54348,null,57591,null,57701,null,58007,null,58787,null,59838,null,60106,null,60237,null,60720,null,60753,null,61117,null,61341,null,61995,null,62972,null,63841,null,64967,null,65297,null,65491,null,65502,null,66606,null,66692,null,68188,null,69132,null,69316,null,70677,null,71346,null,71839,null,72978,null,73559,null,76035,null,76445,null,79762,null,80123,null,81838,null,84449,null,86564,null,86858,null,87934,null,88354,null,88572,null,90242,null,91311,null,92304,null,92483,null,92919,null,93393,null,93847,null,94128,null,97767,null,99077]
    // 预期 11, 超时

    class TreeNode {
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
