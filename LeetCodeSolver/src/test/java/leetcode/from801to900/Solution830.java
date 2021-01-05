package leetcode.from801to900;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/5 9:46
 * @Description: 830.较大分组的位置 | 难度：简单 | 标签：数组
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * <p>
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * <p>
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * <p>
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * <p>
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * <p>
 * 示例 1：
 * 输入：s = "abbxxxxzzy"
 * 输出：[[3,6]]
 * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 * <p>
 * 示例 2：
 * 输入：s = "abc"
 * 输出：[]
 * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
 * <p>
 * 示例 3：
 * 输入：s = "abcdddeeeeaabbbcd"
 * 输出：[[3,5],[6,9],[12,14]]
 * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
 * <p>
 * 示例 4：
 * 输入：s = "aba"
 * 输出：[]
 *  
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/positions-of-large-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution830 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 74.02% 的用户
     *
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            int start = i;
            while (i < s.length() - 1 && s.charAt(i + 1) == s.charAt(i)) {
                i++;
            }
            if (i - start > 1) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(start);
                arrayList.add(i);
                result.add(arrayList);
            }
        }
        /// 无需排序，正常情况就是起始索引递增
        // result.sort(Comparator.comparingInt(o->o.get(1)));
        return result;
    }
}
