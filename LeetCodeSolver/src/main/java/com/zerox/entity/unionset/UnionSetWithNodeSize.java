package com.zerox.entity.unionset;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/31 16:24
 * @Description: 带节点计数数目的并查集模板
 * @Modified By: ZeromaXHe
 */
public class UnionSetWithNodeSize {
    /**
     * 当前结点的父亲结点
     */
    private int[] parent;
    /**
     * 以当前结点为根结点的子树的结点总数
     */
    private int[] size;

    public UnionSetWithNodeSize(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 路径压缩，只要求每个不相交集合的「根结点」的子树包含的结点总数数值正确即可，因此在路径压缩的过程中不用维护数组 size
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }
        parent[rootX] = rootY;
        // 在合并的时候维护数组 size
        size[rootY] += size[rootX];
    }

    /**
     * @param x
     * @return x 在并查集的根结点的子树包含的结点总数
     */
    public int getSize(int x) {
        int root = find(x);
        return size[root];
    }
}
