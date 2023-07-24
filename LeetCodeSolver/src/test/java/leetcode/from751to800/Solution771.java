package leetcode.from751to800;

import java.util.HashSet;

/**
 * @author zhuxi
 * @apiNote 771. 宝石与石头 | 难度：简单 | 标签：哈希表、字符串
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。
 * stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * <p>
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 * <p>
 * 提示：
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/7/24 9:49
 */
public class Solution771 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 67.58% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了 33.33% 的用户
     * 通过测试用例：255 / 255
     *
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> set = new HashSet<>();
        for (char jewel : jewels.toCharArray()) {
            set.add(jewel);
        }
        int count = 0;
        for (char stone : stones.toCharArray()) {
            if (set.contains(stone)) {
                count++;
            }
        }
        return count;
    }
}
