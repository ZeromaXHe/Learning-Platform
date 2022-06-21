package leetcode.from1101to1150;

/**
 * @author zhuxi
 * @apiNote 1108. IP 地址无效化 | 难度：简单 | 标签：字符串
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 * <p>
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 * 示例 2：
 * <p>
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 *  
 * <p>
 * 提示：
 * <p>
 * 给出的 address 是一个有效的 IPv4 地址
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/defanging-an-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/21 9:48
 */
public class Solution1108 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了 66.84% 的用户
     * 通过测试用例： 62 / 62
     *
     * @param address
     * @return
     */
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == '.') {
                sb.append("[.]");
            } else {
                sb.append(address.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 16.89% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了 5.21% 的用户
     * 通过测试用例： 62 / 62
     *
     * @param address
     * @return
     */
    public String defangIPaddr_replaceAll(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了 5.21% 的用户
     * 通过测试用例： 62 / 62
     * <p>
     * 看自己 2020 年 3 月的代码，感觉有点不可置信。
     * 一方面是发现，replace 居然就是替换所有的匹配字符串，而且第一个变量还是按字面量去替换的，不是正则表达式。
     * 而另一方面是 replace 居然明显比 replaceAll 快。估计是正则的解析过程比较慢？
     *
     * @param address
     * @return
     */
    public String defangIPaddr_replace(String address) {
        return address.replace(".", "[.]");
    }
}
