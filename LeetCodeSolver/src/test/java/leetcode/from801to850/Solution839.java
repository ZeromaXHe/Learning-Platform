package leetcode.from801to850;

import java.util.Arrays;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/1/31 16:03
 * @Description: 839.相似字符串组 | 难度：困难 | 标签：深度优先搜索、并查集、图
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。
 * <p>
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * <p>
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
 * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * <p>
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 * <p>
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= strs.length <= 100
 * 1 <= strs[i].length <= 1000
 * sum(strs[i].length) <= 2 * 104
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 * <p>
 * 备注：
 *       字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/similar-string-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution839 {
    /**
     * 执行用时： 16 ms , 在所有 Java 提交中击败了 76.39% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 70.92% 的用户
     *
     * @param strs
     * @return
     */
    public int numSimilarGroups(String[] strs) {
        UnionFind uf = new UnionFind(strs.length);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (!uf.connected(i, j) && simular(strs, i, j)) {
                    uf.unite(i, j);
                }
            }
        }
        return uf.setCount;
    }

    private boolean simular(String[] strs, int i, int j) {
        if (strs[i].equals(strs[j])) {
            return true;
        }
        int count = 0;
        for (int k = 0; k < strs[i].length(); k++) {
            if (strs[i].charAt(k) != strs[j].charAt(k)) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
        }
        return count == 2;
    }

    // 并查集模板
    class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int findset(int x) {
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        public boolean unite(int x, int y) {
            x = findset(x);
            y = findset(y);
            if (x == y) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;
            return true;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }
}
