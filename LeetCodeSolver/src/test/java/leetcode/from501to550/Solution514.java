package leetcode.from501to550;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2020/11/11 15:17
 * @Description: 514.自由之路 | 难度：困难 | 标签：深度优先算法、分治算法、动态规划
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 1.您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 2.如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * <p>
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * 提示：
 * 1.ring 和 key 的字符串长度取值范围均为 1 至 100；
 * 2.两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 3.字符串 key 一定可以由字符串 ring 旋转拼出。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution514 {
    /**
     * 执行用时： 15 ms, 在所有 Java 提交中击败了 51.37% 的用户
     * 内存消耗： 39.1 MB, 在所有 Java 提交中击败了 67.15% 的用户
     * 动态规划，感觉最重要的就是怎么去快速判断出转移方程。
     * 然后就是注意可以用滚动数组优化（这里每次转移只用到了dp[i]和dp[i-1]，所以肯定可以用滚动数组。如果只用到dp[i]的，那就可以优化成一维数组了）。
     * TODO：有人提到了Viterbi算法，可以了解一下是个啥
     * TODO：滚动数组优化
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        char[] charsR = ring.toCharArray();
        char[] charsK = key.toCharArray();

        // 用于存储各个字符在ring上的位置
        List<Integer>[] pos = new List[26];

        // 将ring的信息填充pos数组
        for (int i = 0; i < charsR.length; i++) {
            int index = charsR[i] - 'a';
            if (pos[index] == null) {
                pos[index] = new ArrayList<>();
            }
            pos[index].add(i);
        }

        // dp数组用来记录不同转换之间的最短路程
        int[][] dp = new int[charsK.length][charsR.length];
        for (int i = 0; i < charsK.length; i++) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }

        // 针对key的第一个字符，填写跳转到这个字符的所有可能（ring上可以有多个同一字符）
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, charsR.length - i) + 1;
        }

        // 对于key的第二个字符以及后面的字符
        for (int i = 1; i < charsK.length; ++i) {
            // 对于ring上同一字符的每一种选择
            for (int j : pos[key.charAt(i) - 'a']) {
                // 对于 可以到达key的前一个字符同一字符的所有最短路径
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), charsR.length - Math.abs(j - k)) + 1);
                }
            }
        }

        // 选择dp数组最后一行，即key完整字符串对应的所有最短路径，中最短的。
        return Arrays.stream(dp[charsK.length - 1]).min().getAsInt();
    }

    public static void main(String[] args) {
        Solution514 solution514 = new Solution514();
        // 4
        System.out.println(solution514.findRotateSteps("godding","gd"));
        // 16
        System.out.println(solution514.findRotateSteps("caotmcaataijjxi","mjjx"));
        // 137
        System.out.println(solution514.findRotateSteps("caotmcaataijjxi","oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"));
    }
}
