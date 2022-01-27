package leetcode.from151to200;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/4/12 19:51
 * @Description: 179.最大数 | 难度：中等 | 标签：排序
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * <p>
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution179 {
    /**
     * 官方题解
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.50% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 99.42% 的用户
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    /**
     * 执行用时： 11 ms , 在所有 Java 提交中击败了 22.62% 的用户
     * 内存消耗： 37.6 MB , 在所有 Java 提交中击败了 94.00% 的用户
     *
     * @param nums
     * @return
     */
    public String largestNumber_stringSort(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        String[] numStrs = new String[n];
        for (int i = 0; i < n; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrs, (s1, s2) -> {
            long l = -Long.parseLong(s1 + s2) + Long.parseLong(s2 + s1);
            if (l > 0) {
                return 1;
            } else if (l == 0) {
                return 0;
            } else {
                return -1;
            }
        });

        if ("0".equals(numStrs[0])) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (String numStr : numStrs) {
            ret.append(numStr);
        }
        return ret.toString();
    }
}
