package leetcode.from501to600;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/31 14:53
 * @Description: 500. 键盘行 | 难度：简单 | 标签：数组、哈希表、字符串
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * <p>
 * 美式键盘 中：
 * <p>
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * <p>
 * 示例 1：
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * <p>
 * 示例 2：
 * 输入：words = ["omk"]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 * <p>
 * 提示：
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keyboard-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution500 {
    private static HashMap<Character, Integer> map = new HashMap<>();

    static {
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);
        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);
        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 51.88% 的用户
     * 通过测试用例： 22 / 22
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        List<String> result = new LinkedList<>();
        loop:
        for (String word : words) {
            String wordLow = word.toLowerCase();
            int line = map.get(wordLow.charAt(0));
            for (int i = 1; i < wordLow.length(); i++) {
                if (line != map.get(wordLow.charAt(i))) {
                    continue loop;
                }
            }
            result.add(word);
        }
        return result.toArray(new String[result.size()]);
    }
}
