package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/11 18:41
 * @Description: 面试题 08.02. 迷路的机器人 | 难度：中等 | 标签：数组、动态规划、回溯、矩阵
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 * <p>
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * 说明：r 和 c 的值均不超过 100。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/robot-in-a-grid-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_02 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.2 MB , 在所有 Java 提交中击败了 41.84% 的用户
     * 通过测试用例： 46 / 46
     * <p>
     * 回溯就是更快…… 估计障碍都不多，所以比较快？
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) {
            return result;
        }
        findPath(obstacleGrid, 0, 0, result);
        return result;
    }

    boolean findPath(int[][] obstacleGrid, int x, int y, LinkedList<List<Integer>> result) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }
        if (x == r - 1 && y == c - 1) {
            result.addFirst(Arrays.asList(x, y));
            return true;
        }
        if (obstacleGrid[x][y] == 0) {
            obstacleGrid[x][y] = 2;
            if (findPath(obstacleGrid, x + 1, y, result)) {
                result.addFirst(Arrays.asList(x, y));
                return true;
            }
            if (findPath(obstacleGrid, x, y + 1, result)) {
                result.addFirst(Arrays.asList(x, y));
                return true;
            }
            obstacleGrid[x][y] = 3;
            return false;
        }
        return false;
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 14.29% 的用户
     * 内存消耗： 42.5 MB , 在所有 Java 提交中击败了 16.67% 的用户
     * 通过测试用例： 46 / 46
     * <p>
     * bfs 感觉效率不行啊…… 非得回溯 dfs 吗？
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles_bfs(int[][] obstacleGrid) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) {
            return result;
        }
        if (r == 1 && c == 1) {
            ArrayList<Integer> list0 = new ArrayList<>();
            list0.add(0);
            list0.add(0);
            result.add(list0);
            return result;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int pace = -1;
        obstacleGrid[0][0] = pace;
        queue.offer(0);
        loop:
        while (!queue.isEmpty()) {
            pace--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (poll % 1000 < r - 1 && obstacleGrid[poll % 1000 + 1][poll / 1000] == 0) {
                    obstacleGrid[poll % 1000 + 1][poll / 1000] = pace;
                    if (poll % 1000 + 1 == r - 1 && poll / 1000 == c - 1) {
                        break loop;
                    }
                    queue.offer(poll + 1);
                }
                if (poll / 1000 < c - 1 && obstacleGrid[poll % 1000][poll / 1000 + 1] == 0) {
                    obstacleGrid[poll % 1000][poll / 1000 + 1] = pace;
                    if (poll % 1000 == r - 1 && poll / 1000 + 1 == c - 1) {
                        break loop;
                    }
                    queue.offer(poll + 1000);
                }
            }
        }
        if (obstacleGrid[r - 1][c - 1] != pace) {
            return result;
        }
        int x = r - 1;
        int y = c - 1;
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(x);
        list1.add(y);
        result.add(list1);
        while (pace < -1) {
            ArrayList<Integer> list = new ArrayList<>();
            if (x > 0 && obstacleGrid[x - 1][y] == pace + 1) {
                list.add(--x);
                list.add(y);
            } else if (y > 0 && obstacleGrid[x][y - 1] == pace + 1) {
                list.add(x);
                list.add(--y);
            }
            result.addFirst(list);
            pace++;
        }
        return result;
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 11.68% 的用户
     * 内存消耗： 42.7 MB , 在所有 Java 提交中击败了 5.44% 的用户
     * 通过测试用例： 46 / 46
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles_twoDirection(int[][] obstacleGrid) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) {
            return result;
        }
        if (r == 1 && c == 1) {
            ArrayList<Integer> list0 = new ArrayList<>();
            list0.add(0);
            list0.add(0);
            result.add(list0);
            return result;
        }
        LinkedList<int[]> queue = new LinkedList<>();
        int pace = -1;
        obstacleGrid[0][0] = pace;
        queue.offer(new int[]{0, 0});
        loop:
        while (!queue.isEmpty()) {
            pace--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if (poll[0] < r - 1 && obstacleGrid[poll[0] + 1][poll[1]] == 0) {
                    obstacleGrid[poll[0] + 1][poll[1]] = pace;
                    if (poll[0] + 1 == r - 1 && poll[1] == c - 1) {
                        break loop;
                    }
                    queue.offer(new int[]{poll[0] + 1, poll[1]});
                }
                if (poll[1] < c - 1 && obstacleGrid[poll[0]][poll[1] + 1] == 0) {
                    obstacleGrid[poll[0]][poll[1] + 1] = pace;
                    if (poll[0] == r - 1 && poll[1] + 1 == c - 1) {
                        break loop;
                    }
                    queue.offer(new int[]{poll[0], poll[1] + 1});
                }
            }
        }
        if (obstacleGrid[r - 1][c - 1] != pace) {
            return result;
        }
        int x = r - 1;
        int y = c - 1;
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(x);
        list1.add(y);
        result.add(list1);
        while (pace < -1) {
            ArrayList<Integer> list = new ArrayList<>();
            if (x > 0 && obstacleGrid[x - 1][y] == pace + 1) {
                list.add(--x);
                list.add(y);
            } else if (y > 0 && obstacleGrid[x][y - 1] == pace + 1) {
                list.add(x);
                list.add(--y);
            }
            result.addFirst(list);
            pace++;
        }
        return result;
    }

    /**
     * 没注意题目只能向下和向右……
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles_fourDirection(int[][] obstacleGrid) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) {
            return result;
        }
        if (r == 1 && c == 1) {
            ArrayList<Integer> list0 = new ArrayList<>();
            list0.add(0);
            list0.add(0);
            result.add(list0);
            return result;
        }
        LinkedList<int[]> queue = new LinkedList<>();
        int pace = -1;
        obstacleGrid[0][0] = pace;
        queue.offer(new int[]{0, 0});
        loop:
        while (!queue.isEmpty()) {
            pace--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if (poll[0] > 0 && obstacleGrid[poll[0] - 1][poll[1]] == 0) {
                    obstacleGrid[poll[0] - 1][poll[1]] = pace;
                    queue.offer(new int[]{poll[0] - 1, poll[1]});
                }
                if (poll[0] < r - 1 && obstacleGrid[poll[0] + 1][poll[1]] == 0) {
                    obstacleGrid[poll[0] + 1][poll[1]] = pace;
                    if (poll[0] + 1 == r - 1 && poll[1] == c - 1) {
                        break loop;
                    }
                    queue.offer(new int[]{poll[0] + 1, poll[1]});
                }
                if (poll[1] > 0 && obstacleGrid[poll[0]][poll[1] - 1] == 0) {
                    obstacleGrid[poll[0]][poll[1] - 1] = pace;
                    queue.offer(new int[]{poll[0], poll[1] - 1});
                }
                if (poll[1] < c - 1 && obstacleGrid[poll[0]][poll[1] + 1] == 0) {
                    obstacleGrid[poll[0]][poll[1] + 1] = pace;
                    if (poll[0] == r - 1 && poll[1] + 1 == c - 1) {
                        break loop;
                    }
                    queue.offer(new int[]{poll[0], poll[1] + 1});
                }
            }
        }
        if (obstacleGrid[r - 1][c - 1] != pace) {
            return result;
        }
        int x = r - 1;
        int y = c - 1;
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(x);
        list1.add(y);
        result.add(list1);
        while (pace < -1) {
            ArrayList<Integer> list = new ArrayList<>();
            if (x > 0 && obstacleGrid[x - 1][y] == pace + 1) {
                list.add(--x);
                list.add(y);
            } else if (x < r - 1 && obstacleGrid[x + 1][y] == pace + 1) {
                list.add(++x);
                list.add(y);
            } else if (y > 0 && obstacleGrid[x][y - 1] == pace + 1) {
                list.add(x);
                list.add(--y);
            } else if (y < c - 1 && obstacleGrid[x][y + 1] == pace + 1) {
                list.add(x);
                list.add(++y);
            }
            result.addFirst(list);
            pace++;
        }
        return result;
    }
}
