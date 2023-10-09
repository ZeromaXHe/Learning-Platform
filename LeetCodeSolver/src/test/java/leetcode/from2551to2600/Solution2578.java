package leetcode.from2551to2600;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhuxi
 * @apiNote 2578. 最小和分割 | 难度：简单 | 标签：贪心、数学、排序
 * 给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：
 * <p>
 * num1 和 num2 直接连起来，得到 num 各数位的一个排列。
 * 换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。
 * num1 和 num2 可以包含前导 0 。
 * 请你返回 num1 和 num2 可以得到的和的 最小 值。
 * <p>
 * 注意：
 * num 保证没有前导 0 。
 * num1 和 num2 中数位顺序可以与 num 中数位顺序不同。
 * <p>
 * 示例 1：
 * 输入：num = 4325
 * 输出：59
 * 解释：我们可以将 4325 分割成 num1 = 24 和 num2 = 35 ，和为 59 ，59 是最小和。
 * <p>
 * 示例 2：
 * 输入：num = 687
 * 输出：75
 * 解释：我们可以将 687 分割成 num1 = 68 和 num2 = 7 ，和为最优值 75 。
 * <p>
 * 提示：
 * 10 <= num <= 109
 * @implNote
 * @since 2023/10/9 9:57
 */
public class Solution2578 {
    /**
     * 时间 1 ms
     * 击败 91.00% 使用 Java 的用户
     * 内存 37.21 MB
     * 击败 87.00% 使用 Java 的用户
     *
     * @param num
     * @return
     */
    public int splitNum(int num) {
        ArrayList<Integer> nums = new ArrayList<>();
        while (num > 0) {
            nums.add(num % 10);
            num /= 10;
        }
        Collections.sort(nums);
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i % 2 == 0) {
                num1 *= 10;
                num1 += nums.get(i);
            } else {
                num2 *= 10;
                num2 += nums.get(i);
            }
        }
        return num1 + num2;
    }
}
