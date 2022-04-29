package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 10:31
 * @Description: 面试题 01.04. 回文排列 | 难度：简单 | 标签：位运算、哈希表、字符串
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * <p>
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * <p>
 * 回文串不一定是字典当中的单词。
 * <p>
 * 示例1：
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_04 {
    @Test
    public void canPermutePalindromeTest() {
        Assert.assertFalse(canPermutePalindrome("AaBb//a"));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 46.97% 的用户
     * 通过测试用例： 27 / 27
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 17.97% 的用户
     * 通过测试用例： 27 / 27
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome_BitSet(String s) {
        BitSet set = new BitSet(128);
        for (char c : s.toCharArray()) {
            set.flip(c);
        }
        return set.cardinality() <= 1;
    }

    public boolean canPermutePalindrome_failedTry_lowerChar(String s) {
        int bitMap = 0;
        for (char c : s.toCharArray()) {
            bitMap ^= 1 << (c - 'a');
        }
        if (bitMap == 0) {
            return true;
        }
        while ((bitMap & 1) == 0) {
            bitMap >>= 1;
        }
        bitMap >>= 1;
        return bitMap == 0;
    }
}
