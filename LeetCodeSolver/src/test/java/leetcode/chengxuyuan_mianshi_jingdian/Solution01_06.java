package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 11:14
 * @Description: 面试题 01.06. 字符串压缩 | 难度：简单 | 标签：双指针、字符串
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * <p>
 * 示例1:
 * 输入："aabcccccaaa"
 * 输出："a2b1c5a3"
 * <p>
 * 示例2:
 * 输入："abbccd"
 * 输出："abbccd"
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * <p>
 * 提示：
 * 字符串长度在[0, 50000]范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compress-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_06 {
    @Test
    public void compressStringTest() {
        Assert.assertEquals("bb", compressString("bb"));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 99.88% 的用户
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 75.66% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param S
     * @return
     */
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        char pre = '\n';
        int count = 0;
        for (char c : S.toCharArray()) {
            if (c != pre) {
                if (count != 0) {
                    sb.append(count);
                }
                sb.append(c);
                pre = c;
                count = 1;
            } else {
                count++;
            }
        }
        if (count != 0) {
            sb.append(count);
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
