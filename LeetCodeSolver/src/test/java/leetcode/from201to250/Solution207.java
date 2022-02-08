package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/2/8 9:38
 * @Description: 207. 课程表 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、拓扑排序
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * 提示：
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution207 {
    @Test
    public void canFinishTest() {
        Assert.assertTrue(canFinish(1, new int[][]{{1, 0}}));
        Assert.assertFalse(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 95.68% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 10.61% 的用户
     * 通过测试用例： 51 / 51
     * <p>
     * 参考210题进行优化
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 存储有向图
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        // 存储每个节点的入度
        int[] indeg = new int[numCourses];
        int count = 0;
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            indeg[prerequisite[0]]++;
        }
        for (int i = 0; i < indeg.length; i++) {
            count = addIn0ToResult(i, count, indeg, edges);
        }
        return count == numCourses;
    }

    private int addIn0ToResult(int i, int count, int[] indeg, List<List<Integer>> edges) {
        if (indeg[i] == 0) {
            count++;
            for (int to : edges.get(i)) {
                indeg[to]--;
                count = addIn0ToResult(to, count, indeg, edges);
            }
            indeg[i] = -1;
        }
        return count;
    }

    /**
     * 执行用时： 135 ms , 在所有 Java 提交中击败了 5.13% 的用户
     * 内存消耗： 42.6 MB , 在所有 Java 提交中击败了 5.00% 的用户
     * 通过测试用例： 51 / 51
     * <p>
     * 待优化
     * 根据 numCourses 其实可以使用数组来代替 HashMap
     * 然后先建立图之后再进行拓扑排序，效率会比插入过程中校验高。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            if (!dfs(map, prerequisite[0], prerequisite[1])) {
                return false;
            }
            map.putIfAbsent(prerequisite[1], new LinkedList<>());
            map.get(prerequisite[1]).add(prerequisite[0]);
        }
        return true;
    }

    private boolean dfs(HashMap<Integer, LinkedList<Integer>> map, int key, int value) {
        if (key == value) {
            return false;
        }
        LinkedList<Integer> list = map.get(key);
        if (list == null) {
            return true;
        }
        for (int connect : list) {
            if (!dfs(map, connect, value)) {
                return false;
            }
        }
        return true;
    }
}
