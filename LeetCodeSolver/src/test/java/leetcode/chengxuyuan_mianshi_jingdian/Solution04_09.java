package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 14:54
 * @Description: 面试题 04.09. 二叉搜索树序列 | 难度：困难 | 标签：树、二叉搜索树、回溯、二叉树
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 * <p>
 * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
 * <p>
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: [[2,1,3],[2,3,1]]
 * 解释: 数组 [2,1,3]、[2,3,1] 均可以通过从左向右遍历元素插入树中形成以下二叉搜索树
 * |       2
 * |      / \
 * |     1   3
 * <p>
 * 示例 2:
 * 输入: root = [4,1,null,null,3,2]
 * 输出: [[4,1,3,2]]
 * <p>
 * 提示：
 * 二叉搜索树中的节点数在 [0, 1000] 的范围内
 * 1 <= 节点值 <= 10^6
 * 用例保证符合要求的数组数量不超过 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bst-sequences-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_09 {
    @Test
    public void BSTSequencesTest() {
        // [5,2,null,1,4,null,null,3]
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        node5.left = node2;
        TreeNode node1 = new TreeNode(1);
        node2.left = node1;
        TreeNode node4 = new TreeNode(4);
        node2.right = node4;
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        System.out.println(BSTSequences(node5));
    }

    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.7 MB , 在所有 Java 提交中击败了 69.88% 的用户
     * 通过测试用例： 24 / 24
     * <p>
     * 类似返回拓扑排序的所有结果，只是这里拓扑排序的图是棵树。
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            result.add(new ArrayList<>());
            return result;
        }
        LinkedList<TreeNode> choose = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        choose.add(root);
        backtrace(result, choose, temp);
        return result;
    }

    private void backtrace(List<List<Integer>> result, LinkedList<TreeNode> choose, LinkedList<Integer> temp) {
        if (choose.isEmpty()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        int size = choose.size();
        for (int i = 0; i < size; i++) {
            TreeNode first = choose.removeFirst();
            if (first.left != null) {
                choose.addLast(first.left);
            }
            if (first.right != null) {
                choose.addLast(first.right);
            }
            temp.addLast(first.val);
            backtrace(result, choose, temp);
            temp.removeLast();
            if (first.right != null) {
                choose.removeLast();
            }
            if (first.left != null) {
                choose.removeLast();
            }
            choose.addLast(first);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
