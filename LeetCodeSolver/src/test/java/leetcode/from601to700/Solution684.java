package leetcode.from601to700;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/13 9:49
 * @Description: 684. 冗余连接 | 难度：中等 | 标签：图、并查集、树
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * <p>
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * <p>
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * <p>
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * <p>
 * 示例 1：
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 * 1
 * / \
 * 2 - 3
 * <p>
 * 示例 2：
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * <p>
 * 注意:
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution684 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 87.99% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 54.62% 的用户
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // unionSet[i][0]为并查集的父节点索引（0代表无父节点了）
        // unionSet[i][1]为并查集的高度-1
        int[][] unionSet = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            int firstRoot = edges[i][0];
            int secondRoot = edges[i][1];
            while (unionSet[firstRoot][0] != 0) {
                firstRoot = unionSet[firstRoot][0];
            }
            while (unionSet[secondRoot][0] != 0) {
                secondRoot = unionSet[secondRoot][0];
            }
            if (firstRoot == secondRoot) {
                return new int[]{edges[i][0], edges[i][1]};
            }
            if (unionSet[firstRoot][1] > unionSet[secondRoot][1]) {
                unionSet[secondRoot][0] = firstRoot;
                unionSet[firstRoot][1]++;
            } else {
                unionSet[firstRoot][0] = secondRoot;
                unionSet[secondRoot][1]++;
            }
        }
        return null;
    }
}
