package leetcode.from401to500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/16 18:54
 * @Description: 406. 根据身高重建队列 | 难度：中等 | 标签：贪心算法
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * <p>
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution406 {
    /**
     * 执行用时： 18 ms, 在所有 Java 提交中击败了 12.03% 的用户
     * 内存消耗： 39.5 MB, 在所有 Java 提交中击败了 78.61% 的用户
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) {
            return people;
        }
        // reversed每次都会把之前动作排出的所有顺序都颠倒。
        // 所以要使得第一次正序，第二次倒序的话，不是.compare.thenCompare.reversed,而是如下
        Arrays.sort(people,
                Comparator.comparingInt((int[] i) -> i[0])
                        .reversed()
                        .thenComparingInt((int[] i) -> i[1])
                        .reversed());
//        System.out.println(print2DArr(people));
        int[][] result = new int[people.length][2];
        int[] toBePlaced = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            toBePlaced[i] = i;
        }
        for (int i = 0; i < people.length; i++) {
            result[toBePlaced[people[i][1]]] = people[i];
            for (int j = people[i][1]; j < people.length - i - 1; j++) {
                toBePlaced[j] = toBePlaced[j + 1];
            }
//            System.out.println("toBePlaced: " + Arrays.toString(toBePlaced));
//            System.out.println("people: "+print2DArr(result));
        }
        return result;
    }

    public static void main(String[] args) {
        Solution406 solution406 = new Solution406();
        // [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
        System.out.println(print2DArr(solution406.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
    }

    private static String print2DArr(int[][] arr2D) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr2D.length; i++) {
            if (i != 0) sb.append(',');
            sb.append('[');
            for (int j = 0; j < arr2D[0].length; j++) {
                if (j != 0) sb.append(',');
                sb.append(arr2D[i][j]);
            }
            sb.append(']');
        }
        sb.append(']');
        return sb.toString();
    }
}
