package leetcode.jianzhi_offer;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer 11. 旋转数组的最小数字 | 难度：简单 | 标签：数组、二分查找
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。  
 * <p>
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 示例 1：
 * 输入：numbers = [3,4,5,1,2]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：numbers = [2,2,2,0,1]
 * 输出：0
 * <p>
 * 提示：
 * n == numbers.length
 * 1 <= n <= 5000
 * -5000 <= numbers[i] <= 5000
 * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/7 15:05
 */
public class SolutionOffer11 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了 16.08% 的用户
     * 通过测试用例：192 / 192
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] < numbers[r]) {
                r = mid;
            } else if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return numbers[l];
    }
}
