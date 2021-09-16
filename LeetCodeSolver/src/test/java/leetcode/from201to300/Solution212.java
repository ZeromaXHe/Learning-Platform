package leetcode.from201to300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2021/9/16 9:43
 * @Description: 212. 单词搜索 II | 难度：困难 | 标签：字典树、数组、字符串、回溯、矩阵
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution212 {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 执行用时： 175 ms , 在所有 Java 提交中击败了 55.28% 的用户
     * 内存消耗： 37.1 MB , 在所有 Java 提交中击败了 67.42% 的用户
     * 通过测试用例： 42 / 42
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, root, resultSet);
            }
        }
        return new ArrayList<>(resultSet);
    }

    private void dfs(int i, int j, char[][] board, Trie trie, Set<String> resultSet) {
        char c = board[i][j];
        Trie child = trie.children[c - 'a'];
        if (child == null) {
            return;
        } else if (child.word != null) {
            resultSet.add(child.word);
        }
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int i2 = i + dir[0], j2 = j + dir[1];
            if (i2 >= 0 && i2 < board.length
                    && j2 >= 0 && j2 < board[0].length
                    && board[i2][j2] != '#') {
                dfs(i2, j2, board, child, resultSet);
            }
        }
        board[i][j] = c;
    }

    static class Trie {
        String word;
        Trie[] children = new Trie[26];

        void insert(String word) {
            char[] chars = word.toCharArray();
            Trie ptr = this;
            for (char aChar : chars) {
                if (ptr.children[aChar - 'a'] == null) {
                    Trie trie = new Trie();
                    ptr.children[aChar - 'a'] = trie;
                    ptr = trie;
                } else {
                    ptr = ptr.children[aChar - 'a'];
                }
            }
            ptr.word = word;
        }
    }
}
