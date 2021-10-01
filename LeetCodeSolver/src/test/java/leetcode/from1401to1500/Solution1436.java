package leetcode.from1401to1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/10/1 9:46
 * @Description: 1436. 旅行终点站 | 难度：简单 | 标签：哈希表、字符串
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。
 * 请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * <p>
 * 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。
 * <p>
 * 示例 1：
 * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * 输出："Sao Paulo"
 * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
 * <p>
 * 示例 2：
 * 输入：paths = [["B","C"],["D","B"],["C","A"]]
 * 输出："A"
 * 解释：所有可能的线路是：
 * "D" -> "B" -> "C" -> "A". 
 * "B" -> "C" -> "A". 
 * "C" -> "A". 
 * "A". 
 * 显然，旅行终点站是 "A" 。
 * <p>
 * 示例 3：
 * 输入：paths = [["A","Z"]]
 * 输出："Z"
 * <p>
 * 提示：
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * 所有字符串均由大小写英文字母和空格字符组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/destination-city
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: ZeromaXHe
 */
public class Solution1436 {
    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 94.47% 的用户
     * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 50.26% 的用户
     * 通过测试用例： 103 / 103
     *
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> path : paths) {
            String from = path.get(0);
            List<String> toList = map.getOrDefault(from, new ArrayList<>());
            toList.add(path.get(1));
            map.put(from, toList);
        }
        String dest = paths.get(0).get(1);
        while (map.containsKey(dest)) {
            dest = map.get(dest).get(0);
        }
        return dest;
    }
}
