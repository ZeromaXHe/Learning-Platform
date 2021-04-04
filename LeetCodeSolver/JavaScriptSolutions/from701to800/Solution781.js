/**
 * 781.森林中的兔子 | 难度：中等 | 标签：哈希表、数学
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * <p>
 * 返回森林中兔子的最少数量。
 * <p>
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 * <p>
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 * <p>
 * 输入: answers = []
 * 输出: 0
 * <p>
 * 说明:
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 104 ms , 在所有 JavaScript 提交中击败了 16.33% 的用户
 * 内存消耗： 40.5 MB , 在所有 JavaScript 提交中击败了 8.16% 的用户
 *
 * @param {number[]} answers
 * @return {number}
 */
var numRabbits = function (answers) {
    // js 字典
    let map = new Map();
    // js 遍历数组
    for (const answer of answers) {
        map.set(answer, (map.get(answer) || 0) + 1);
    }
    let num = 0;
    // js 遍历字典entry
    for (const [key, value] of map.entries()) {
        num += (key + 1) * (1 + Math.floor((value - 1) / (key + 1)));
    }
    return num;
};