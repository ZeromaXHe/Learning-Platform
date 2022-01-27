package leetcode.from1001to1050;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/1/14 9:54
 * @Description: 1018. 可被 5 整除的二进制前缀 | 难度：简单 | 标签：数组
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * <p>
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * <p>
 * 示例 1：
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * <p>
 * 示例 2：
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * <p>
 * 示例 3：
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * <p>
 * 示例 4：
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 * <p>
 * 提示：
 * 1 <= A.length <= 30000
 * A[i] 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution1018 {
    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 92.76% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 5.11% 的用户
     *
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>(A.length);
        int num = 0;
        for (int i : A) {
            num <<= 1;
            num += i;
            num %= 5;
            list.add(num == 0);
        }
        return list;
    }

    @Test
    public void prefixesDivBy5Test() {
        // [true,false,false,false,true,false]
        System.out.println(prefixesDivBy5(new int[]{0, 1, 1, 1, 1, 1}));
        System.out.println("============================================");
        System.out.println("[false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, true, true, true, false]");
        System.out.println(prefixesDivBy5(new int[]{1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1}));
    }
}
