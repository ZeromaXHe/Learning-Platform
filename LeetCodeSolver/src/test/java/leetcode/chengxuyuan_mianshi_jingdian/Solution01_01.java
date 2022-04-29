package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/4/29 9:59
 * @Description: 面试题 01.01. 判定字符是否唯一 | 难度：简单 | 标签：位运算、哈希表、字符串、排序
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 * <p>
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 * <p>
 * 限制：
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-unique-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution01_01 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 28.26% 的用户
     * 通过测试用例： 17 / 17
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        int bitMap = 0;
        for (char c : astr.toCharArray()) {
            if (((bitMap >> (c - 'a')) & 1) == 1) {
                return false;
            }
            bitMap += 1 << (c - 'a');
        }
        return true;
    }
}
