package leetcode.from101to200;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/12/6 15:02
 * @Description: 118. 杨辉三角 | 难度：简单 | 标签：数组
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution118 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36 MB , 在所有 Java 提交中击败了 96.27% 的用户
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows < 1) {
            return result;
        }
        List<Integer> row = new ArrayList<>(1);
        row.add(1);
        result.add(row);
        if (numRows == 1) {
            return result;
        }
        int i = 2;
        while (i <= numRows) {
            row = new ArrayList<>(i);
            row.add(1);
            for (int j = 1; j < i - 1; j++) {
                row.add(result.get(i-2).get(j-1)+result.get(i-2).get(j));
            }
            row.add(1);
            result.add(row);
            i++;
        }
        return result;
    }

    @Test
    public void generateTest(){
        System.out.println(generate(5));
    }
}
