package leetcode.from951to1000;

/**
 * @Author: zhuxi
 * @Time: 2021/1/25 15:08
 * @Description: 959.由斜杠划分区域 | 难度：中等 | 标签：深度优先搜索、并查集、图
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * <p>
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * <p>
 * 返回区域的数目。
 * <p>
 * 示例 1：
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 * 解释：2x2 网格如下：
 * <p>
 * 示例 2：
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 * 解释：2x2 网格如下：
 * <p>
 * 示例 3：
 * 输入：
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 2x2 网格如下：
 * <p>
 * 示例 4：
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 2x2 网格如下：
 * <p>
 * 示例 5：
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 * 解释：2x2 网格如下：
 * <p>
 * 提示：
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution959 {
    /**
     * 参考评论区的题解： https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/you-xie-gang-hua-fen-qu-yu-by-leetcode-s-ztob/758886
     * <p>
     * n*n的网格，可以看作是(n+1)*(n+1)个坐标点。比如2*2的网格，可以看作(0,0)、(0,1)、(0,2)、(1,0)、(1,1)、(1,2)、(2,0)、(2,1)、(2,2)这9个坐标点。
     * 因此初始网格可以看作是上下左右四条边上的坐标点相连构成的连通分支。而每一个斜杠可以看作是(n+1)*(n+1)个坐标点中某两个坐标点的连边。
     * 因此可以这样做：首先初始化，将n*n网格的四条边上的坐标点相连构成一个连通分支，在初始化时会形成一个大区域。
     * 接着遍历所有斜杠(或空白，空白表示无连边)，将斜杠涉及到的两个坐标点合并成，如果这两个点本来就在一个连通分支里，则区域数加1。
     * 最终所有斜杠遍历完后，返回并查集中一直更新的区域数即可。
     * <p>
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 89.82% 的用户
     *
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        //网格大小
        int n = grid.length;
        //n*n的网格共有(n+1)*(n+1)个坐标点
        UnionFind uf = new UnionFind((n + 1) * (n + 1));
        //先连接网格的4个边界，将边界上的坐标点两两相连
        for (int i = 0; i < n; i++) {
            //上边界
            uf.union(i, i + 1);
            //下边界
            uf.union(n * (n + 1) + i, n * (n + 1) + i + 1);
            //左边界
            uf.union(getPosition(i, 0, n), getPosition(i + 1, 0, n));
            //右边界
            uf.union(getPosition(i, n, n), getPosition(i + 1, n, n));
        }

        //处理输入数据
        for (int i = 0; i < n; i++) {
            char[] c = grid[i].toCharArray();
            //j用来保存坐标点的纵坐标
            for (int j = 0; j < c.length; j++) {
                if (c[j] == '/') {
                    //一条斜杠涉及到2个坐标点。这种情况属于右上坐标点连接左下1*1对角线的坐标点
                    uf.union(getPosition(i, j + 1, n), getPosition(i + 1, j, n));
                } else if (c[j] == '\\') {
                    //一条斜杠涉及到2个坐标点。这种情况属于左上坐标点连接右下1*1对角线的坐标点
                    uf.union(getPosition(i, j, n), getPosition(i + 1, j + 1, n));
                }
            }
        }

        return uf.areaCount;
    }

    public int getPosition(int row, int col, int n) {
        //n+1是因为n*n的网格共有(n+1)*(n+1)个坐标点
        return row * (n + 1) + col;
    }

    class UnionFind {
        int[] parent;
        int areaCount;

        public UnionFind(int n) {
            //初始区域个数为0，因为在将所有边界坐标点相连的时候，最终会有一次两个点属于同一连通分支的情况
            areaCount = 0;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            int rootX = find(index1);
            int rootY = find(index2);

            if (rootX == rootY) {
                //如果两个坐标原本属于一个连通分支，则区域数加一
                areaCount++;
            } else {
                parent[rootX] = rootY;
            }
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }
}
