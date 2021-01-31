package com.zerox.entity.unionset;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/31 16:23
 * @Description: 普通并查集模板
 * @Modified By: ZeromaXHe
 */
public class UnionSet {
        int[] parent;

        public UnionSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }

}
