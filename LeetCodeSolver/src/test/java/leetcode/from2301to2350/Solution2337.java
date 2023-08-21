package leetcode.from2301to2350;

/**
 * @author zhuxi
 * @apiNote 2337. 移动片段得到字符串 | 难度：中等 | 标签：双指针、字符串
 * 给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：
 * <p>
 * 字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。
 * 字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。
 * 如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：start = "_L__R__R_", target = "L______RR"
 * 输出：true
 * 解释：可以从字符串 start 获得 target ，需要进行下面的移动：
 * - 将第一个片段向左移动一步，字符串现在变为 "L___R__R_" 。
 * - 将最后一个片段向右移动一步，字符串现在变为 "L___R___R" 。
 * - 将第二个片段向右移动散步，字符串现在变为 "L______RR" 。
 * 可以从字符串 start 得到 target ，所以返回 true 。
 * <p>
 * 示例 2：
 * 输入：start = "R_L_", target = "__LR"
 * 输出：false
 * 解释：字符串 start 中的 'R' 片段可以向右移动一步得到 "_RL_" 。
 * 但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
 * <p>
 * 示例 3：
 * 输入：start = "_R", target = "R_"
 * 输出：false
 * 解释：字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。
 * <p>
 * 提示：
 * n == start.length == target.length
 * 1 <= n <= 105
 * start 和 target 由字符 'L'、'R' 和 '_' 组成
 * @implNote
 * @since 2023/8/21 9:55
 */
public class Solution2337 {
    /**
     * 时间 21 ms
     * 击败 44.30% 使用 Java 的用户
     * 内存 42.61 MB
     * 击败 41.77% 使用 Java 的用户
     *
     * @param start
     * @param target
     * @return
     */
    public boolean canChange(String start, String target) {
        int n = start.length();
        int slcount = 0;
        int srcount = 0;
        int tlcount = 0;
        int trcount = 0;
        for (int i = 0; i < n; i++) {
            if (start.charAt(i) == 'L') {
                slcount++;
            } else if (start.charAt(i) == 'R') {
                srcount++;
            }
            if (target.charAt(i) == 'L') {
                tlcount++;
                if (slcount == tlcount && start.charAt(i) != 'L') {
                    return false;
                }
                if (slcount > tlcount || srcount != trcount) {
                    return false;
                }
            } else if (target.charAt(i) == 'R') {
                trcount++;
                if (slcount != tlcount || srcount < trcount) {
                    return false;
                }
            }
            if (start.charAt(i) == 'L') {
                if (srcount > trcount) {
                    return false;
                }
            } else if (start.charAt(i) == 'R') {
                if (slcount < tlcount) {
                    return false;
                }
            }
        }
        return slcount == tlcount && srcount == trcount;
    }
}
