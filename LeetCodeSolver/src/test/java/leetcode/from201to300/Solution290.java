package leetcode.from201to300;

import java.util.HashMap;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/16 9:36
 * @Description: 290.单词规律 | 难度：简单 | 标签：哈希表
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution290 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.94% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 73.14% 的用户
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.trim().split(" ");
        if (pattern.length() != arr.length) {
            return false;
        }
        HashMap<String, Character> map = new HashMap<>();
        boolean[] charExist = new boolean[26];
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                if (!map.get(arr[i]).equals(pattern.charAt(i))){
                    return false;
                }
            }else{
                if(charExist[pattern.charAt(i)-'a']){
                    return false;
                }
                map.put(arr[i],pattern.charAt(i));
            }
            charExist[pattern.charAt(i)-'a']=true;
        }
        return true;
    }
}
