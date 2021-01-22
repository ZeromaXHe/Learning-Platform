package leetcode.from101to200;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/22 11:38
 * @Description: 127.单词接龙 | 难度：困难 | 标签：广度优先搜索
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的最短转换序列中的单词数目。如果不存在这样的转换序列，返回 0。
 * <p>
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * <p>
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution127 {
    /**
     * 执行用时： 588 ms , 在所有 Java 提交中击败了 26.94% 的用户
     * 内存消耗： 73.4 MB , 在所有 Java 提交中击败了 4.99% 的用户
     * 慢，待优化
     * 可以把图从二维数组改为Map<Integer,List<Integer>>,还可以使用双向广度搜索进行优化
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean[][] graph = initGraph(wordList);
        int[] indexes = getBeginAndEndWordIndexes(beginWord, endWord, wordList);
        return ladderLength(graph, indexes, wordList, beginWord);
    }

    private int ladderLength(boolean[][] graph, int[] indexes, List<String> wordList, String beginWord) {
        if (indexes[1] == -1) {
            return 0;
        }
        boolean[] visit = new boolean[graph.length];
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int result = 1;
        if (indexes[0] != -1) {
            visit[indexes[0]] = true;
            queue.offer(indexes[0]);
            count++;
        } else {
            result++;
            int num = 0;
            for (String s : wordList) {
                if (oneCharDiff(beginWord, s)) {
                    if (num == indexes[1]) {
                        return result;
                    }
                    visit[num] = true;
                    queue.offer(num);
                    count++;
                }
                num++;
            }
        }
        while (count > 0) {
            result++;
            while (count > 0) {
                int now = queue.poll();
                count--;
                for (int i = 0; i < graph.length; i++) {
                    if (graph[now][i] && !visit[i]) {
                        if (i == indexes[1]) {
                            return result;
                        }
                        queue.offer(i);
                        visit[i] = true;
                    }
                }
            }
            count = queue.size();
        }
        return 0;
    }

    private int[] getBeginAndEndWordIndexes(String beginWord, String endWord, List<String> wordList) {
        int[] indexes = new int[2];
        indexes[0] = indexes[1] = -1;
        int index = 0;
        Iterator<String> iterator = wordList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals(beginWord)) {
                indexes[0] = index;
            } else if (s.equals(endWord)) {
                indexes[1] = index;
            }
            index++;
        }
        return indexes;
    }

    private boolean[][] initGraph(List<String> wordList) {
        int n = wordList.size();
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (oneCharDiff(wordList.get(i), wordList.get(j))) {
                    graph[i][j] = graph[j][i] = true;
                }
            }
        }
        return graph;
    }

    private boolean oneCharDiff(String s1, String s2) {
        boolean oneDiff = false;
        for (int i = 0; i < s1.length(); i++) {
            if (oneDiff) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
            } else {
                if (s1.charAt(i) != s2.charAt(i)) {
                    oneDiff = true;
                }
            }
        }
        return oneDiff;
    }

    @Test
    public void ladderLengthTest() {
        Assert.assertEquals(5, ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog")));
        Assert.assertEquals(0, ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log")));
        Assert.assertEquals(2, ladderLength("hog", "cog", Lists.newArrayList("cog")));
    }
}
