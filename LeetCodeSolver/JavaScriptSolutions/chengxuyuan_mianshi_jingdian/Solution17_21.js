/**
 * 面试题17.21 直方图的水量 | 难度：困难 | 标签：栈、数组、双指针
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 84 ms , 在所有 JavaScript 提交中击败了 86.29% 的用户
 * 内存消耗： 39.4 MB , 在所有 JavaScript 提交中击败了 62.90% 的用户
 *
 * @param {number[]} height
 * @return {number}
 */
var trap = function (height) {
    let l = 0;
    let r = height.length - 1;
    let result = 0;
    let maxL = 0;
    let maxR = 0;
    while (l <= r) {
        if (maxL < maxR) {
            result += Math.max(0, maxL - height[l]);
            maxL = Math.max(maxL, height[l]);
            l++;
        } else {
            result += Math.max(0, maxR - height[r]);
            maxR = Math.max(maxR, height[r]);
            r--;
        }
    }
    return result;
};