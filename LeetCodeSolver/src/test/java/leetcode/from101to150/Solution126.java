package leetcode.from101to150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2022/1/19 19:36
 * @Description: 126. 单词接龙 II | 难度：困难 | 标签：广度优先搜索、哈希表、字符串、回溯
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * <p>
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * <p>
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * <p>
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * <p>
 * 提示：
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有单词 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution126 {
    @Test
    public void findLaddersTest() {
        System.out.println(findLadders("hit", "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(findLadders("qa", "sq",
                Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar",
                        "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or",
                        "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh",
                        "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la",
                        "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr",
                        "sq", "ye")));
    }

    /**
     * 执行用时： 56 ms , 在所有 Java 提交中击败了 5.09% 的用户
     * 内存消耗： 114.8 MB , 在所有 Java 提交中击败了 5.09% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * bfs是重复的新建List操作，所以效率很低。后续可以看看怎么优化
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();

        // 初始化单词到索引的映射
        Map<String, Integer> wordToIndexMap = new HashMap<>();
        int index = 0;
        for (String word : wordList) {
            wordToIndexMap.put(word, index++);
        }

        if (wordToIndexMap.get(endWord) == null) {
            return new LinkedList<>();
        }

        // 初始化转换表
        Map<Integer, List<Integer>> transMap = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String a = wordList.get(i);
            for (int j = i + 1; j < n; j++) {
                String b = wordList.get(j);
                if (onlyOneCharDifferent(a, b)) {
                    transMap.putIfAbsent(i, new LinkedList<>());
                    transMap.putIfAbsent(j, new LinkedList<>());
                    transMap.get(i).add(j);
                    transMap.get(j).add(i);
                }
            }
        }

        List<List<String>> result = new LinkedList<>();

        if (wordToIndexMap.get(beginWord) == null) {
            for (String s : wordList) {
                if (onlyOneCharDifferent(s, beginWord)) {
                    bfs(result, transMap, wordToIndexMap, s, endWord, wordList, beginWord);
                }
            }
        } else {
            bfs(result, transMap, wordToIndexMap, beginWord, endWord, wordList, null);
        }
        return result;
    }

    private void bfs(List<List<String>> result, Map<Integer, List<Integer>> transMap,
                     Map<String, Integer> wordToIndexMap, String beginWord, String endWord, List<String> wordList,
                     String notInListBegin) {
        int n = wordList.size();

        Map<String, List<LinkedList<String>>> beginTest = new HashMap<>();
        Map<String, List<LinkedList<String>>> endTest = new HashMap<>();

        LinkedList<LinkedList<String>> listList = new LinkedList<>();
        LinkedList<String> list = new LinkedList<>();
        if (notInListBegin != null) {
            list.addLast(notInListBegin);
        }
        list.addLast(beginWord);
        listList.add(list);
        beginTest.put(beginWord, listList);

        LinkedList<LinkedList<String>> listList2 = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        list2.add(endWord);
        listList2.add(list2);
        endTest.put(endWord, listList2);

        Map<String, List<LinkedList<String>>> beginTestNew = new HashMap<>();
        Map<String, List<LinkedList<String>>> endTestNew = new HashMap<>();

        while (!beginTest.isEmpty() && !endTest.isEmpty() && result.isEmpty()) {
            for (Map.Entry<String, List<LinkedList<String>>> begin : beginTest.entrySet()) {
                int beginIndex = wordToIndexMap.get(begin.getKey());
                List<Integer> nexts = transMap.get(beginIndex);
                if (nexts == null) {
                    continue;
                }
                for (int nextIndex : nexts) {
                    String nextStr = wordList.get(nextIndex);
                    if (endTest.containsKey(nextStr)) {
                        addPathToResult(result, begin.getValue(), endTest.get(nextStr));
                    } else {
                        addToTestNew(beginTestNew, true, nextStr, begin.getValue());
                    }
                }
            }

            // 将新的测试集交换
            Map<String, List<LinkedList<String>>> temp = beginTest;
            beginTest = beginTestNew;
            beginTestNew = temp;
            beginTestNew.clear();

            if (!result.isEmpty()) {
                break;
            }

            for (Map.Entry<String, List<LinkedList<String>>> end : endTest.entrySet()) {
                int endIndex = wordToIndexMap.get(end.getKey());
                List<Integer> nexts = transMap.get(endIndex);
                if (nexts == null) {
                    continue;
                }
                for (int nextIndex : nexts) {
                    String nextStr = wordList.get(nextIndex);
                    if (beginTest.containsKey(nextStr)) {
                        addPathToResult(result, beginTest.get(nextStr), end.getValue());
                    } else {
                        addToTestNew(endTestNew, false, nextStr, end.getValue());
                    }
                }
            }

            // 将新的测试集交换
            temp = endTest;
            endTest = endTestNew;
            endTestNew = temp;
            endTestNew.clear();
        }
    }

    private void addToTestNew(Map<String, List<LinkedList<String>>> testNew, boolean begin, String nextStr, List<LinkedList<String>> lists) {
        testNew.putIfAbsent(nextStr, new LinkedList<>());
        for (LinkedList<String> list : lists) {
            if (list.contains(nextStr)) {
                continue;
            }
            LinkedList<String> newList = new LinkedList<>(list);
            if (begin) {
                newList.addLast(nextStr);
            } else {
                newList.addFirst(nextStr);
            }
            testNew.get(nextStr).add(newList);
        }
    }

    private void addPathToResult(List<List<String>> result, List<LinkedList<String>> begins, List<LinkedList<String>> ends) {
        for (LinkedList<String> beginList : begins) {
            for (LinkedList<String> endList : ends) {
                List<String> list = new ArrayList<>(beginList.size() + endList.size());
                list.addAll(beginList);
                list.addAll(endList);
                result.add(list);
            }
        }
    }

    private boolean onlyOneCharDifferent(String a, String b) {
        boolean diff = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (diff) {
                    return false;
                } else {
                    diff = true;
                }
            }
        }
        return diff;
    }
}
