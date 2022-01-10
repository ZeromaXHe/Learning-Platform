package leetcode.from301to400;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2022/1/10 9:55
 * @Description: 306. 累加数 | 难度：中等 | 标签：字符串、回溯
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * <p>
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * <p>
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * 示例 1：
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * <p>
 * 示例 2：
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * <p>
 * 提示：
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 * <p>
 * 进阶：你计划如何处理由过大的整数输入导致的溢出?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution306 {
    @Test
    public void isAdditiveNumberTest() {
        Assert.assertTrue(isAdditiveNumber("112358"));
        Assert.assertTrue(isAdditiveNumber("199100199"));
        Assert.assertFalse(isAdditiveNumber("199100099"));
        // 通过测试用例： 24 / 42
        Assert.assertTrue(isAdditiveNumber("123"));
        // 通过测试用例： 29 / 42
        Assert.assertFalse(isAdditiveNumber("199001200"));
        Assert.assertFalse(isAdditiveNumber("1991000199299498797"));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 64.65% 的用户
     * 通过测试用例： 42 / 42
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }

        // 第一个数字以外剩下的位数大于等于第一个数字的长度+1
        for (int firstEnd = 1; firstEnd < (num.length() + 1) / 2; firstEnd++) {
            // 第二个数字后剩下的位数必须大于等于第一个数字和第二个数字里面最长的一个
            for (int secondEnd = firstEnd + 1;
                 secondEnd <= firstEnd + Math.min((num.length() - firstEnd) / 2, num.length() - 2 * firstEnd);
                 secondEnd++) {
                if (isAdditiveNumber(num, firstEnd, secondEnd)) {
                    return true;
                }
            }
        }

        if (num.startsWith("00")) {
            for (int i = 2; i < num.length(); i++) {
                if (num.charAt(i) != '0') {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private boolean isAdditiveNumber(String num, int firstEnd, int secondEnd) {
        int firstStart = 0;
        while (secondEnd < num.length()) {
            // 后续的数字不可能以0开头
            if (num.charAt(secondEnd) == '0' || (secondEnd != firstEnd + 1 && num.charAt(firstEnd) == '0')) {
                return false;
            }
            int thirdEnd = firstPlusSecondEqualsThird(num, firstStart, firstEnd, secondEnd);
            if (thirdEnd != -1) {
                firstStart = firstEnd;
                firstEnd = secondEnd;
                secondEnd = thirdEnd;
            } else {
                return false;
            }
        }
        return true;
    }

    private int firstPlusSecondEqualsThird(String num, int firstStart, int firstEnd, int secondEnd) {
        int leftCharsLen = num.length() - secondEnd;
        if (leftCharsLen < firstEnd - firstStart || leftCharsLen < secondEnd - firstEnd) {
            return -1;
        }
        boolean carry = needCarry(num, firstStart, firstEnd, secondEnd);
        if (carry && (leftCharsLen < firstEnd - firstStart + 1 || leftCharsLen < secondEnd - firstEnd + 1)) {
            return -1;
        }
        return firstPlusSecondEqualsThird(num, firstStart, firstEnd, secondEnd,
                secondEnd + Math.max(firstEnd - firstStart, secondEnd - firstEnd) + (carry ? 1 : 0));
    }

    private int firstPlusSecondEqualsThird(String num, int firstStart, int firstEnd, int secondEnd, int thirdEnd) {
        int index = 0;
        int carry = 0;
        while (firstEnd - 1 - index >= firstStart
                || secondEnd - 1 - index >= firstEnd
                || thirdEnd - 1 - index >= secondEnd) {
            int first = 0;
            int second = 0;
            int third = 0;
            if (firstEnd - 1 - index >= firstStart) {
                first = num.charAt(firstEnd - 1 - index) - '0';
            }
            if (secondEnd - 1 - index >= firstEnd) {
                second = num.charAt(secondEnd - 1 - index) - '0';
            }
            if (thirdEnd - 1 - index >= secondEnd) {
                third = num.charAt(thirdEnd - 1 - index) - '0';
            }
            int sum = first + second + carry;
            if (sum % 10 != third) {
                return -1;
            }
            carry = sum >= 10 ? 1 : 0;
            index++;
        }
        return thirdEnd;
    }

    private boolean needCarry(String num, int firstStart, int firstEnd, int secondEnd) {
        boolean firstLonger = firstEnd - firstStart >= secondEnd - firstEnd;
        int longerStart = firstLonger ? firstStart : firstEnd;
        int longerEnd = firstLonger ? firstEnd : secondEnd;
        int shorterStart = firstLonger ? firstEnd : firstStart;
        int shorterEnd = firstLonger ? secondEnd : firstEnd;
        if (firstEnd - firstStart == secondEnd - firstEnd) {
            return needCarrySameLength(num, longerStart, longerEnd, shorterStart, shorterEnd);
        } else {
            return needCarryDifferentLength(num, longerStart, longerEnd, shorterStart, shorterEnd);
        }
    }

    private boolean needCarryDifferentLength(String num, int longerStart, int longerEnd, int shorterStart, int shorterEnd) {
        int check = longerEnd - longerStart - shorterEnd + shorterStart;
        while (check-- > 0) {
            if (num.charAt(longerStart) != '9') {
                return false;
            }
        }
        return needCarrySameLength(num, longerEnd - shorterEnd + shorterStart, longerEnd, shorterStart, shorterEnd);
    }

    private boolean needCarrySameLength(String num, int longerStart, int longerEnd, int shorterStart, int shorterEnd) {
        if (longerStart == longerEnd) {
            return false;
        }
        int firstDigitSum = num.charAt(longerStart) - '0' + num.charAt(shorterStart) - '0';
        if (firstDigitSum >= 10) {
            return true;
        } else if (firstDigitSum < 9) {
            return false;
        } else {
            return needCarrySameLength(num, longerStart + 1, longerEnd, shorterStart + 1, shorterEnd);
        }
    }
}
