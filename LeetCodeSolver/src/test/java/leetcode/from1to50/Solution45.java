package leetcode.from1to50;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/9 18:51
 * @Description: 45. 跳跃游戏II | 难度：困难 | 标签：贪心算法、数组
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution45 {
    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 40.64% 的用户
     * 内存消耗： 40.1 MB , 在所有 Java 提交中击败了 95.59% 的用户
     * 一次循环内解决，非迭代
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int end = 0;
        int result = 0;
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            maxJump = Math.max(maxJump, nums[i] + i);
            if (i == end) {
                result++;
                end = maxJump;
                if (end >= nums.length - 1) {
                    return result;
                }
            }
        }
        return result;
    }

    @Test
    public void jumpTest() {
        Assert.assertEquals(jump(new int[]{2, 3, 1, 1, 4}), 2);
        Assert.assertEquals(jump(new int[]{0}), 0);
        Assert.assertEquals(jump(new int[]{1, 2}), 1);
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 95.48% 的用户
     * 内存消耗： 42.2 MB, 在所有 Java 提交中击败了 5.03% 的用户
     * 迭代方式(删不删除shortJumps数组效率都一样)
     *
     * @param nums
     * @return
     */
    public int jump_recursive(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] >= nums.length - 1) {
            return 1;
        }
        // boolean[] shortJumps = new boolean[nums.length];
        return jump_recursive(nums, 0, nums[0]/*, shortJumps*/) + 1;
    }

    private int jump_recursive(int[] nums, int index, int range/*, boolean[] shortJumps*/) {
        int maxJumpIndex = -1;
        int maxJumpTo = -1;
        for (int i = index + 1; i <= index + range; i++) {
//            if (!shortJumps[i]) {
            if (nums[i] + i >= nums.length - 1) {
                return 1;
            }
            if (nums[i] + i > maxJumpTo) {
                maxJumpTo = nums[i] + i;
                maxJumpIndex = i;
            }
//                } else {
//                    shortJumps[i] = true;
//                }
//            }
        }
        return jump_recursive(nums, maxJumpIndex, nums[maxJumpIndex]/*, shortJumps*/) + 1;
    }

    @Test
    public void jump_recursiveTest() {
        Assert.assertEquals(jump_recursive(new int[]{2, 3, 1, 1, 4}), 2);
        Assert.assertEquals(jump_recursive(new int[]{0}), 0);
        Assert.assertEquals(jump_recursive(new int[]{1, 2}), 1);
    }
}
