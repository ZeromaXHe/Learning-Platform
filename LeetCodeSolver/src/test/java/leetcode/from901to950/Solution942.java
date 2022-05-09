package leetcode.from901to950;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/5/9 11:43
 * @Description: 942. 增减字符串匹配 | 难度：简单 | 标签：贪心、数组、数学、双指针、字符串
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 * <p>
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 * <p>
 * 示例 1：
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 * <p>
 * 示例 2：
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 * <p>
 * 示例 3：
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s 只包含字符 "I" 或 "D"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/di-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution942 {
    @Test
    public void diStringMatchTest() {
        Assert.assertArrayEquals(new int[]{0, 4, 1, 3, 2}, diStringMatch("IDID"));
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, diStringMatch("III"));
        Assert.assertArrayEquals(new int[]{3, 2, 0, 1}, diStringMatch("DDI"));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 87.44% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 96.02% 的用户
     * 通过测试用例： 95 / 95
     *
     * @param s
     * @return
     */
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] result = new int[n + 1];
        int max = n;
        int min = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                result[i] = min++;
            } else {
                result[i] = max--;
            }
        }
        result[n] = max;
        return result;
    }
}
