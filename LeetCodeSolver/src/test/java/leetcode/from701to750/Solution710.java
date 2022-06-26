package leetcode.from701to750;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ZeromaXHe
 * @apiNote 710. 黑名单中的随机数 | 难度：困难 | 标签：哈希表、数学、二分查找、排序、随机化
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。
 * 设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。
 * 任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 * <p>
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * <p>
 * 实现 Solution 类:
 * <p>
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 * <p>
 * 示例 1：
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 * <p>
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 * // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 * <p>
 * 提示:
 * 1 <= n <= 109
 * 0 <= blacklist.length <= min(105, n - 1)
 * 0 <= blacklist[i] < n
 * blacklist 中所有值都 不同
 *  pick 最多被调用 2 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/random-pick-with-blacklist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/26 17:33
 */
public class Solution710 {
    /**
     * 执行用时：42 ms, 在所有 Java 提交中击败了 58.45% 的用户
     * 内存消耗：51.7 MB, 在所有 Java 提交中击败了 39.23% 的用户
     * 通过测试用例： 68 / 68
     */
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random();
        int white;

        public Solution(int n, int[] blacklist) {
            white = n - blacklist.length;
            Arrays.sort(blacklist);
            int index = Arrays.binarySearch(blacklist, white);
            if (index < 0) {
                index = -index - 1;
            }
            int blackIndex = index;
            int whiteNum = white;
            for (int i = 0; i < index; i++) {
                while (blackIndex < blacklist.length && blacklist[blackIndex] == whiteNum) {
                    blackIndex++;
                    whiteNum++;
                }
                map.put(blacklist[i], whiteNum);
                whiteNum++;
            }
        }

        public int pick() {
            int key = random.nextInt(white);
            return map.getOrDefault(key, key);
        }
    }
    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(n, blacklist);
     * int param_1 = obj.pick();
     */
}
