package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Objects;

/**
 * @Author: zhuxi
 * @Time: 2022/5/13 20:15
 * @Description: 面试题 10.05. 稀疏数组搜索 | 难度：简单 | 标签：数组、字符串、二分查找
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * <p>
 * 示例1:
 * 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明: 不存在返回-1。
 * <p>
 * 示例2:
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 * <p>
 * 提示:
 * words的长度在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sparse-array-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_05 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 90.25% 的用户
     * 通过测试用例： 28 / 28
     *
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        return binaryFindString(words, 0, words.length, s);
    }

    private int binaryFindString(String[] words, int from, int to, String s) {
        if (to <= from) {
            return -1;
        }
        if (to - from == 1) {
            return s.equals(words[from]) ? from : -1;
        }
        int index = (from + to) / 2;
        while (index < words.length && "".equals(words[index])) {
            index++;
        }
        if (index == words.length) {
            return binaryFindString(words, from, (from + to) / 2, s);
        }
        if (s.equals(words[index])) {
            return index;
        }
        if (words[index].compareTo(s) > 0) {
            return binaryFindString(words, from, (from + to) / 2, s);
        } else {
            return binaryFindString(words, index, to, s);
        }
    }
}
