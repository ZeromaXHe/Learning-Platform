package leetcode.from1451to1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhuxi
 * @apiNote 1462. 课程表 IV | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、拓扑排序
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * <p>
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * <p>
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * <p>
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * <p>
 * 示例 3：
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 * <p>
 * 提示：
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 104
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * @implNote
 * @since 2023/9/12 10:01
 */
public class Solution1462 {
    /**
     * 官方题解简单修改来的
     * <p>
     * 时间 17 ms
     * 击败 63.64% 使用 Java 的用户
     * 内存 44.43 MB
     * 击败 73.33% 使用 Java 的用户
     *
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> g = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            g.add(new ArrayList<>());
        }
        int[] indgree = new int[numCourses];
        boolean[][] pre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            g.get(p[0]).add(p[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g.get(cur)) {
                pre[cur][ne] = true;
                // 维护 pre 二维数组很关键
                for (int i = 0; i < numCourses; ++i) {
                    pre[i][ne] = pre[i][ne] | pre[i][cur];
                }
                --indgree[ne];
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(pre[query[0]][query[1]]);
        }
        return res;
    }

    /**
     * 超时（28 / 68 个通过的测试用例）
     *
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite_hashMap(int numCourses, int[][] prerequisites, int[][] queries) {
        HashMap<Integer, List<Integer>> preMap = new HashMap<>();
        for (int[] pre : prerequisites) {
            preMap.putIfAbsent(pre[1], new LinkedList<>());
            preMap.get(pre[1]).add(pre[0]);
        }
        List<Boolean> result = new LinkedList<>();
        for (int[] query : queries) {
            result.add(isPre(query[0], query[1], preMap));
        }
        return result;
    }

    private boolean isPre(int queried, int next, HashMap<Integer, List<Integer>> preMap) {
        if (!preMap.containsKey(next)) {
            return false;
        }
        for (int pre : preMap.get(next)) {
            if (pre == queried || isPre(queried, pre, preMap)) {
                return true;
            }
        }
        return false;
    }
}
