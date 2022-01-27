package leetcode.from401to450;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/11/24 9:53
 * @Description: 423. 从英文中重建数字 | 难度：中等 | 标签：哈希表、数字、字符串
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 * <p>
 * 示例 1：
 * 输入：s = "owoztneoer"
 * 输出："012"
 * <p>
 * 示例 2：
 * 输入：s = "fviefuro"
 * 输出："45"
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution423 {
    @Test
    public void originalDigitsTest() {
        Assert.assertEquals("012", originalDigits("owoztneoer"));
        Assert.assertEquals("45", originalDigits("fviefuro"));
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 43.50% 的用户
     * 内存消耗： 39 MB , 在所有 Java 提交中击败了 50.28% 的用户
     * 通过测试用例： 24 / 24
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        // one      o - w - u - z
        // two      w
        // three    r - u - z(h - g)
        // four     u
        // five     f - u
        // six      x
        // seven    v - f + u
        // eight    g
        // nine     i - f + u - x - g
        // zero     z
        int countO = 0, countW = 0, countU = 0, countR = 0, countF = 0,
                countX = 0, countV = 0, countG = 0, countI = 0, countZ = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'o':
                    countO++;
                    break;
                case 'w':
                    countW++;
                    break;
                case 'u':
                    countU++;
                    break;
                case 'r':
                    countR++;
                    break;
                case 'f':
                    countF++;
                    break;
                case 'x':
                    countX++;
                    break;
                case 'v':
                    countV++;
                    break;
                case 'g':
                    countG++;
                    break;
                case 'i':
                    countI++;
                    break;
                case 'z':
                    countZ++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countZ; i++) {
            sb.append('0');
        }
        for (int i = 0; i < countO - countW - countU - countZ; i++) {
            sb.append('1');
        }
        for (int i = 0; i < countW; i++) {
            sb.append('2');
        }
        for (int i = 0; i < countR - countU - countZ; i++) {
            sb.append('3');
        }
        for (int i = 0; i < countU; i++) {
            sb.append('4');
        }
        for (int i = 0; i < countF - countU; i++) {
            sb.append('5');
        }
        for (int i = 0; i < countX; i++) {
            sb.append('6');
        }
        for (int i = 0; i < countV - countF + countU; i++) {
            sb.append('7');
        }
        for (int i = 0; i < countG; i++) {
            sb.append('8');
        }
        for (int i = 0; i < countI - countF + countU - countX - countG; i++) {
            sb.append('9');
        }
        return sb.toString();
    }
}
