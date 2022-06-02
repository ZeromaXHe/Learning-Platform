package leetcode.from401to450;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/6/2 14:16
 * @Description: 443. 压缩字符串 | 难度：中等 | 标签：双指针、字符串
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * <p>
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * 示例 2：
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 * <p>
 * 示例 3：
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 * <p>
 * 提示：
 * 1 <= chars.length <= 2000
 * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution443 {
    @Test
    public void compressTest() {
        Assert.assertEquals(4, compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
        Assert.assertEquals(3, compress(new char[]{'a', 'b', 'c'}));
        Assert.assertEquals(12, compress(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'a', 'b', 'c'}));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 90.27% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 6.64% 的用户
     * 通过测试用例： 72 / 72
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }
        int index = 1;
        int count = 1;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[index - 1]) {
                count++;
            } else {
                if (count > 1) {
                    while (count > 0) {
                        stack.push(count % 10);
                        count /= 10;
                    }
                    while (!stack.isEmpty()) {
                        chars[index++] = (char) ('0' + stack.poll());
                    }
                    chars[index++] = chars[i];
                    count = 1;
                } else {
                    chars[index++] = chars[i];
                }
            }
        }
        if (count > 1) {
            while (count > 0) {
                stack.push(count % 10);
                count /= 10;
            }
            while (!stack.isEmpty()) {
                chars[index++] = (char) ('0' + stack.poll());
            }
        }
        return index;
    }
}
