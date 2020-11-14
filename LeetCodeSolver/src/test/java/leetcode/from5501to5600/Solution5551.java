package leetcode.from5501to5600;

/**
 * @author ZeromaXHe
 * @date 2020/11/14 23:00
 * @Description 5551.使字符串平衡的最少删除次数 | 难度：中等 | 标签：贪心算法、字符串
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。我们称 s 平衡的 当不存在下标对 (i,j) 满足 i < j 且 s[i] = 'b' 同时 s[j]= 'a' 。
 * 请你返回使 s 平衡 的 最少 删除次数。
 * <p>
 * 示例 1：
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * <p>
 * 示例 2：
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'​ 。​
 */
public class Solution5551 {
    /**
     * 通过
     * 执行用时: 42 ms
     * 内存消耗: 39.4 MB
     *
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        char[] chars = s.toCharArray();
        int[] count = new int[chars.length];
        int countB = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'b') {
                countB++;
            }
            count[i] = countB;
        }
        int countA = 0;
        int min = Integer.MAX_VALUE;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'a') {
                countA++;
            }
            count[i] += countA - 1;
            if (count[i] == 0) {
                return 0;
            }
            if (count[i] < min) {
                min = count[i];
            }
        }
        return min;
    }

    /**
     * 原始版本ver1，未提交
     *
     * @param s
     * @return
     */
    public int minimumDeletions1(String s) {
        char[] chars = s.toCharArray();
        int[] countLeftB = new int[chars.length];
        int[] countRightA = new int[chars.length];
        int countB = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'b') {
                countB++;
            }
            countLeftB[i] = countB;
        }
        int countA = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'a') {
                countA++;
            }
            countRightA[i] = countA;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {
            int deleteCount = countRightA[i] + countLeftB[i] - 1;
            if (deleteCount == 0) {
                return 0;
            }
            if (deleteCount < min) {
                min = deleteCount;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Solution5551 solution5551 = new Solution5551();
        // 2
        System.out.println(solution5551.minimumDeletions("aababbab"));
        // 2
        System.out.println(solution5551.minimumDeletions("bbaaaaabb"));
        // 0
        System.out.println(solution5551.minimumDeletions("aaaaabb"));
        // 0
        System.out.println(solution5551.minimumDeletions("aaaaa"));
    }
}
