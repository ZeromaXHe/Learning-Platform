package leetcode.from501to550;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/7 15:18
 * @Description: 547.省份数量 | 难度：中等 | 标签：深度优先搜索、并查集
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *  
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution547 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.49% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 86.27% 的用户
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        // 并查集数组，同一个并查集中的元素拥有同一个parent值
        int[] parent = new int[provinces];
        // 初始化并查集，每一个元素在各自的并查集中
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        // 检查矩阵，将相互连接的两个并查集合并
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        // 计数。parent[i]==i即为并查集的最高父节点，数这些节点的数量即可
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    /**
     * 将index1所在并查集与index2所在并查集置于一个并查集下
     *
     * @param parent
     * @param index1
     * @param index2
     */
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     * 递归寻找并查集的最高的父节点
     *
     * @param parent
     * @param index
     * @return
     */
    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
