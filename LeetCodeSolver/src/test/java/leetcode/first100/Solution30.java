package leetcode.first100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/4 16:12
 * @Description: 30.串联所有单词的子串 | 难度：困难 | 标签：哈希表、双指针、字符串
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 示例 2：
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution30 {
    /**
     * 执行用时： 9 ms , 在所有 Java 提交中击败了 92.17% 的用户
     * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 65.23% 的用户
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) {
            return new LinkedList<>();
        }
        int len = words[0].length();
        int totalLen = len * words.length;
        if (totalLen > s.length()) {
            return new LinkedList<>();
        }
        // 单词转换为对应的int值的映射
        HashMap<String, Integer> wordsMap = new HashMap<>();
        // int值索引对应存的就是单词的个数
        ArrayList<Integer> wordCount = new ArrayList<>(words.length);
        int count = 0;
        // 处理words数组，将不同单词转换为对应的int值
        // 注意存在重复单词的情况
        for (String str : words) {
            if (wordsMap.containsKey(str)) {
                int index = wordsMap.get(str);
                wordCount.set(index, wordCount.get(index) + 1);
            } else {
                wordsMap.put(str, count++);
                wordCount.add(1);
            }
        }
        int[] subWord = new int[s.length() - len + 1];
        // 将字符串解析成含有索引-单词对的信息的数组
        for (int i = 0; i < subWord.length; i++) {
            String test = s.substring(i, i + len);
            if (wordsMap.containsKey(test)) {
                subWord[i] = wordsMap.get(test);
            } else {
                subWord[i] = -1;
            }
        }
        return checkSubWord(subWord, words.length, len, wordCount);
    }

    /**
     * 根据subWord数组求出结果队列
     *
     * @param subWord subWord数组
     * @param count   words数组的单词个数
     * @param len     words中每个单词的长度
     * @return
     */
    private List<Integer> checkSubWord(int[] subWord, int count, int len, ArrayList<Integer> wordCount) {
        LinkedList<Integer> result = new LinkedList<>();
        // 判断单词是否存在过的数组
        int[] wordCountTemp = new int[count];
        for (int i = 0; i < len; i++) {
            // 按单词长度分组，每组以len的间隔进行跳跃判断
            // 方便处理超过wordCount的情况，可以回退以利用之前的信息。
            Arrays.fill(wordCountTemp, 0);
            loop:
            for (int j = i; j < subWord.length - count * len + len; j += len) {
                for (int k = 0; k < count; k++) {
                    int wordIndex = subWord[j + k * len];
                    if (wordIndex == -1) {
                        j += k * len;
                        Arrays.fill(wordCountTemp, 0);
                        continue loop;
                    } else {
                        if (wordCountTemp[wordIndex] >= wordCount.get(wordIndex)) {
                            // 实际上不可能大于，只会等于
                            while (subWord[j] != wordIndex) {
                                // 回退的过程，寻找有效的状态（每个单词都小于wordCount）
                                wordCountTemp[subWord[j]]--;
                                j += len;
                                if (j >= subWord.length - count * len + len) {
                                    break loop;
                                }
                                k--;
                            }
                            j += len;
                            if (j >= subWord.length - count * len + len) {
                                break loop;
                            }
                            k--;
                            // 回退成功，进入下一个循环
                            continue;
                        }
                        wordCountTemp[wordIndex]++;
                    }
                }
                result.add(j);
                Arrays.fill(wordCountTemp, 0);
            }
        }
        return result;
    }

    @Test
    public void findSubstringTest() {
        // [0,9]
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        // []
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        // [0]
        System.out.println(findSubstring("wordgoodbestword", new String[]{"word", "good", "best", "word"}));
        // [8]
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
        // [6,16,17,18,19,20]
        System.out.println(findSubstring("bcabbcaabbccacacbabccacaababcbb", new String[]{"c", "b", "a", "c", "a", "a", "a", "b", "c"}));
    }
}
