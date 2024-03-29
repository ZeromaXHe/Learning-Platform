package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2022/5/12 16:37
 * @Description: 面试题 08.06. 汉诺塔问题 | 难度：简单 | 标签：递归、数组
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 * <p>
 * 示例1:
 * 输入：A = [2, 1, 0], B = [], C = []
 * 输出：C = [2, 1, 0]
 * <p>
 * 示例2:
 * 输入：A = [1, 0], B = [], C = []
 * 输出：C = [1, 0]
 * <p>
 * 提示:
 * A中盘子的数目不大于14个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/hanota-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_06 {
    @Test
    public void hanotaTest() {
        LinkedList<Integer> a = new LinkedList<>();
        a.add(2);
        a.add(1);
        a.add(0);
        LinkedList<Integer> b = new LinkedList<>();
        LinkedList<Integer> c = new LinkedList<>();
        hanota(a, b, c);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 56.90% 的用户
     * 通过测试用例： 14 / 14
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A, A.size(), C, B);
    }

    private void move(List<Integer> from, int count, List<Integer> to, List<Integer> temp) {
        if (count == 1) {
            to.add(from.remove(from.size() - 1));
            return;
        }
        move(from, count - 1, temp, to);
        to.add(from.remove(from.size() - 1));
        move(temp, count - 1, to, from);
    }
}
