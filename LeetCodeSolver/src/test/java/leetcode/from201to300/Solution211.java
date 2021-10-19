package leetcode.from201to300;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/10/19 9:53
 * @Description: 211. 添加与搜索单词 - 数据结构设计 | 难度：中等 | 标签：深度优先搜索、设计、字典树、字符串
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * 提示：
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution211 {
    @Test
    public void testWordDictionary() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        Assert.assertFalse(wordDictionary.search("pad")); // return False
        Assert.assertTrue(wordDictionary.search("bad")); // return True
        Assert.assertTrue(wordDictionary.search(".ad")); // return True
        Assert.assertTrue(wordDictionary.search("b..")); // return True
        Assert.assertFalse(wordDictionary.search("b...")); // return True

        // 通过测试用例：8 / 13
        // ["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
        // [              [],    ["a"],   ["ab"],   ["a"],  ["a."],  ["ab"],  [".a"],  [".b"], ["ab."],   ["."],  [".."]]
        // 预期：[       null,    null,      null,    true,    true,    true,   false,    true,   false,    true,    true]
        WordDictionary wordDictionary2 = new WordDictionary();
        wordDictionary2.addWord("a");
        wordDictionary2.addWord("ab");
        Assert.assertTrue(wordDictionary2.search("a"));
        Assert.assertTrue(wordDictionary2.search("a."));
        Assert.assertTrue(wordDictionary2.search("ab"));
        Assert.assertFalse(wordDictionary2.search(".a"));
        Assert.assertTrue(wordDictionary2.search(".b"));
        Assert.assertFalse(wordDictionary2.search("ab."));
        Assert.assertTrue(wordDictionary2.search("."));
        Assert.assertTrue(wordDictionary2.search(".."));
    }

    /**
     * 执行用时： 45 ms , 在所有 Java 提交中击败了 46.45% 的用户
     * 内存消耗： 49.1 MB , 在所有 Java 提交中击败了 45.32% 的用户
     * 通过测试用例：13 / 13
     */
    class WordDictionary {
        WordDictionary[] next = new WordDictionary[26];
        boolean exist = false;

        public WordDictionary() {
        }

        public void addWord(String word) {
            addWord(word, 0);
        }

        private void addWord(String word, int index) {
            if (index >= word.length()) {
                this.exist = true;
                return;
            }
            int i = word.charAt(index) - 'a';
            WordDictionary nextWordDic = next[i];
            if (nextWordDic == null) {
                next[i] = new WordDictionary();
            }
            next[i].addWord(word, index + 1);
        }

        public boolean search(String word) {
            return search(word, 0);
        }

        private boolean search(String word, int index) {
            if (index >= word.length()) {
                return this.exist;
            }
            if (word.charAt(index) == '.') {
                for (WordDictionary wordDictionary : next) {
                    if (wordDictionary != null && wordDictionary.search(word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
            int i = word.charAt(index) - 'a';
            WordDictionary nextWordDic = next[i];
            if (nextWordDic == null) {
                return false;
            }
            return nextWordDic.search(word, index + 1);
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
