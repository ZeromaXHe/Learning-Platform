package leetcode.from1401to1450;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/12/1 10:30
 * @Description: 1446. 连续字符 | 难度：简单 | 标签：字符串
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串的能量。
 * <p>
 * 示例 1：
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * <p>
 * 示例 2：
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * <p>
 * 示例 3：
 * 输入：s = "triplepillooooow"
 * 输出：5
 * <p>
 * 示例 4：
 * 输入：s = "hooraaaaaaaaaaay"
 * 输出：11
 * <p>
 * 示例 5：
 * 输入：s = "tourist"
 * 输出：1
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/consecutive-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1446 {
    @Test
    public void maxPowerTest() {
        Assert.assertEquals(2, maxPower("leetcode"));
        Assert.assertEquals(5, maxPower("abbcccddddeeeeedcba"));
        Assert.assertEquals(5, maxPower("triplepillooooow"));
        Assert.assertEquals(11, maxPower("hooraaaaaaaaaaay"));
        Assert.assertEquals(1, maxPower("tourist"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.8 MB , 在所有 Java 提交中击败了 94.52% 的用户
     * 通过测试用例： 333 / 333
     *
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int max = 1;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return Math.max(max, count);
    }
}
