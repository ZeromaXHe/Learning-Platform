package leetcode.from901to950;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 10:07
 * @Description: 944. 删列造序 | 难度：简单 | 标签：数组、字符串
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * <p>
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：
 * <p>
 * abc
 * bce
 * cae
 * 你需要找出并删除 不是按字典序升序排列的 列。在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按升序排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * <p>
 * 返回你需要删除的列数。
 * <p>
 * 示例 1：
 * 输入：strs = ["cba","daf","ghi"]
 * 输出：1
 * 解释：网格示意如下：
 * cba
 * daf
 * ghi
 * 列 0 和列 2 按升序排列，但列 1 不是，所以只需要删除列 1 。
 * <p>
 * 示例 2：
 * 输入：strs = ["a","b"]
 * 输出：0
 * 解释：网格示意如下：
 * a
 * b
 * 只有列 0 这一列，且已经按升序排列，所以不用删除任何列。
 * <p>
 * 示例 3：
 * 输入：strs = ["zyx","wvu","tsr"]
 * 输出：3
 * 解释：网格示意如下：
 * zyx
 * wvu
 * tsr
 * 所有 3 列都是非升序排列的，所以都要删除。
 * <p>
 * 提示：
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 1000
 * strs[i] 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-columns-to-make-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution944 {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 95.71% 的用户
     * 内存消耗： 41.6 MB , 在所有 Java 提交中击败了 37.14% 的用户
     * 通过测试用例： 85 / 85
     *
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int count = 0;
        int sLen = strs[0].length();
        for (int i = 0; i < sLen; i++) {
            char pre = 'a';
            for (int j = 0; j < strs.length; j++) {
                char c = strs[j].charAt(i);
                if (c < pre) {
                    count++;
                    break;
                }
                pre = c;
            }
        }
        return count;
    }
}
