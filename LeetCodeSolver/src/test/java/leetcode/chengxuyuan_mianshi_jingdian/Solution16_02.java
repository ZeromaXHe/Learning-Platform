package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2022/5/14 17:59
 * @Description: 面试题 16.02. 单词频率 | 难度：中等 | 标签：设计、字典树、数组、哈希表、字符串
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在书中出现的频率
 * <p>
 * 示例：
 * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
 * wordsFrequency.get("you"); //返回0，"you"没有出现过
 * wordsFrequency.get("have"); //返回2，"have"出现2次
 * wordsFrequency.get("an"); //返回1
 * wordsFrequency.get("apple"); //返回1
 * wordsFrequency.get("pen"); //返回1
 * <p>
 * 提示：
 * book[i]中只包含小写字母
 * 1 <= book.length <= 100000
 * 1 <= book[i].length <= 10
 * get函数的调用次数不会超过100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/words-frequency-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution16_02 {
    /**
     * 执行用时： 140 ms , 在所有 Java 提交中击败了 62.84% 的用户
     * 内存消耗： 79.9 MB , 在所有 Java 提交中击败了 76.88% 的用户
     * 通过测试用例： 24 / 24
     */
    class WordsFrequency {
        private HashMap<String, Integer> map = new HashMap<>();

        public WordsFrequency(String[] book) {
            for (String word : book) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        public int get(String word) {
            return map.getOrDefault(word, 0);
        }
    }

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */
}
