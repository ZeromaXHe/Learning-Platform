package leetcode.from1051to1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhuxi
 * @apiNote 1089. 复写零 | 难度：简单 | 标签：数组、双指针
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * <p>
 * 注意：请不要在超过该数组长度的位置写入元素。
 * <p>
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 * <p>
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * <p>
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 * <p>
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/6/17 15:32
 */
public class Solution1089 {
    @Test
    public void duplicateZerosTest() {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        duplicateZeros(arr);
        Assert.assertArrayEquals(new int[]{1, 0, 0, 2, 3, 0, 0, 4}, arr);
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.21% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 17.69% 的用户
     * 通过测试用例： 30 / 30
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int pre = 0;
        int end = 0;
        while (end < arr.length) {
            if (arr[pre] == 0) {
                end += 2;
            } else {
                end++;
            }
            pre++;
        }
        if (pre == arr.length) {
            return;
        }
        if (end == arr.length) {
            end = arr.length - 1;
            pre--;
        } else {
            end = arr.length - 2;
            arr[arr.length - 1] = 0;
            pre -= 2;
        }
        while (pre >= 0) {
            if (arr[pre] == 0) {
                arr[end] = arr[end - 1] = 0;
                end -= 2;
            } else {
                arr[end--] = arr[pre];
            }
            pre--;
        }
    }
}
