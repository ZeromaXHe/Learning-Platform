class Solution:
    """
    28.实现strStr() | 难度：简单 | 标签：双指针、字符串
    实现 strStr() 函数。
    <p>
    给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
    <p>
    说明：
    当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
    <p>
    示例 1：
    输入：haystack = "hello", needle = "ll"
    输出：2
    <p>
    示例 2：
    输入：haystack = "aaaaa", needle = "bba"
    输出：-1
    <p>
    示例 3：
    输入：haystack = "", needle = ""
    输出：0
    <p>
    提示：
    0 <= haystack.length, needle.length <= 5 * 10^4
    haystack 和 needle 仅由小写英文字符组成
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/implement-strstr
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def strStr(self, haystack: str, needle: str) -> int:
        """
        执行用时： 32 ms , 在所有 Python3 提交中击败了 96.67% 的用户
        内存消耗： 15.1 MB , 在所有 Python3 提交中击败了 49.73% 的用户
        :param haystack:
        :param needle:
        :return:
        """
        haystack_len = len(haystack)
        needle_len = len(needle)
        if haystack_len < needle_len:
            return -1
        if len(needle) == 0:
            return 0
        for i in range(0, haystack_len - needle_len + 1):
            loop_success = True
            for j in range(0, needle_len):
                if haystack[i + j] != needle[j]:
                    loop_success = False
                    break
            if loop_success:
                return i
        return -1
