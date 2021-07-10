package Functional_Programming_in_Java.chapter10;

import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/7/10 17:37
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class TreeTest {
    @Test
    public void test10_5_3_practice10_10() {
        Tree<Integer> tree = Tree.empty();
        tree.foldInOrder(Tree.<Integer>empty(), t1 -> i -> t2 -> Tree.tree(t1, i, t2));
        tree.foldPostOrder(Tree.<Integer>empty(), t1 -> t2 -> i -> Tree.tree(t1, i, t2));
        tree.foldPreOrder(Tree.<Integer>empty(), i -> t1 -> t2 -> Tree.tree(t1, i, t2));
    }
}
