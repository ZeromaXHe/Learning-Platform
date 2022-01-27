package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/1/15 11:16
 * @Description: 76.最小覆盖子串 | 难度：困难 | 标签：哈希表、双指针、字符串、滑动窗口
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution76 {
    /**
     * 执行用时： 17 ms , 在所有 Java 提交中击败了 47.89% 的用户
     * 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 12.69% 的用户
     * 19ms的用户答案示例
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow_userAnswer(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> winMap = new HashMap<>();
        //将数组元素映射到hash集合中
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        //定义窗口中是否包含t的子串的标志，若vaild==t.length(),那么则说明当前窗口已经包含了覆盖t的子串
        int vaild = 0;
        //定义左右指针
        int left = 0, right = 0;
        //定义窗口的起始位置
        int start = 0;
        //定义窗口的长度
        int len = Integer.MAX_VALUE;
        //遍历长字符串s
        while (right < s.length()) {
            //s = "ADOBECODEBANC", t = "ABC"
            char ch = s.charAt(right);
            right++;
            if (tMap.keySet().contains(ch)) {
                winMap.put(ch, winMap.getOrDefault(ch, 0) + 1);
                if (tMap.get(ch).equals(winMap.get(ch))) {
                    vaild++;
                }
            }
            //若当前窗口已经包含了覆盖t的子串，则收缩左指针，尝试缩小窗口范围，找到最优解
            while (vaild == tMap.size() && right > left) {
                //比较当前窗口的大小和之前窗口的大小获得较小的那个
                if (len > right - left) {
                    len = right - left;
                    start = left;
                }
                char c = s.charAt(left);
                left++;
                if (tMap.keySet().contains(c)) {
                    //从窗口中移除和t中字符相等的字符
                    if (winMap.get(c).equals(tMap.get(c))) {
                        vaild--;
                    }
                    winMap.put(c, winMap.getOrDefault(c, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 266 / 266 个通过测试用例
     * 状态：通过
     * 执行用时: 138 ms
     * 内存消耗: 39.1 MB
     * 没什么变化，如果要改变时间，估计得对hashMap判断逻辑做进一步优化（最优就是用128位的数组）
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        if (t.length() == 1) {
            if (s.contains(t)) {
                return t;
            } else {
                return "";
            }
        }
        HashMap<Character, Integer> match = new HashMap<>();
        HashMap<Character, Integer> target = new HashMap<>();
        int left = 0;
        int right = t.length();
        for (char c : t.toCharArray()) {
            target.merge(c, 1, Integer::sum);
        }
        for (int i = 0; i < right; i++) {
            if (target.containsKey(s.charAt(i))) {
                match.merge(s.charAt(i), 1, Integer::sum);
            }
        }
        String minSub = null;
        while (right < s.length()) {
            if (checkMatchTarget(match, target)) {
                if (minSub == null || minSub.length() > right - left) {
                    minSub = s.substring(left, right);
                }
                left = leftPlus(s, match, target, left);
            } else {
                while (right < s.length() - 1 && !target.containsKey(s.charAt(right))) {
                    right++;
                }
                right = rightPlus(s, match, target, right);
            }
        }
        while (checkMatchTarget(match, target)) {
            while (left < s.length() - t.length() && !target.containsKey(s.charAt(left))) {
                left++;
            }
            if (minSub == null || minSub.length() > right - left) {
                minSub = s.substring(left, right);
            }
            left = leftPlus(s, match, target, left);
        }
        return minSub == null ? "" : minSub;
    }

    private int rightPlus(String s, HashMap<Character, Integer> match, HashMap<Character, Integer> target, int right) {
        if (!target.containsKey(s.charAt(right))) {
            return right + 1;
        }
        match.merge(s.charAt(right), 1, Integer::sum);
        right++;
        return right;
    }

    private int leftPlus(String s, HashMap<Character, Integer> match, HashMap<Character, Integer> target, int left) {
        if (!target.containsKey(s.charAt(left))) {
            return left + 1;
        }
        int num = match.get(s.charAt(left));
        if (num > 1) {
            match.put(s.charAt(left), num - 1);
        } else {
            match.remove(s.charAt(left));
        }
        left++;
        return left;
    }

    private boolean checkMatchTarget(HashMap<Character, Integer> match, HashMap<Character, Integer> target) {
        if (match.size() != target.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            if (match.containsKey(entry.getKey())) {
                if (match.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行用时： 142 ms , 在所有 Java 提交中击败了 9.82% 的用户
     * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 21.36% 的用户
     * 太慢了，待优化
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow_slow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        if (t.length() == 1) {
            if (s.contains(t)) {
                return t;
            } else {
                return "";
            }
        }
        HashMap<Character, Integer> match = new HashMap<>();
        HashMap<Character, Integer> target = new HashMap<>();
        int left = 0;
        int right = t.length();
        for (char c : t.toCharArray()) {
            target.merge(c, 1, Integer::sum);
        }
        for (int i = 0; i < right; i++) {
            match.merge(s.charAt(i), 1, Integer::sum);
        }
        String minSub = null;
        while (right < s.length()) {
            if (checkMatchTarget_slow(match, target)) {
                if (minSub == null || minSub.length() > right - left) {
                    minSub = s.substring(left, right);
                }
                left = leftPlus_slow(s, match, left);
            } else {
                while (right < s.length() - 1 && !target.containsKey(s.charAt(right))) {
                    right = rightPlus_slow(s, match, right);
                }
                right = rightPlus_slow(s, match, right);
            }
        }
        while (checkMatchTarget_slow(match, target)) {
            while (left < s.length() - t.length() && !target.containsKey(s.charAt(left))) {
                left = leftPlus_slow(s, match, left);
            }
            if (minSub == null || minSub.length() > right - left) {
                minSub = s.substring(left, right);
            }
            left = leftPlus_slow(s, match, left);
        }
        return minSub == null ? "" : minSub;
    }

    private int rightPlus_slow(String s, HashMap<Character, Integer> match, int right) {
        match.merge(s.charAt(right), 1, Integer::sum);
        right++;
        return right;
    }

    private int leftPlus_slow(String s, HashMap<Character, Integer> match, int left) {
        int num = match.get(s.charAt(left));
        if (num > 1) {
            match.put(s.charAt(left), num - 1);
        } else {
            match.remove(s.charAt(left));
        }
        left++;
        return left;
    }

    private boolean checkMatchTarget_slow(HashMap<Character, Integer> match, HashMap<Character, Integer> target) {
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            if (match.containsKey(entry.getKey())) {
                if (match.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void minWindowTest() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }
}
