package leetcode.from151to200;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2021/10/8 9:56
 * @Description: 187. 重复的DNA序列 | 难度：中等 | 标签：位运算、哈希表、字符串、滑动窗口、哈希函数、滚动哈希
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * <p>
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * <p>
 * 提示：
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution187 {
    /**
     * 执行用时： 17 ms , 在所有 Java 提交中击败了 63.93% 的用户
     * 内存消耗： 47.2 MB , 在所有 Java 提交中击败了 17.08% 的用户
     * 通过测试用例： 31 / 31
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() <= 10) {
            return result;
        }
        Set<String> existSet = new HashSet<>();
        Set<String> repeatSet = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            if (existSet.contains(substring)) {
                repeatSet.add(substring);
            }
            existSet.add(substring);
        }
        result.addAll(repeatSet);
        return result;
    }
}
