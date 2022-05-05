package leetcode.chengxuyuan_mianshi_jingdian;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 11:25
 * @Description: 面试题 03.01. 三合一 | 难度：简单 | 标签：栈、设计、数组
 * 三合一。描述如何只用一个数组来实现三个栈。
 * <p>
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 * <p>
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 * <p>
 * 示例1:
 * 输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 * 输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 * <p>
 * 示例2:
 * 输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 * 输出：
 * [null, null, null, null, 2, 1, -1, -1]
 * <p>
 * 提示：
 * 0 <= stackNum <= 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/three-in-one-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution03_01 {
    /**
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 50.3 MB , 在所有 Java 提交中击败了 90.94% 的用户
     * 通过测试用例： 30 / 30
     * <p>
     * 懒得写成一维数组了，浪费时间。二维数组也可以理解成一个数组吧~
     * 不过必须吐槽这道题目真的粪……
     */
    class TripleInOne {
        private int[][] stack;

        public TripleInOne(int stackSize) {
            stack = new int[3][stackSize + 1];
            stack[0][0] = 1;
            stack[1][0] = 1;
            stack[2][0] = 1;
        }

        public void push(int stackNum, int value) {
            if (stack[stackNum][0] != stack[stackNum].length) {
                stack[stackNum][stack[stackNum][0]++] = value;
            }
        }

        public int pop(int stackNum) {
            if (stack[stackNum][0] != 1) {
                return stack[stackNum][--stack[stackNum][0]];
            } else {
                return -1;
            }
        }

        public int peek(int stackNum) {
            if (stack[stackNum][0] != 1) {
                return stack[stackNum][stack[stackNum][0] - 1];
            } else {
                return -1;
            }
        }

        public boolean isEmpty(int stackNum) {
            return stack[stackNum][0] == 1;
        }
    }

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
}
