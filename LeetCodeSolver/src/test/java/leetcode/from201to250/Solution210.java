package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/2/8 10:43
 * @Description: 210. 课程表 II | 难度：中等 | 标签：深度优先搜索、广度优先搜索、图、拓扑排序
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * <p>
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * <p>
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 * <p>
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution210 {
    @Test
    public void findOrderTest() {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(findOrder(1, new int[][]{})));
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 97.69% 的用户
     * 内存消耗： 42.7 MB , 在所有 Java 提交中击败了 5.43% 的用户
     * 通过测试用例： 44 / 44
     * <p>
     * 图的知识感觉有点生疏了，参考了题解
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 存储有向图
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        // 存储每个节点的入度
        int[] indeg = new int[numCourses];
        // 存储答案
        int[] result = new int[numCourses];
        int index = 0;
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            indeg[prerequisite[0]]++;
        }
        for (int i = 0; i < indeg.length; i++) {
            index = addIn0ToResult(i, index, result, indeg, edges);
        }
        if (index < numCourses) {
            return new int[0];
        } else {
            return result;
        }
    }

    private int addIn0ToResult(int i, int resultIndex, int[] result, int[] indeg, List<List<Integer>> edges) {
        if (indeg[i] == 0) {
            result[resultIndex++] = i;
            for (int to : edges.get(i)) {
                indeg[to]--;
                resultIndex = addIn0ToResult(to, resultIndex, result, indeg, edges);
            }
            indeg[i] = -1;
        }
        return resultIndex;
    }
}
