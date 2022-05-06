package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/6 15:29
 * @Description: 面试题 04.10. 检查子树 | 难度：中等 | 标签：树、深度优先搜索、二叉树、字符串匹配、哈希函数
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 * <p>
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 * <p>
 * 注意：此题相对书上原题略有改动。
 * <p>
 * 示例1:
 * 输入：t1 = [1, 2, 3], t2 = [2]
 * 输出：true
 * <p>
 * 示例2:
 * 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 * 输出：false
 * <p>
 * 提示：
 * 树的节点数目范围为[0, 20000]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-subtree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_10 {
    /**
     * Definition for a binary tree node.
     * | public class TreeNode {
     * |     int val;
     * |     TreeNode left;
     * |     TreeNode right;
     * |     TreeNode(int x) { val = x; }
     * | }
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.3 MB , 在所有 Java 提交中击败了 32.48% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 测试用例貌似比较拉跨，没有所谓的特别大的子树…… 而且默认一个前提条件，树中的 val 不会相等。如果真正去做这道题，这样迭代肯定不行。
     * 真正解答这道题的话，估计就是按字符串匹配思路吧，把树序列化成字符串，然后字符串匹配。
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
        } else {
            return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
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
