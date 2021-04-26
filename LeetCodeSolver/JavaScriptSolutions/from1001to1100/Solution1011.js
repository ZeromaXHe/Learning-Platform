/**
 * 1011.在 D 天内送达包裹的能力 | 难度：中等 | 标签：数组、二分查找
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 示例 2：
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * 示例 3：
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 * <p>
 * 提示：
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 104 ms , 在所有 JavaScript 提交中击败了 83.13% 的用户
 * 内存消耗： 41.6 MB , 在所有 JavaScript 提交中击败了 57.50% 的用户
 *
 * @param {number[]} weights
 * @param {number} D
 * @return {number}
 */
var shipWithinDays = function (weights, D) {
    // js 最大值
    let left = Math.max(...weights);
    // js 求和
    let right = weights.reduce((a, b) => a + b);
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        let carry = 0;
        let day = 1;
        // for...in和for...of的区别
        // for...in是ES5的标准，该方法遍历的是对象的属性名称(key：键名)。
        // 一个Array对象也是一个对象，数组中的每个元素的索引被视为属性名称，所以在使用for...in遍历Array时，拿到的是每个元素索引
        //
        // for...of是ES6的标准，该方法遍历的是对象的属性所对应的值(value：键值)。
        // 所以它用来遍历数组时得到每个元素的值
        for (const weight of weights) {
            if (carry + weight > mid) {
                ++day;
                carry = 0;
            }
            carry += weight;
        }
        if (day <= D) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};