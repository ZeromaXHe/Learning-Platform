package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 10:49
 * @Description: 面试题 01.05. 一次编辑 | 难度：中等 | 标签：双指针、字符串
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 * <p>
 * 示例 2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/one-away-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_05 {
    @Test
    public void oneEditAwayTest() {
        Assert.assertTrue(oneEditAway("", "a"));
        Assert.assertTrue(oneEditAway("a", "ab"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.69% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 37.12% 的用户
     * 通过测试用例： 1146 / 1146
     * <p>
     * 不使用这种模拟的思路的话，可以直接调用力扣 72 题：编辑距离的代码（动态规划），根据返回的编辑距离是否小于等于 1 来判断
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int lenDiff = Math.abs(first.length() - second.length());
        if (lenDiff > 1) {
            return false;
        }
        if (lenDiff == 0) {
            boolean diff = false;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (diff) {
                        return false;
                    } else {
                        diff = true;
                    }
                }
            }
            return true;
        }
        if (second.length() > first.length()) {
            String temp = first;
            first = second;
            second = temp;
        }
        if (first.length() == 1) {
            return true;
        }
        int diff = 0;
        for (int i = 0; i < first.length(); i++) {
            if (i == first.length() - 1 && diff == 0) {
                return true;
            }
            if (first.charAt(i) != second.charAt(i - diff)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}
