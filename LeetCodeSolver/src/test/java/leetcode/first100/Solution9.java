package leetcode.first100;

import java.util.LinkedList;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/3 18:56
 * @Description: 9. 回文数 | 难度：简单 | 标签：数学
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution9 {
    /**
     * 执行用时： 10 ms , 在所有 Java 提交中击败了 79.58% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 61.53% 的用户
     * 用数组快一点
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int num = x;
        int[] arr = new int[10];
        int index = 0;
        while (x > 0) {
            arr[index++] = x % 10;
            x /= 10;
        }
        int count = index / 2;
        while (count > 0 && arr[--index] == num % 10) {
            count--;
            num /= 10;
        }
        return count == 0;
    }

    /**
     * 执行用时： 14 ms , 在所有 Java 提交中击败了 17.79% 的用户
     * 内存消耗： 38.3 MB , 在所有 Java 提交中击败了 33.45% 的用户
     * 用链表速度慢一点点
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_linkedList(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int num = x;
        LinkedList<Integer> list = new LinkedList<>();
        while (x > 0) {
            list.add(x % 10);
            x /= 10;
        }
        int count = (list.size() + 1) / 2;
        while (count > 0 && list.peekLast() == num % 10) {
            count--;
            list.removeLast();
            num /= 10;
        }
        return count == 0;
    }
}
