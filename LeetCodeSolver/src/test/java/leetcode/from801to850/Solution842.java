package leetcode.from801to850;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 9:41
 * @Description: 842. 将数组拆分成斐波那契序列 | 难度：中等 | 标签：贪心算法、字符串、回溯算法
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * <p>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * <p>
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * <p>
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * <p>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 * <p>
 * 示例 1：
 * 输入："123456579"
 * 输出：[123,456,579]
 * <p>
 * 示例 2：
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * <p>
 * 示例 3：
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * <p>
 * 示例 4：
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * <p>
 * 示例 5：
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 * 提示：
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution842 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 20.32% 的用户
     * 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 66.28% 的用户
     * 反复地构造字符串无疑降低了效率，如果想办法优化一下待选数字的构造过程（例如换成字符数组的处理）估计效率会好些
     * TODO：优化构造字符串
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new LinkedList<>();
        if (S == null || S.length() == 0) {
            return list;
        }
        String num1 = "";
        String num2 = "";
        while (num1.length() + num2.length() + Math.max(num1.length(), num2.length()) < S.length()) {
            num1 = formNextNum(S, 0, num1.length() + 1);
            if ("".equals(num1)) {
                return new LinkedList<>();
            }
            list.add(Integer.parseInt(num1));
            while (num1.length() + num2.length() + Math.max(num1.length(), num2.length()) < S.length()) {
                num2 = formNextNum(S, num1.length(), num2.length() + 1);
                if ("".equals(num2)) {
                    break;
                }
                list.add(Integer.parseInt(num2));
                if (isFibonacci(S, num1.length() + num2.length(), num1, num2, list)) {
                    return list;
                }
                // System.out.println("num1:" + num1 + " num2:" + num2 + " 不符合");
                // 回溯num2
                list.remove(list.size() - 1);
            }
            num2 = "";
            // 回溯num1
            list.remove(list.size() - 1);
        }
        return new LinkedList<>();
    }

    private String formNextNum(String S, int index, int len) {
        if (S.length() < index + len) {
            return "";
        }
        if (S.charAt(index) == '0') {
            if (len == 1) {
                return "0";
            } else {
                return "";
            }
        }
        String str = S.substring(index, index + len);
        Long num = Long.parseLong(str);
        if (num <= Integer.MAX_VALUE) {
            return str;
        } else {
            return "";
        }
    }

    private boolean isFibonacci(String S, int index, String num1, String num2, List<Integer> list) {
        int lengthS = S.length();
        if (index + Math.max(num1.length(), num2.length()) > lengthS) {
            return false;
        }
        Long num3 = (long) Integer.parseInt(num1) + Integer.parseInt(num2);
        if (num3 > Integer.MAX_VALUE) {
            return false;
        }
        String num3Str = "" + (Integer.parseInt(num1) + Integer.parseInt(num2));
        int lengthNum3 = num3Str.length();
        if (index + lengthNum3 <= lengthS && num3Str.equals(S.substring(index, index + lengthNum3))) {
            list.add(Integer.parseInt(num3Str));
            if (index + lengthNum3 == lengthS) {
                return true;
            }
            if (isFibonacci(S, index + lengthNum3, num2, num3Str, list)) {
                return true;
            } else {
                list.remove(list.size() - 1);
                return false;
            }
        }
        return false;
    }

    @Test
    public void splitIntoFibonacciTest() {
        // [123,456,579]
        System.out.println(splitIntoFibonacci("123456579"));
        // []
        System.out.println(splitIntoFibonacci("214748364721474836422147483641"));
        // [13205,8,13213,13221,26434,39655,66089,105744,171833,277577]
        System.out.println(splitIntoFibonacci("1320581321313221264343965566089105744171833277577"));
    }
}
