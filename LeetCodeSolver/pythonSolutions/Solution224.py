"""
224.基本计算器 | 难度：困难 | 标签：栈、数学
实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。

示例 1：
输入：s = "1 + 1"
输出：2

示例 2：
输入：s = " 2-1 + 2 "
输出：3

示例 3：
输入：s = "(1+(4+5+2)-3)+(6+8)"
输出：23

提示：
1 <= s.length <= 3 * 105
s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
s 表示一个有效的表达式

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/basic-calculator
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""


class Solution:
    def calculate(self, s: str) -> int:
        """
        执行用时： 132 ms , 在所有 Python3 提交中击败了 34.40% 的用户
        内存消耗： 15.2 MB , 在所有 Python3 提交中击败了 54.44% 的用户
        :param s:
        :return:
        """
        ops = []
        ops.append(1)
        sign = 1
        ret = 0
        n = len(s)
        i = 0
        while i < n:
            if s[i] == ' ':
                i += 1
            elif s[i] == '+':
                sign = ops[-1]
                i += 1
            elif s[i] == '-':
                sign = -ops[-1]
                i += 1
            elif s[i] == '(':
                ops.append(sign)
                i += 1
            elif s[i] == ')':
                ops.pop()
                i += 1
            else:
                num = 0
                while i < n and s[i].isdigit():
                    num = num * 10 + int(s[i])
                    i += 1
                ret += sign * num
        return ret
