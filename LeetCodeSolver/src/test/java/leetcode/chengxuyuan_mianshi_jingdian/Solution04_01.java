package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 15:45
 * @Description: 面试题 04.01. 节点间通路 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * <p>
 * 示例1:
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * <p>
 * 示例2:
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 * <p>
 * 提示：
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/route-between-nodes-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution04_01 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.43% 的用户
     * 内存消耗： 80.7 MB , 在所有 Java 提交中击败了 85.65% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 参考的结果里面最快的方法。有点不可思议…… 是测试用例的原因吗？
     * 最离谱的是，看结果里面的迭代 dfs 的方法，加入辅助方法越多就越慢。
     * 加入 visit 辅助数组的大概 2ms ~ 5ms，然后再加 map 数组来存储图的又更慢，就离谱……
     * 而且还有一个直接测试用例打表过的，服了……
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath_recursive(int n, int[][] graph, int start, int target) {
        if (start == target) {
            return true;
        }
        for (int[] entry : graph) {
            if (target == entry[1]) {
                return findWhetherExistsPath_recursive(n, graph, start, entry[0]);
            }
        }
        return false;
    }

    /**
     * 执行用时： 29 ms , 在所有 Java 提交中击败了 46.37% 的用户
     * 内存消耗： 84.7 MB , 在所有 Java 提交中击败了 36.80% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if (start == target) {
            return true;
        }
        Map<Integer, LinkedList<Integer>> startMap = new HashMap<>();
        for (int[] e : graph) {
            startMap.putIfAbsent(e[0], new LinkedList<>());
            startMap.get(e[0]).add(e[1]);
        }
        boolean[] visit = new boolean[n];
        LinkedList<Integer> queueStart = new LinkedList<>();
        queueStart.offer(start);
        visit[start] = true;
        int sizeStart = 1;

        while (!queueStart.isEmpty()) {
            for (int i = 0; i < sizeStart; i++) {
                int from = queueStart.poll();
                if (startMap.containsKey(from)) {
                    for (int to : startMap.get(from)) {
                        if (to == target) {
                            return true;
                        }
                        if (!visit[to]) {
                            queueStart.offer(to);
                            visit[to] = true;
                        }
                    }
                }
            }
            sizeStart = queueStart.size();
        }
        return false;
    }

    /**
     * 执行用时： 50 ms , 在所有 Java 提交中击败了 5.36% 的用户
     * 内存消耗： 86.1 MB , 在所有 Java 提交中击败了 26.00% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath_doubleMap(int n, int[][] graph, int start, int target) {
        if (start == target) {
            return true;
        }
        Map<Integer, LinkedList<Integer>> startMap = new HashMap<>();
        Map<Integer, LinkedList<Integer>> targetMap = new HashMap<>();
        for (int[] e : graph) {
            startMap.putIfAbsent(e[0], new LinkedList<>());
            startMap.get(e[0]).add(e[1]);
            targetMap.putIfAbsent(e[1], new LinkedList<>());
            targetMap.get(e[1]).add(e[0]);
        }
        boolean[] visitStart = new boolean[n];
        LinkedList<Integer> queueStart = new LinkedList<>();
        queueStart.offer(start);
        visitStart[start] = true;
        int sizeStart = 1;

        boolean[] visitTarget = new boolean[n];
        LinkedList<Integer> queueTarget = new LinkedList<>();
        queueTarget.offer(target);
        visitTarget[target] = true;
        int sizeTarget = 1;

        while (!queueStart.isEmpty() && !queueTarget.isEmpty()) {
            for (int i = 0; i < sizeStart; i++) {
                int from = queueStart.poll();
                if (startMap.containsKey(from)) {
                    for (int to : startMap.get(from)) {
                        if (visitTarget[to]) {
                            return true;
                        }
                        if (!visitStart[to]) {
                            queueStart.offer(to);
                            visitStart[to] = true;
                        }
                    }
                }
            }
            if (queueStart.isEmpty()) {
                return false;
            }
            sizeStart = queueStart.size();

            for (int i = 0; i < sizeTarget; i++) {
                int to = queueTarget.poll();
                if (targetMap.containsKey(to)) {
                    for (int from : targetMap.get(to)) {
                        if (visitStart[from]) {
                            return true;
                        }
                        if (!visitTarget[from]) {
                            queueTarget.offer(from);
                            visitTarget[from] = true;
                        }
                    }
                }
            }
            if (queueTarget.isEmpty()) {
                return false;
            }
            sizeTarget = queueTarget.size();
        }
        return false;
    }

    /**
     * 执行用时： 53 ms , 在所有 Java 提交中击败了 5.36% 的用户
     * 内存消耗： 86.3 MB , 在所有 Java 提交中击败了 23.36% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath_doubleMapWithSet(int n, int[][] graph, int start, int target) {
        if (start == target) {
            return true;
        }
        Map<Integer, LinkedList<Integer>> startMap = new HashMap<>();
        Map<Integer, LinkedList<Integer>> targetMap = new HashMap<>();
        for (int[] e : graph) {
            startMap.putIfAbsent(e[0], new LinkedList<>());
            startMap.get(e[0]).add(e[1]);
            targetMap.putIfAbsent(e[1], new LinkedList<>());
            targetMap.get(e[1]).add(e[0]);
        }
        Set<Integer> startSet = new HashSet<>();
        Set<Integer> addStartSet = new HashSet<>();
        startSet.add(start);
        int startPreCount = 1;
        Set<Integer> targetSet = new HashSet<>();
        Set<Integer> addTargetSet = new HashSet<>();
        targetSet.add(target);
        int targetPreCount = 1;
        for (int i = 0; i < n / 2; i++) {
            for (int from : startSet) {
                if (startMap.containsKey(from)) {
                    for (int to : startMap.get(from)) {
                        if (targetSet.contains(to)) {
                            return true;
                        }
                        addStartSet.add(to);
                    }
                }
            }
            startSet.addAll(addStartSet);
            addStartSet.clear();
            if (startSet.size() == startPreCount) {
                return false;
            }
            startPreCount = startSet.size();

            for (int to : targetSet) {
                if (targetMap.containsKey(to)) {
                    for (int from : targetMap.get(to)) {
                        if (startSet.contains(from)) {
                            return true;
                        }
                        addTargetSet.add(from);
                    }
                }
            }
            targetSet.addAll(addTargetSet);
            addTargetSet.clear();
            if (targetSet.size() == targetPreCount) {
                return false;
            }
            targetPreCount = targetSet.size();
        }
        return false;
    }
}
