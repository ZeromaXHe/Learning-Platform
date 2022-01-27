package leetcode.from451to500;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/4 15:53
 * @Description: 482. 密钥格式化 | 难度：简单 | 标签：
 * 有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。
 * <p>
 * 给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。
 * 特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。
 * 两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
 * <p>
 * 给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
 * <p>
 * 示例 1：
 * 输入：S = "5F3Z-2e-9-w", K = 4
 * 输出："5F3Z-2E9W"
 * 解释：字符串 S 被分成了两个部分，每部分 4 个字符；
 *      注意，两个额外的破折号需要删掉。
 * <p>
 * 示例 2：
 * 输入：S = "2-5g-3-J", K = 2
 * 输出："2-5G-3J"
 * 解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
 * <p>
 * 提示:
 * S 的长度可能很长，请按需分配大小。K 为正整数。
 * S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
 * S 非空
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/license-key-formatting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution482 {
    @Test
    public void testLicenseKeyFormatting() {
        Assert.assertEquals("5F3Z-2E9W", licenseKeyFormatting("5F3Z-2e-9-w", 4));
        Assert.assertEquals("2-5G-3J", licenseKeyFormatting("2-5g-3-J", 2));
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 98.24% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 92.26% 的用户
     * 通过测试用例： 38 / 38
     *
     * @param s
     * @param k
     * @return
     */
    public String licenseKeyFormatting(String s, int k) {
        int countBar = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '-') {
                countBar++;
            }
        }
        StringBuilder sb = new StringBuilder();
        int validCharCount = s.length() - countBar;
        int rem = validCharCount % k;
        int count = 0;
        for (char c : chars) {
            if (c != '-') {
                if (c >= 'a' && c <= 'z') {
                    sb.append((char) (c + 'A' - 'a'));
                } else {
                    sb.append(c);
                }
                count++;
                if (count < validCharCount
                        && ((rem != 0 && (count - rem) % k == 0) || (rem == 0 && count != 0 && count % k == 0))) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }
}
