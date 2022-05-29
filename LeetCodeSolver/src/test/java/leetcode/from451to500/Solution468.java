package leetcode.from451to500;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @Author: ZeromaXHe
 * @Time: 2022/5/29 17:21
 * @Description: 468. 验证IP地址 | 难度：中等 | 标签：字符串
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 * <p>
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 * 例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 * <p>
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * <p>
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，
 * 而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 * <p>
 * 示例 1：
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * <p>
 * 示例 2：
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * <p>
 * 示例 3：
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * <p>
 * 提示：
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution468 {
    private final static String IPV4_NODE_REGEX = "(0|1\\d{0,2}|2[0-4]\\d|25[0-5]|2\\d?|[3-9]\\d?)";
    private final static Pattern IPV4_MATCHER = Pattern.compile("(" + IPV4_NODE_REGEX + "\\.){3}" + IPV4_NODE_REGEX);
    private final static String IPV6_NODE_REGEX = "(([a-fA-F1-9][a-fA-F0-9]{0,3})|(0[a-fA-F1-9][a-fA-F0-9]{0,2})|0{1,4})";
    private final static Pattern IPV6_MATCHER = Pattern.compile("(" + IPV6_NODE_REGEX + ":){7}" + IPV6_NODE_REGEX);

    @Test
    public void validIPAddressTest() {
        Assert.assertEquals("IPv4", validIPAddress("172.16.254.1"));
        Assert.assertEquals("IPv4", validIPAddress("192.0.0.1"));
        Assert.assertEquals("IPv6", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        Assert.assertEquals("IPv6", validIPAddress("2001:0db8:85a3:033:0:8A2E:0370:7334"));
        Assert.assertEquals("IPv6", validIPAddress("2001:0db8:85a3:0000:0:8A2E:0370:733a"));
        Assert.assertEquals("IPv6", validIPAddress("2001:0db8:85a3:000:0:8A2E:0370:7334"));
        Assert.assertEquals("Neither", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        Assert.assertEquals("Neither", validIPAddress("01.01.01.01"));
        Assert.assertEquals("Neither", validIPAddress("256.256.256.256"));
        Assert.assertEquals("Neither", validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334"));
    }

    @Test
    public void regexPatternTest() {
        Pattern pattern = Pattern.compile(IPV6_NODE_REGEX);
        Assert.assertTrue(pattern.matcher("2001").matches());
        Assert.assertTrue(pattern.matcher("0db8").matches());
        Assert.assertTrue(pattern.matcher("85a3").matches());
        Assert.assertTrue(pattern.matcher("0").matches());
        Assert.assertTrue(pattern.matcher("000").matches());
        Assert.assertTrue(pattern.matcher("0000").matches());
        Assert.assertTrue(pattern.matcher("8A2E").matches());
        Assert.assertTrue(pattern.matcher("0370").matches());
        Assert.assertTrue(pattern.matcher("7334").matches());
        Pattern pattern2 = Pattern.compile(IPV4_NODE_REGEX);
        Assert.assertTrue(pattern2.matcher("192").matches());
        Assert.assertTrue(pattern2.matcher("0").matches());
        Assert.assertTrue(pattern2.matcher("1").matches());
    }

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 14.09% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 66.20% 的用户
     * 通过测试用例： 73 / 73
     * <p>
     * 需要在力扣 class 前面 import java.util.regex.Pattern;
     * 实在不想写 if-else 版本的了，傻逼题目。
     * 测试用例全靠撞，题目里面都不说的，ipv6 的 0、000、0000，还有允许大小写混用。都是些傻逼用例！
     *
     * @param queryIP
     * @return
     */
    public String validIPAddress(String queryIP) {
        if (IPV4_MATCHER.matcher(queryIP).matches()) {
            return "IPv4";
        }
        return IPV6_MATCHER.matcher(queryIP).matches() ? "IPv6" : "Neither";
    }

    /**
     * 不想调这些狗日的测试用例了，烦的一比
     * （这个方法里的实现没写完）
     *
     * @param queryIP
     * @return
     */
    public String validIPAddress_fuckThisShittyQuestion(String queryIP) {
        String[] split = queryIP.split("\\.");
        boolean ipv4 = true;
        if (split.length == 4 && queryIP.charAt(0) != '.' && queryIP.charAt(queryIP.length() - 1) != '.') {
            loop:
            for (String s : split) {
                if (s.length() > 3 || s.length() == 0) {
                    ipv4 = false;
                    break;
                }
                if (s.charAt(0) == '0') {
                    if (s.length() > 1) {
                        ipv4 = false;
                        break;
                    } else {
                        continue;
                    }
                }
                int sum = 0;
                for (int i = 0; i < s.length(); i++) {
                    sum *= 10;
                    if (Character.isDigit(s.charAt(i))) {
                        sum += s.charAt(i) - '0';
                    } else {
                        ipv4 = false;
                        break loop;
                    }
                }
                if (sum > 255) {
                    ipv4 = false;
                    break;
                }
            }
        } else {
            ipv4 = false;
        }
        if (ipv4) {
            return "IPv4";
        }

        String[] split2 = queryIP.split(":");
        boolean ipv6 = true;
        if (split2.length == 8 && queryIP.charAt(0) != ':' && queryIP.charAt(queryIP.length() - 1) != ':') {
            for (int i = 0; i < 8; i++) {
                if (split2[i].length() != 4 && (split2[i].length() != 1 || !"0".equals(split2[i]))) {
                    ipv6 = false;
                    break;
                }
            }
        } else {
            ipv6 = false;
        }

        return ipv6 ? "IPv6" : "Neither";
    }
}
