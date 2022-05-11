package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/11 19:31
 * @Description: 面试题 08.03. 魔术索引 | 难度：简单 | 标签：数组、二分查找
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
 * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
 * 若有多个魔术索引，返回索引值最小的一个。
 * <p>
 * 示例1:
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * <p>
 * 示例2:
 * 输入：nums = [1, 1, 1]
 * 输出：1
 * <p>
 * 说明:
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/magic-index-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution08_03 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 67.94% 的用户
     * 通过测试用例： 40 / 40
     *
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        return findMagicIndex(nums, 0, nums.length);
    }

    private int findMagicIndex(int[] nums, int from, int to) {
        if (from >= to) {
            return -1;
        }
        if (from == nums[from]) {
            return from;
        }
        if (from > nums[to - 1] || to - 1 < nums[from]) {
            return -1;
        }
        int mid = (from + 1 + to) / 2;
        int magicIndexLeft = findMagicIndex(nums, from + 1, mid);
        if (magicIndexLeft > -1) {
            return magicIndexLeft;
        }
        return findMagicIndex(nums, mid, to);

    }
}
