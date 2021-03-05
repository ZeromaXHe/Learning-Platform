"""
232.用栈实现队列 | 难度：简单 | 标签：栈、设计
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：
void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false

说明：
你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。

进阶：
你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。

示例：
输入：
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 1, 1, false]

解释：
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false

提示：
1 <= x <= 9
最多调用 100 次 push、pop、peek 和 empty
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""

"""
执行用时： 36 ms , 在所有 Python3 提交中击败了 82.29% 的用户
内存消耗： 15.1 MB , 在所有 Python3 提交中击败了 19.37% 的用户

按照注释内容优化后：
执行用时： 32 ms , 在所有 Python3 提交中击败了 94.17% 的用户
内存消耗： 14.9 MB , 在所有 Python3 提交中击败了 65.30% 的用户
"""


class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.stackIn = []
        self.stackOut = []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.stackIn.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        # 这里替换成“if not self.stackOut”更好
        # 在Python中，False,0,'',[],{},()都可以视为假
        # 如果使用len，也可以省略后面的“== 0”
        if len(self.stackOut) == 0:
            while len(self.stackIn) > 0:
                self.stackOut.append(self.stackIn.pop())
        return self.stackOut.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        # 这里替换成“if not self.stackOut”更好
        # 在Python中，False,0,'',[],{},()都可以视为假
        # 如果使用len，也可以省略后面的“== 0”
        if len(self.stackOut) == 0:
            while len(self.stackIn) > 0:
                self.stackOut.append(self.stackIn.pop())
        # 应该省略成 “return self.stackOut[-1]”
        return self.stackOut[len(self.stackOut) - 1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        # 同理，省略成 “return not self.stackIn and not self.stackOut”
        return (len(self.stackIn) == 0) & (len(self.stackOut) == 0)

# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
