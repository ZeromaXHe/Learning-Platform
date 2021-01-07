package leetcode.from301to400;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/6 19:01
 * @Description: 399. 除法求值 | 难度：中等 | 标签：并查集、图
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * <p>
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *  
 * 提示：
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution399 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.24% 的用户
     * 内存消耗： 37.2 MB , 在所有 Java 提交中击败了 66.02% 的用户
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, String> mapParent = new HashMap<>();
        HashMap<String, Double> mapFrac = new HashMap<>();

        Iterator<List<String>> iterator = equations.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            List<String> next = iterator.next();
            // 被除数
            String dividend = next.get(0);
            // 除数
            String divisor = next.get(1);
            // 商
            double quotient = values[index++];
            union(mapParent, mapFrac, dividend, divisor, quotient);
        }

        double[] result = new double[queries.size()];
        index = 0;
        Iterator<List<String>> iterator2 = queries.iterator();
        while (iterator2.hasNext()) {
            List<String> next = iterator2.next();
            // 被除数
            String dividend = next.get(0);
            // 除数
            String divisor = next.get(1);
            result[index++] = divide(mapParent, mapFrac, dividend, divisor);
        }
        return result;
    }

    private void union(HashMap<String, String> mapParent, HashMap<String, Double> mapFrac, String dividend, String divisor, double quotient) {
        if (!mapParent.containsKey(dividend)) {
            mapParent.put(dividend, dividend);
            mapFrac.put(dividend, 1.0);
        }
        if (!mapParent.containsKey(divisor)) {
            mapParent.put(divisor, divisor);
            mapFrac.put(divisor, 1.0);
        }
        // 被除数所属并查集的根父节点
        String dividendRootParent = dividend;
        // 被除数是其根父节点的倍数
        double dividendMultipleToRootParent = 1.0;
        // 除数所属并查集的根父节点
        String divisorRootParent = divisor;
        // 除数是其根父节点的倍数
        double divisorMultipleToRootParent = 1.0;
        while (mapParent.containsKey(dividendRootParent) && !dividendRootParent.equals(mapParent.get(dividendRootParent))) {
            dividendMultipleToRootParent *= mapFrac.get(dividendRootParent);
            dividendRootParent = mapParent.get(dividendRootParent);
        }
        while (mapParent.containsKey(divisorRootParent) && !divisorRootParent.equals(mapParent.get(divisorRootParent))) {
            divisorMultipleToRootParent *= mapFrac.get(divisorRootParent);
            divisorRootParent = mapParent.get(divisorRootParent);
        }
        if (dividendRootParent.equals(divisorRootParent)) {
            return;
        }
        mapParent.put(dividendRootParent, divisorRootParent);
        mapFrac.put(dividendRootParent, divisorMultipleToRootParent * quotient / dividendMultipleToRootParent);
    }

    private double divide(HashMap<String, String> mapParent, HashMap<String, Double> mapFrac, String dividend, String divisor) {
        if (!mapParent.containsKey(dividend) || !mapParent.containsKey(divisor)) {
            return -1.0;
        }
        // 被除数所属并查集的根父节点
        String dividendRootParent = dividend;
        // 被除数是其根父节点的倍数
        double dividendMultipleToRootParent = 1.0;
        // 除数所属并查集的根父节点
        String divisorRootParent = divisor;
        // 除数是其根父节点的倍数
        double divisorMultipleToRootParent = 1.0;
        while (mapParent.containsKey(dividendRootParent) && !dividendRootParent.equals(mapParent.get(dividendRootParent))) {
            dividendMultipleToRootParent *= mapFrac.get(dividendRootParent);
            dividendRootParent = mapParent.get(dividendRootParent);
        }
        while (mapParent.containsKey(divisorRootParent) && !divisorRootParent.equals(mapParent.get(divisorRootParent))) {
            divisorMultipleToRootParent *= mapFrac.get(divisorRootParent);
            divisorRootParent = mapParent.get(divisorRootParent);
        }
        if (dividendRootParent.equals(divisorRootParent)) {
            return dividendMultipleToRootParent / divisorMultipleToRootParent;
        } else {
            return -1.0;
        }
    }
}
