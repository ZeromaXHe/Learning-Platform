package leetcode.jianzhi_offer;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/21 13:53
 * @Description: 剑指 Offer 05. 替换空格 | 难度：简单 | 标签：无
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class SolutionOffer05 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.1 MB , 在所有 Java 提交中击败了 91.27% 的用户
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(c==' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 13.43% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 76.70% 的用户
     * 估计生成相应regex匹配的过程比较耗时
     *
     * @param s
     * @return
     */
    public String replaceSpace_replaceAll(String s) {
        return s.replaceAll(" ","%20");
    }
}
