package leetcode.first100;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/1/14 10:59
 * @Description: 93. 复原IP地址 | 难度：中等 | 标签：字符串、回溯
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
 * 你不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * <p>
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * <p>
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * <p>
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * <p>
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 提示：
 * 0 <= s.length <= 20
 * s 仅由数字组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution93 {
    @Test
    public void restoreIpAddressesTest() {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("101023"));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 94.40% 的用户
     * 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 93.99% 的用户
     * 通过测试用例： 145 / 145
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        if (s.length() < 4) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        restoreIpAddresses(s, 0, sb, result, 4);
        return result;
    }

    private void restoreIpAddresses(String s, int sIndex, StringBuilder sb, List<String> result, int ipRemain) {
        // 最后一段ip
        if (ipRemain == 1) {
            if (validIpSector(s, sIndex, s.length())) {
                int sbLen = sb.length();
                for (int i = sIndex; i < s.length(); i++) {
                    sb.append(s.charAt(i));
                }
                result.add(sb.toString());
                sb.delete(sbLen, sb.length());
            }
            return;
        }
        // 剩余ip段数等于剩余字符数，直接用.分隔
        if (s.length() - sIndex == ipRemain) {
            int sbLen = sb.length();
            for (int i = sIndex; i < s.length(); i++) {
                sb.append(s.charAt(i));
                if (i != s.length() - 1) {
                    sb.append('.');
                }
            }
            result.add(sb.toString());
            sb.delete(sbLen, sb.length());
            return;
        }
        int sbLen = sb.length();
        for (int i = 1; i <= 3; i++) {
            if (!validIpSector(s, sIndex, sIndex + i)) {
                break;
            }
            sb.append(s.charAt(sIndex + i - 1));
            if (s.length() - sIndex - i <= 3 * ipRemain - 3) {
                sb.append('.');
                restoreIpAddresses(s, sIndex + i, sb, result, ipRemain - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        sb.delete(sbLen, sb.length());
    }

    private boolean validIpSector(String s, int from, int to) {
        if (to > s.length()) {
            return false;
        }
        if (to - from == 1 || (to - from == 2 && s.charAt(from) != '0')) {
            return true;
        }
        if (to - from == 3) {
            if (s.charAt(from) == '1') {
                return true;
            }
            if (s.charAt(from) == '2') {
                if (s.charAt(from + 1) < '5') {
                    return true;
                }
                if (s.charAt(from + 1) == '5') {
                    return s.charAt(from + 2) < '6';
                }
            }
        }
        return false;
    }
}
