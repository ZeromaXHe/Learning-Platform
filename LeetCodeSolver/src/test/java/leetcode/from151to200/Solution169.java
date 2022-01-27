package leetcode.from151to200;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 20:49
 * @Description: 169. 多数元素 | 难度：简单 | 标签：数组、哈希表、分治、计数、排序
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution169 {
    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.92% 的用户
     * 内存消耗： 44.4 MB , 在所有 Java 提交中击败了 24.15% 的用户
     * 通过测试用例： 47 / 47
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int test = 0;
        for (int num : nums) {
            if (num == test) {
                count++;
            } else if (count == 0) {
                test = num;
                count = 1;
            } else {
                count--;
            }
        }
        return test;
    }
}
