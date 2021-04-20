/**
 * 28.实现strStr() | 难度：简单 | 标签：双指针、字符串
 * 实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * <p>
 * 说明：
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：haystack = "", needle = ""
 * 输出：0
 * <p>
 * 提示：
 * 0 <= haystack.length, needle.length <= 5 * 10^4
 * haystack 和 needle 仅由小写英文字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时： 100 ms , 在所有 JavaScript 提交中击败了 29.97% 的用户
 * 内存消耗： 38.2 MB , 在所有 JavaScript 提交中击败了 62.96% 的用户
 *
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function (haystack, needle) {
    const haystackLen = haystack.length;
    const needleLen = needle.length;
    if (haystackLen < needleLen) {
        return -1;
    }
    if (needle.length === 0) {
        return 0;
    }
    for (let i = 0; i < haystackLen - needleLen + 1; i++) {
        let loopSuccess = true;
        for (let j = 0; j < needleLen; j++) {
            if (haystack.charAt(i + j) !== needle.charAt(j)) {
                loopSuccess = false;
                break;
            }
        }
        if (loopSuccess) {
            return i;
        }
    }
    return -1;
};