/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val);
    this.left = (left === undefined ? null : left);
    this.right = (right === undefined ? null : right);
}

/**
 * 897.递增顺序搜索树 | 难度：简单 | 标签：树、深度优先遍历、递归
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * <p>
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * <p>
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 88 ms , 在所有 JavaScript 提交中击败了 50.45% 的用户
 * 内存消耗： 39.3 MB , 在所有 JavaScript 提交中击败了 24.93% 的用户
 *
 * @param {TreeNode} root
 * @return {TreeNode}
 */
// js 全局变量
var lastNode;
var increasingBST = function (root) {
    let dummy = new TreeNode();
    lastNode = dummy;
    inOrderTraverse(root, lastNode);
    return dummy.right;
};

function inOrderTraverse(root) {
    if (root == null) {
        return;
    }
    inOrderTraverse(root.left);
    // 在中序遍历的过程中修改节点指向
    lastNode.right = root;
    root.left = null;
    lastNode = root;

    inOrderTraverse(root.right)
}