package leetcode.from101to150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/21 11:39
 * @Description: 133. 克隆图 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、哈希表
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * | class Node {
 * |     public int val;
 * |     public List<Node> neighbors;
 * | }
 * <p>
 * 测试用例格式：
 * <p>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * <p>
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * <p>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * <p>
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * <p>
 * 示例 2：
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * <p>
 * 示例 3：
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * <p>
 * 示例 4：
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 * <p>
 * 提示：
 * 节点数不超过 100 。
 * 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution133 {
    /**
     * | // Definition for a Node.
     * | class Node {
     * |     public int val;
     * |     public List<Node> neighbors;
     * |     public Node() {
     * |         val = 0;
     * |         neighbors = new ArrayList<Node>();
     * |     }
     * |     public Node(int _val) {
     * |         val = _val;
     * |         neighbors = new ArrayList<Node>();
     * |     }
     * |     public Node(int _val, ArrayList<Node> _neighbors) {
     * |         val = _val;
     * |         neighbors = _neighbors;
     * |     }
     * | }
     * <p>
     * 执行用时： 29 ms , 在所有 Java 提交中击败了 18.93% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 36.75% 的用户
     * 通过测试用例： 22 / 22
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> visit = new HashMap<>();
        Node nodeNew = new Node(node.val);
        visit.put(node, nodeNew);
        dfs(visit, node, nodeNew);
        return nodeNew;
    }

    private void dfs(Map<Node, Node> visit, Node node, Node nodeNew) {
        if (!node.neighbors.isEmpty()) {
            for (Node neighbor : node.neighbors) {
                Node neighborNew = visit.get(neighbor);
                if (neighborNew == null) {
                    neighborNew = new Node(neighbor.val);
                    visit.put(neighbor, neighborNew);
                    dfs(visit, neighbor, neighborNew);
                }
                nodeNew.neighbors.add(neighborNew);
            }
        }
    }

    /**
     * 执行用时： 25 ms , 在所有 Java 提交中击败了 99.78% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 59.06% 的用户
     * 通过测试用例： 22 / 22
     * <p>
     * 这样就莫名其妙的更快…… 难道方法参数数量的影响这么大吗？
     * 好吧，仔细看了一下，就快了4ms……
     *
     * @param node
     * @return
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> visit = new HashMap<>();
        return dfs2(visit, node);
    }

    private Node dfs2(Map<Node, Node> visit, Node node) {
        Node nodeNew = visit.get(node);
        if (nodeNew != null) {
            return nodeNew;
        }
        nodeNew = new Node(node.val);
        visit.put(node, nodeNew);
        if (!node.neighbors.isEmpty()) {
            for (Node neighbor : node.neighbors) {
                nodeNew.neighbors.add(dfs2(visit, neighbor));
            }
        }
        return nodeNew;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
