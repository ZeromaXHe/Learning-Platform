package leetcode.from601to650;

import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/11 9:37
 * @Description: 649.Dota2参议院 | 难度：中等 | 标签：贪心算法
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * <p>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * <p>
 * 1.禁止一名参议员的权利：
 * <p>
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * <p>
 * 2.宣布胜利：
 * <p>
 * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * <p>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * <p>
 * 示例 1：
 * 输入："RD"
 * 输出："Radiant"
 * 解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * <p>
 * 示例 2：
 * 输入："RDD"
 * 输出："Dire"
 * 解释：
 * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 *  
 * <p>
 * 提示：
 * 给定字符串的长度在 [1, 10,000] 之间.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dota2-senate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution649 {
    private final static String RADIANT = "Radiant";
    private final static String DIRE = "Dire";

    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 78.29% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 95.70% 的用户
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        if (senate.length() == 1) {
            if (senate.charAt(0) == 'R') {
                return RADIANT;
            } else {
                return DIRE;
            }
        }
        int length = senate.length();
        int lastIndex = length - 1;
        int firstIndex = 0;
        int validCount = length;
        int radiantCount = 0;
        int direCount = 0;
        // 存储下一个有效位置的数组
        int[] nextValid = new int[length];
        // 存储上一个有效位置的数组
        int[] preValid = new int[length];
        for (int i = 0; i < length; i++) {
            nextValid[i] = (i + 1) % length;
            preValid[i] = i == 0 ? length - 1 : i - 1;
            if (senate.charAt(i) == 'R') {
                radiantCount++;
            } else {
                direCount++;
            }
        }
        // 优势派别
        char dominating = senate.charAt(firstIndex);
        // 天辉有权力数量
        int rCount = senate.charAt(firstIndex) == 'R' ? 1 : 0;
        // 夜魇有权力数量
        int dCount = senate.charAt(firstIndex) == 'D' ? 1 : 0;

        int index = nextValid[firstIndex];
        while (lastIndex != firstIndex) {
            char c = senate.charAt(index);
            if (dominating == c) {
                if (c == 'R') {
                    rCount++;
                } else {
                    dCount++;
                }
            } else {
                if (c == 'R') {
                    if (dCount > 0) {
                        dCount--;
                        nextValid[preValid[index]] = nextValid[index];
                        preValid[nextValid[index]] = preValid[index];
                        if (lastIndex == index) {
                            lastIndex = preValid[index];
                        }
                        if (firstIndex == index) {
                            firstIndex = nextValid[index];
                        }
                        validCount--;
                        radiantCount--;
                    } else {
                        dominating = 'R';
                        rCount++;
                    }
                } else {
                    if (rCount > 0) {
                        rCount--;
                        nextValid[preValid[index]] = nextValid[index];
                        preValid[nextValid[index]] = preValid[index];
                        if (lastIndex == index) {
                            lastIndex = preValid[index];
                        }
                        if (firstIndex == index) {
                            firstIndex = nextValid[index];
                        }
                        validCount--;
                        direCount--;
                    } else {
                        dominating = 'D';
                        dCount++;
                    }
                }
            }
            if (radiantCount == validCount) {
                return RADIANT;
            }
            if (direCount == validCount) {
                return DIRE;
            }
            index = nextValid[index];
        }

        return "";
    }

    @Test
    public void predictPartyVictoryTest() {
        // "RDDR" = R win
        // "RDDDRR" = D win
        // "RDDDRRR" = R win
        // "RDDRD" = D win
        // "RDDDRR" = R win
        // "RRRDDDD" = R win
        // "RRRDDDDD" = D win
        // "RRDDD" = R win
        // "RRDDDD" = D win

        System.out.println(predictPartyVictory("D"));
    }

    @Test
    public void modNegativeTest() {
        // -1
        System.out.println((-1) % 7);
    }
}
