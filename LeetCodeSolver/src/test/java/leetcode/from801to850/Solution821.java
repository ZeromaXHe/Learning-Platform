package leetcode.from801to850;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/4/19 10:04
 * @Description: 821. 字符的最短距离 | 难度：简单 | 标签：数组、双指针、字符串
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * <p>
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * <p>
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 * <p>
 * 示例 1：
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 * <p>
 * 示例 2：
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s[i] 和 c 均为小写英文字母
 * 题目数据保证 c 在 s 中至少出现一次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-distance-to-a-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution821 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 40.46% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 56.34% 的用户
     * 通过测试用例： 76 / 76
     * <p>
     * 不使用 Arrays.fill, 而是将 lastIndex 初始化为 -s.length()，估计可以节省些时间
     *
     * @param s
     * @param c
     * @return
     */
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        Arrays.fill(result, Integer.MAX_VALUE);
        int lastIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                lastIndex = i;
                result[i] = 0;
            } else if (lastIndex != -1) {
                result[i] = i - lastIndex;
            }
        }
        lastIndex = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                lastIndex = i;
            } else if (lastIndex != -1) {
                result[i] = Math.min(lastIndex - i, result[i]);
            }
        }
        return result;
    }
}
