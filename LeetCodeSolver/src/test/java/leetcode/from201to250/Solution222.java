package leetcode.from201to250;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/24 20:07
 * @Description: 222.完全二叉树的节点个数 | 难度：中等 | 标签：树、二分查找
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution222 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了 84.66% 的用户
     *
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int deep = 0;
        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
            deep++;
        }
        if (deep == 0) {
            return 1;
        }
        return binaryFindLastNode(root, deep, 0, 1 << deep) + (1 << deep);
    }

    private int binaryFindLastNode(TreeNode root, int deep, int from, int to) {
        if (from == to) {
            return from - 1;
        }
        if (from == to - 1) {
            if (exist(from, deep, root)) {
                return from;
            } else {
                return from - 1;
            }
        }
        if (exist((from + to) / 2, deep, root)) {
            return binaryFindLastNode(root, deep, (from + to) / 2 + 1, to);
        } else {
            return binaryFindLastNode(root, deep, from, (from + to) / 2);
        }

    }

    private boolean exist(int num, int deep, TreeNode root) {
        TreeNode node = root;
        int div = 1 << (deep - 1);
        while (div > 1) {
            if (num / div == 0) {
                node = node.left;
            } else {
                node = node.right;
                num -= div;
            }
            div >>= 1;
        }
        if (num == 0) {
            node = node.left;
        } else {
            node = node.right;
        }
        return node != null;
    }

    public static void main(String[] args) {
        Solution222 solution222 = new Solution222();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        System.out.println(solution222.countNodes(root));
        // 这个有点坑
        System.out.println(solution222.countNodes(null));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
