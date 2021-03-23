package leetcode.from301to400;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: zhuxi
 * @Time: 2021/3/23 9:43
 * @Description: 341.扁平化嵌套列表迭代器 | 难度：中等 | 标签：
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * <p>
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * <p>
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution341 {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return null if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     * <p>
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 51.02% 的用户
     * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 97.17% 的用户
     */
    class NestedIterator implements Iterator<Integer> {
        LinkedList<Iterator<NestedInteger>> stack = new LinkedList<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            // hasNext 中已经保证栈顶一定是整数
            // 其实这个实现已经不符合Iterator的规范了，只是按照题目的调用顺序来满足需求
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (!stack.peek().hasNext()) {
                    stack.pop();
                    continue;
                }
                // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                // 以下操作都是为了防止出现空队列的情况，只有当拿到一个真正的整数，才返回true
                NestedInteger nest = stack.peek().next();
                if (nest.isInteger()) {
                    List<NestedInteger> list = new ArrayList<>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }
    }

    /**
     * 过不了空队列的案例: [[]]
     */
    class NestedIterator_wrongAns implements Iterator<Integer> {
        LinkedList<Iterator<NestedInteger>> stack = new LinkedList<>();

        public NestedIterator_wrongAns(List<NestedInteger> nestedList) {
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            Iterator<Iterator<NestedInteger>> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Iterator<NestedInteger> nestedIntegerIterator = iterator.next();
                if (nestedIntegerIterator.hasNext()) {
                    return nextOfNestedInteger(nestedIntegerIterator);
                } else {
                    iterator.remove();
                }
            }
            throw new NoSuchElementException();
        }

        private Integer nextOfNestedInteger(Iterator<NestedInteger> nestedIntegerIterator) {
            if (!nestedIntegerIterator.hasNext()) {
                throw new NoSuchElementException();
            }
            NestedInteger next = nestedIntegerIterator.next();
            if (next.isInteger()) {
                return next.getInteger();
            } else {
                Iterator<NestedInteger> listIterator = next.getList().iterator();
                stack.push(listIterator);
                return nextOfNestedInteger(listIterator);
            }
        }

        @Override
        public boolean hasNext() {
            Iterator<Iterator<NestedInteger>> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Iterator<NestedInteger> nestedIntegerIterator = iterator.next();
                if (nestedIntegerIterator.hasNext()) {
                    // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                    // 这一步参考了题解
                    NestedInteger nest = nestedIntegerIterator.next();
                    if (nest.isInteger()) {
                        List<NestedInteger> list = new ArrayList<>();
                        list.add(nest);
                        stack.push(list.iterator());
                        return true;
                    }
                    stack.push(nest.getList().iterator());
                } else {
                    iterator.remove();
                }
            }
            return false;
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
