package leetcode.from1301to1400;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/1/23 9:50
 * @Description: 1319.连通网络的操作次数 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 * <p>
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * <p>
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 
 * <p>
 * 示例 1：
 * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
 * 输出：1
 * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
 * <p>
 * 示例 2：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * 输出：-1
 * 解释：线缆数量不足。
 * <p>
 * 示例 4：
 * 输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * 没有重复的连接。
 * 两台计算机不会通过多条线缆连接。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1319 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 45.44% 的用户
     * 内存消耗： 54.4 MB , 在所有 Java 提交中击败了 25.96% 的用户
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int[][] union = new int[n][2];
        for (int i = 0; i < n; i++) {
            union[i][0] = i;
            union[i][1] = 1;
        }
        int countUnion = n;
        for (int[] connection : connections) {
            if (connect(connection[0], connection[1], union)) {
                countUnion--;
            }
        }
        return countUnion - 1;
    }

    private boolean connect(int x, int y, int[][] union) {
        int xRoot = findRoot(union, x);
        int yRoot = findRoot(union, y);
        if (xRoot == yRoot) {
            return false;
        }
        if (union[xRoot][1] < union[yRoot][1]) {
            union[xRoot][0] = yRoot;
        } else {
            union[yRoot][0] = xRoot;
            if (union[xRoot][1] == union[yRoot][1]) {
                union[xRoot][1]++;
            }
        }
        return true;
    }

    private int findRoot(int[][] union, int i) {
        int root = i;
        while (union[root][0] != root) {
            root = union[root][0];
        }
        return root;
    }

    @Test
    public void makeConnectedTest() {
        Assert.assertEquals(1, makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        Assert.assertEquals(2, makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
        Assert.assertEquals(-1, makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
        Assert.assertEquals(0, makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));
    }
}
