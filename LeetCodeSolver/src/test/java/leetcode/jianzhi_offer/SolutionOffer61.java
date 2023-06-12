package leetcode.jianzhi_offer;

import java.util.Arrays;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 61. 扑克牌中的顺子 | 难度：简单 | 标签：数组、排序
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * 限制：
 * 数组长度为 5 
 * 数组的数取值为 [0, 13] .
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/12 14:32
 */
public class SolutionOffer61 {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 36.08% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了 10.57% 的用户
     * 通过测试用例：204 / 204
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for (int i = 0; i < 5; i++) {
            if (nums[i] == 0) {
                joker++;
            } else if (i > 0 && nums[i - 1] != 0 && (nums[i] == nums[i - 1] || nums[i] - nums[i - 1] - 1 > joker)) {
                return false;
            } else if (i > 0 && nums[i - 1] != 0) {
                joker -= nums[i] - nums[i - 1] - 1;
            }
        }
        return true;
    }
}
