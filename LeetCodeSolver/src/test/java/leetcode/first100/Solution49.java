package leetcode.first100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/14 9:13
 * @Description: 49. 字母异位词分组 | 难度：中等 | 标签：哈希表、字符串
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution49 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 74.94% 的用户
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 97.53% 的用户
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new LinkedList<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if(map.containsKey(s)){
                map.get(s).add(str);
            }else{
                List<String> list = new LinkedList<>();
                list.add(str);
                map.put(s, list);
                result.add(list);
            }
        }
        return result;
    }
}
