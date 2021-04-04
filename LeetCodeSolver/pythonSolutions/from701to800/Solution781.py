from typing import List, Counter


class Solution:
    """
    森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
    <p>
    返回森林中兔子的最少数量。
    <p>
    示例:
    输入: answers = [1, 1, 2]
    输出: 5
    解释:
    两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
    之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
    设回答了 "2" 的兔子为蓝色。
    此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
    因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
    <p>
    输入: answers = [10, 10, 10]
    输出: 11
    <p>
    输入: answers = []
    输出: 0
    <p>
    说明:
    answers 的长度最大为1000。
    answers[i] 是在 [0, 999] 范围内的整数。
    <p>
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/rabbits-in-forest
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """

    def numRabbits(self, answers: List[int]) -> int:
        """
        执行用时： 56 ms , 在所有 Python3 提交中击败了 48.62% 的用户
        内存消耗： 14.7 MB , 在所有 Python3 提交中击败了 93.92% 的用户
        :param answers:
        :return:
        """
        dic = {}
        for answer in answers:
            if answer in dic:
                dic[answer] += 1
            else:
                dic[answer] = 1
        num = 0
        for key, value in dic.items():
            num += (key + 1) * (1 + (value - 1) // (key + 1))
        return num
