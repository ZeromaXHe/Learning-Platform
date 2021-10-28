package leetcode.from801to900;

import org.junit.Test;

import java.util.HashSet;

/**
 * @Author: zhuxi
 * @Time: 2021/10/28 9:51
 * @Description: 869. 重新排序得到 2 的幂 | 难度：中等 | 标签：数学、计数、枚举、排序
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * <p>
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * <p>
 * 示例 1：
 * 输入：1
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：10
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：16
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：24
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：46
 * 输出：true
 * <p>
 * 提示：
 * 1 <= N <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution869 {
    @Test
    public void formPower2Set() {
        HashSet<String> set = new HashSet<>();
        int i = 1;
        while (i < 1_000_000_000) {
            set.add(numToDigitCountString(i));
            i <<= 1;
        }
        System.out.println(set);
    }

    private static HashSet<String> power2StringSet = new HashSet<>();

    static {
        power2StringSet.add("(0)1(1)1(4)1(5)1(6)1(7)1(8)1");
        power2StringSet.add("(2)1(5)1(6)1");
        power2StringSet.add("(2)1(3)1(6)1(7)1(8)1");
        power2StringSet.add("(1)2(2)2(3)1(4)1(7)2(8)1");
        power2StringSet.add("(4)1(6)1");
        power2StringSet.add("(0)1(4)1(6)1(9)1");
        power2StringSet.add("(2)1(3)1(4)2(5)2(6)2(8)1");
        power2StringSet.add("(2)1(3)1");
        power2StringSet.add("(0)1(1)1(4)1(6)2(7)1(8)2");
        power2StringSet.add("(0)1(1)1(2)1(4)1");
        power2StringSet.add("(0)1(2)1(4)1(8)1");
        power2StringSet.add("(1)1(6)1");
        power2StringSet.add("(1)1(2)1(8)1");
        power2StringSet.add("(1)1(2)1(5)1");
        power2StringSet.add("(2)1");
        power2StringSet.add("(1)1(2)1(8)1(9)1");
        power2StringSet.add("(2)1(3)3(4)2(5)2");
        power2StringSet.add("(1)1(2)2(4)2(6)1");
        power2StringSet.add("(1)1");
        power2StringSet.add("(0)1(3)1(6)1(8)4");
        power2StringSet.add("(4)1");
        power2StringSet.add("(0)1(1)1(2)2(5)1(7)1(9)1");
        power2StringSet.add("(1)1(3)1(4)1(6)1(8)1");
        power2StringSet.add("(0)1(1)1(2)1(3)1(5)1(6)1(7)1(8)1(9)1");
        power2StringSet.add("(8)1");
        power2StringSet.add("(0)1(1)2(2)1(3)1(7)1");
        power2StringSet.add("(1)2(2)1(6)2(7)3");
        power2StringSet.add("(3)1(5)2(6)2");
        power2StringSet.add("(0)1(1)1(3)1(4)3(9)1");
        power2StringSet.add("(2)2(4)1(5)1(8)2");
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.93% 的用户
     * 内存消耗： 35.2 MB , 在所有 Java 提交中击败了 76.35% 的用户
     * 通过测试用例： 136 / 136
     *
     * @param n
     * @return
     */
    public boolean reorderedPowerOf2(int n) {
        return power2StringSet.contains(numToDigitCountString(n));
    }

    private String numToDigitCountString(int num) {
        int[] count = new int[10];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (count[i] > 0) {
                sb.append('(').append(i).append(')').append(count[i]);
            }
        }
        return sb.toString();
    }
}
