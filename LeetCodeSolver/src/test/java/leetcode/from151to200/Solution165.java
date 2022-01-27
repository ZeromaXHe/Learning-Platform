package leetcode.from151to200;

/**
 * @Author: zhuxi
 * @Time: 2021/9/1 9:32
 * @Description: 165.比较版本号 | 难度：中等 | 标签：双指针、字符串
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 * <p>
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 * <p>
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 * <p>
 * 返回规则如下：
 * <p>
 * 如果 version1 > version2 返回 1，
 * 如果 version1 < version2 返回 -1，
 * 除此之外返回 0。
 *  
 * <p>
 * 示例 1：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 * <p>
 * 示例 2：
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
 * <p>
 * 示例 3：
 * 输入：version1 = "0.1", version2 = "1.1"
 * 输出：-1
 * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
 * <p>
 * 示例 4：
 * 输入：version1 = "1.0.1", version2 = "1"
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 仅包含数字和 '.'
 * version1 和 version2 都是 有效版本号
 * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution165 {
    public static void main(String[] args) {
        System.out.println(new Solution165().compareVersion("0.1", "1.1"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.56% 的用户
     * 内存消耗： 36.4 MB , 在所有 Java 提交中击败了 70.59% 的用户
     * 通过测试用例：81 / 81
     * <p>
     * 好久没做力扣题了，错了3次才过……
     * 第一次没加regex的斜杠（.作为任意字符匹配了），
     * 第二次复制代码贴错了地方，
     * 第三次是后面判断v2.length < v1.length复制的前面大于的代码，其他改了return没改
     * <p>
     * 还是得多练啊~
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.min(v1.length, v2.length);
        for (int i = 0; i < len; i++) {
            int i1 = Integer.parseInt(v1[i]);
            int i2 = Integer.parseInt(v2[i]);
            if (i1 > i2) {
                return 1;
            }
            if (i1 < i2) {
                return -1;
            }
        }
        if (v2.length == v1.length) {
            return 0;
        }
        if (v2.length > v1.length) {
            for (int i = v1.length; i < v2.length; i++) {
                if (Integer.parseInt(v2[i]) != 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            for (int i = v2.length; i < v1.length; i++) {
                if (Integer.parseInt(v1[i]) != 0) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
