package leetcode.from1151to1200;

import java.time.LocalDate;

/**
 * @Author: zhuxi
 * @Time: 2021/12/21 10:01
 * @Description: 1154. 一年中的第几天 | 难度：简单 | 标签：数学、字符串
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * <p>
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 * <p>
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 * <p>
 * 示例 3：
 * 输入：date = "2003-03-01"
 * 输出：60
 * <p>
 * 示例 4：
 * 输入：date = "2004-03-01"
 * 输出：61
 * <p>
 * 提示：
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution1154 {
    int[] days = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    /**
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 99.09% 的用户
     * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 58.94% 的用户
     * 通过测试用例： 10957 / 10957
     *
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        // return LocalDate.parse(date).getDayOfYear();
        int year = Integer.parseInt(date.substring(0, 4));
        boolean leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        int month = Integer.parseInt(date.substring(5, 7));
        return days[month - 1] + Integer.parseInt(date.substring(8, 10)) + (month > 2 && leap ? 1 : 0);
    }
}
