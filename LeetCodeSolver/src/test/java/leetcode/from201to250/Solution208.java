package leetcode.from201to250;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/4/14 9:38
 * @Description: 208.实现 Trie (前缀树) | 难度：中等 | 标签：设计、字典树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution208 {
    /**
     * 执行用时： 64 ms , 在所有 Java 提交中击败了 10.10% 的用户
     * 内存消耗： 49.7 MB , 在所有 Java 提交中击败了 12.02% 的用户
     *
     * 效率低，题解里面直接在Trie下设属性：
     * private Trie[] children; // 代替哈希表，效率估计高些？
     * private boolean isEnd;
     * 可以直接使用Trie进行判断。
     */
    class Trie {
        TrieNode dummy = new TrieNode('#');

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode index = dummy;
            for (char c : word.toCharArray()) {
                if (index.map.containsKey(c)) {
                    index = index.map.get(c);
                } else {
                    TrieNode node = new TrieNode(c);
                    index.map.put(c, node);
                    index = node;
                }
            }
            index.exist = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode index = dummy;
            for (char c : word.toCharArray()) {
                if (index.map.containsKey(c)) {
                    index = index.map.get(c);
                } else {
                    return false;
                }
            }
            return index.exist;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode index = dummy;
            for (char c : prefix.toCharArray()) {
                if (index.map.containsKey(c)) {
                    index = index.map.get(c);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    class TrieNode {
        char c;
        boolean exist;
        HashMap<Character, TrieNode> map;

        public TrieNode(char c) {
            this.c = c;
            this.exist = false;
            this.map = new HashMap<>();
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
