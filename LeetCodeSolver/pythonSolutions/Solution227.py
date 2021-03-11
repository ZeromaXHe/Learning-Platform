class Solution:
    """
    给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
    <p>
    整数除法仅保留整数部分。
    <p>
    示例 1：
    输入：s = "3+2*2"
    输出：7
    <p>
    示例 2：
    输入：s = " 3/2 "
    输出：1
    <p>
    示例 3：
    输入：s = " 3+5 / 2 "
    输出：5
    <p>
    提示：
    1 <= s.length <= 3 * 105
    s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
    s 表示一个 有效表达式
    表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
    题目数据保证答案是一个 32-bit 整数
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/basic-calculator-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def calculate(self, s: str) -> int:
        """
        执行用时： 128 ms , 在所有 Python3 提交中击败了 29.45% 的用户
        内存消耗： 16.6 MB , 在所有 Python3 提交中击败了 38.01% 的用户
        :param s:
        :return:
        """
        stack = []
        pre_sign = '+'
        num = 0
        n = len(s)
        for i in range(n):
            if s[i].isdigit():
                num = num * 10 + int(s[i])
            if (not s[i].isdigit()) and s[i] != ' ' or i == n - 1:
                if pre_sign == '+':
                    stack.append(num)
                elif pre_sign == '-':
                    stack.append(-num)
                elif pre_sign == '*':
                    stack.append(stack.pop() * num)
                else:
                    # python的除法有点坑，负数的//得出的结果是向下取整的结果。
                    # 和java的/还不是直接对应的
                    top = stack.pop()
                    if top < 0:
                        stack.append(int(top / num))
                    else:
                        stack.append(top // num)
                pre_sign = s[i]
                num = 0
        ans = 0
        while stack:
            ans += stack.pop()
        return ans

    def caculate_comment_ans(self, s: str) -> int:
        """
        题解区别人的答案，相比之下改变点：
        1.遍历使用了 enumerate(s)
        2.(not s[i].isdigit()) and s[i] != ' ' 简化成了 each in '+-*/'
        3.求和直接 sum(stack)

        执行用时： 92 ms , 在所有 Python3 提交中击败了 83.48% 的用户
        内存消耗： 16.5 MB , 在所有 Python3 提交中击败了 55.62% 的用户
        :param s:
        :return:
        """
        stack = []
        pre_op = '+'
        num = 0
        for i, each in enumerate(s):
            if each.isdigit():
                num = 10 * num + int(each)
            if i == len(s) - 1 or each in '+-*/':
                if pre_op == '+':
                    stack.append(num)
                elif pre_op == '-':
                    stack.append(-num)
                elif pre_op == '*':
                    stack.append(stack.pop() * num)
                elif pre_op == '/':
                    top = stack.pop()
                    if top < 0:
                        stack.append(int(top / num))
                    else:
                        stack.append(top // num)
                pre_op = each
                num = 0
        return sum(stack)


if __name__ == "__main__":
    print(Solution().calculate("3+2*2"))  # 7
    print(Solution().calculate("14-3/2"))  # 13

    print(-3 // 2)  # -2 （如果是java，System.out.println(-3/2);的结果为-1）
    print(int(-3 / 2))  # -1
