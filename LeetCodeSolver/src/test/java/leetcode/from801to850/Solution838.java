package leetcode.from801to850;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/2/21 10:54
 * @Description: 838. 推多米诺 | 难度： 中等 | 标签： 双指针、字符串、动态规划
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * <p>
 * 示例 1：
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * <p>
 * 示例 2：
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * <p>
 * 提示：
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/push-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution838 {
    @Test
    public void pushDominoesTest() {
        Assert.assertEquals("RR.L", pushDominoes("RR.L"));
        Assert.assertEquals("LL.RR.LLRRLL..", pushDominoes(".L.R...LR..L.."));
        // 通过测试用例： 26 / 43
        Assert.assertEquals("LL.RR", pushDominoes(".L.R."));
    }

    /**
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 79.73% 的用户
     * 内存消耗： 42.7 MB , 在所有 Java 提交中击败了 19.36% 的用户
     * 通过测试用例： 43 / 43
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        char[] result = new char[dominoes.length()];
        boolean fromR = false;
        int from = 0;
        int index = 0;
        while (index < result.length) {
            while (index < result.length && dominoes.charAt(index) == '.') {
                index++;
            }
            if (index == result.length) {
                for (int i = from; i < index; i++) {
                    result[i] = fromR ? 'R' : '.';
                }
                break;
            }
            if (dominoes.charAt(index) == 'L') {
                if (fromR) {
                    int l = index;
                    int r = from;
                    while (r < l) {
                        result[r++] = 'R';
                        result[l--] = 'L';
                    }
                    if (r == l) {
                        result[r] = '.';
                    }
                    fromR = false;
                } else {
                    for (int i = from; i <= index; i++) {
                        result[i] = 'L';
                    }
                }
            } else {
                result[index] = 'R';
                for (int i = from; i < index; i++) {
                    result[i] = fromR ? 'R' : '.';
                }
                fromR = true;
                from = index;
            }

            index++;
            if (!fromR) {
                from = index;
            }
        }
        return new String(result);
    }
}
