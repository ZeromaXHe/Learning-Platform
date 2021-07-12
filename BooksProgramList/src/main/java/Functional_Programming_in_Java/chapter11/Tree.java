package Functional_Programming_in_Java.chapter11;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/12 15:22
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Tree<A extends Comparable<A>> {
    private static Tree E = new E();
    private static Color R = new Red();
    private static Color B = new Black();

    protected abstract boolean isE();

    protected abstract boolean isT();

    protected abstract boolean isB();

    protected abstract boolean isR();

    protected abstract boolean isTB();

    protected abstract boolean isTR();

    public abstract boolean isEmpty();

    protected abstract Tree<A> right();

    protected abstract Tree<A> left();

    protected abstract A value();

//    public abstract int size();
//
//    public abstract int height();

    public abstract boolean member(A a);

    public abstract A max();

    public abstract A min();

    public abstract Result<A> get(A value);

    public abstract <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g);

    public abstract <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g);

    public abstract <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f);

    public abstract <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f);

    public abstract <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f);

    /**
     * 11.1.2 往红黑树中插入元素 - 练习11.1 编写insert、balance和blacken方法来实现红黑树的插入。
     * 不幸的是，Java没有实现模式匹配，所以你不得不使用条件指令来代替。
     *
     * @param value
     * @return
     */
    protected abstract Tree<A> ins(A value);

    /**
     * 11.2.2 扩展map - 练习11.3 在Map类中编写一个values方法，按键的升序返回map中包含的值列表。
     *
     * @param identity
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> B foldInReverseOrder(B identity, Function<B, Function<A, Function<B, B>>> f);

    private static class E<A extends Comparable<A>> extends Tree<A> {

        @Override
        protected boolean isE() {
            return true;
        }

        @Override
        protected boolean isT() {
            return false;
        }

        @Override
        protected boolean isB() {
            return true;
        }

        @Override
        protected boolean isR() {
            return false;
        }

        @Override
        protected boolean isTB() {
            return false;
        }

        @Override
        protected boolean isTR() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        protected Tree<A> right() {
            return E;
        }

        @Override
        protected Tree<A> left() {
            return E;
        }

        @Override
        protected A value() {
            throw new IllegalStateException("value called on Empty");
        }

//        @Override
//        public int size() {
//            return 0;
//        }
//
//        @Override
//        public int height() {
//            return -1;
//        }

        @Override
        public boolean member(A a) {
            return false;
        }

        @Override
        public A max() {
            throw new IllegalStateException("max called on Empty");
        }

        @Override
        public A min() {
            throw new IllegalStateException("min called on Empty");
        }

        @Override
        public Result<A> get(A value) {
            return Result.empty();
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g) {
            return identity;
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g) {
            return identity;
        }

        @Override
        public <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f) {
            return identity;
        }

        @Override
        protected Tree<A> ins(A value) {
            return new T<>(R, empty(), value, empty());
        }

        @Override
        public <B> B foldInReverseOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public String toString() {
            return "E";
        }
    }

    private static class T<A extends Comparable<A>> extends Tree<A> {
        private final Tree<A> left;
        private final Tree<A> right;
        private final A value;
        private final Color color;
//        private final int length;
//        private final int height;

        public T(Color color, Tree<A> left, A value, Tree<A> right) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.color = color;
        }

        @Override
        protected boolean isE() {
            return false;
        }

        @Override
        protected boolean isT() {
            return true;
        }

        @Override
        protected boolean isB() {
            return this.color.isB();
        }

        @Override
        protected boolean isR() {
            return this.color.isR();
        }

        @Override
        protected boolean isTB() {
            return this.color.isB();
        }

        @Override
        protected boolean isTR() {
            return this.color.isR();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        protected Tree<A> right() {
            return right;
        }

        @Override
        protected Tree<A> left() {
            return left;
        }

        @Override
        protected A value() {
            return value;
        }

//        @Override
//        public int size() {
//            return length;
//        }
//
//        @Override
//        public int height() {
//            return height;
//        }

        @Override
        public boolean member(A value) {
            return value.compareTo(this.value) < 0 ?
                    left.member(value) :
                    value.compareTo(this.value) == 0 || right.member(value);
        }

        @Override
        public A max() {
            return right.isEmpty()
                    ? value
                    : right.max();
        }

        @Override
        public A min() {
            return left.isEmpty()
                    ? value
                    : left.min();
        }

        @Override
        public Result<A> get(A value) {
            return value.compareTo(this.value) < 0
                    ? left.get(value)
                    : value.compareTo(this.value) > 0
                    ? right.get(value)
                    : Result.success(this.value);
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g) {
            return g.apply(right.foldLeft(identity, f, g))
                    .apply(f.apply(left.foldLeft(identity, f, g)).apply(this.value));
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g) {
            return g.apply(f.apply(this.value).apply(left.foldRight(identity, f, g)))
                    .apply(right.foldRight(identity, f, g));
        }

        @Override
        public <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return f.apply(left.foldInOrder(identity, f))
                    .apply(value)
                    .apply(right.foldInOrder(identity, f));
        }

        @Override
        public <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f) {
            return f.apply(value)
                    .apply(left.foldPreOrder(identity, f))
                    .apply(right.foldPreOrder(identity, f));
        }

        @Override
        public <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f) {
            return f.apply(left.foldPostOrder(identity, f))
                    .apply(right.foldPostOrder(identity, f))
                    .apply(value);
        }

        @Override
        protected Tree<A> ins(A value) {
            return value.compareTo(this.value) < 0 ?
                    balance(this.color, this.left.ins(value), this.value, this.right) :
                    value.compareTo(this.value) > 0 ?
                            balance(this.color, this.left, this.value, this.right.ins(value)) :
                            this;
        }

        @Override
        public <B> B foldInReverseOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return f.apply(right.foldInReverseOrder(identity, f))
                    .apply(value)
                    .apply(left.foldInReverseOrder(identity, f));
        }

        @Override
        public String toString() {
            return String.format("(T %s %s %s %s)", color, left, value, right);
        }
    }

    private static abstract class Color {

        abstract boolean isR();

        abstract boolean isB();
    }

    private static class Red extends Color {
        @Override
        boolean isR() {
            return true;
        }

        @Override
        boolean isB() {
            return false;
        }

        @Override
        public String toString() {
            return "R";
        }
    }

    private static class Black extends Color {
        @Override
        boolean isR() {
            return false;
        }

        @Override
        boolean isB() {
            return true;
        }

        @Override
        public String toString() {
            return "B";
        }
    }

    public static <A extends Comparable<A>> Tree<A> empty() {
        return E;
    }

    /**
     * 11.1.2 往红黑树中插入元素 - 练习11.1 编写insert、balance和blacken方法来实现红黑树的插入。
     * 不幸的是，Java没有实现模式匹配，所以你不得不使用条件指令来代替。
     *
     * @param value
     * @return
     */
    Tree<A> balance(Color color, Tree<A> left, A value, Tree<A> right) {
        if (color.isB() && left.isTR() && left.left().isTR()) {
            return new T<>(
                    R,
                    new T<>(B, left.left().left(), left.left().value(), left.left().right()),
                    left.value(),
                    new T<>(B, left.right(), value, right)
            );
        }
        if (color.isB() && left.isTR() && left.right().isTR()) {
            return new T<>(
                    R,
                    new T<>(B, left.left(), left.value(), left.right().left()),
                    left.right().value(),
                    new T<>(B, left.right().right(), value, right)
            );
        }
        if (color.isB() && right.isTR() && right.left().isTR()) {
            return new T<>(
                    R,
                    new T<>(B, left, value, right.left().left()),
                    right.left().value(),
                    new T<>(B, right.left().right(), right.value(), right.right())
            );
        }
        if (color.isB() && right.isTR() && right.right().isTR()) {
            return new T<>(
                    R,
                    new T<>(B, left, value, right.left()),
                    right.value(),
                    new T<>(B, right.right().left(), right.right().value(), right.right().right())
            );
        }
        return new T<>(color, left, value, right);
    }

    protected static <A extends Comparable<A>> Tree<A> blacken(Tree<A> t) {
        return t.isEmpty() ?
                empty() :
                new T<>(B, t.left(), t.value(), t.right());
    }

    public Tree<A> insert(A value) {
        return blacken(ins(value));
    }
}
