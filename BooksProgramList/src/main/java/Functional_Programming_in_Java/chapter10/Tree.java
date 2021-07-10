package Functional_Programming_in_Java.chapter10;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter4.TailCall;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/9 20:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Tree<A extends Comparable<A>> {
    @SuppressWarnings("rawtypes")
    private static Tree EMPTY = new Empty();

    public abstract A value();

    abstract Tree<A> left();

    abstract Tree<A> right();

    /**
     * 10.2 实现二叉搜索树 - 练习10.1 定义一个insert方法以将值插入树中。
     * 作为方法名称的insert并不是很好的选择，因为不应该真的插入什么东西。
     * 与往常一样，Tree结构是不可变和持久化的，所以需要构造一个带有插入值的树，并使原来的树保持不变。
     * 但是通常还是将该方法称为insert，因为它与传统编程中的插入功能相同。
     * 如果该值等于根，则你必须返回一棵新树，以插入的值为根并保持原来的两个分支不变。
     * 否则，在做分支中插入小于根的值，在右分支插入大于根的值。
     *
     * @param a
     * @return
     */
    public abstract Tree<A> insert(A a);

    /**
     * 10.2 实现二叉搜索树 - 练习10.2 一个经常用于树的操作是检查树中是否存在特定元素。实现一个执行此检查的member方法。
     *
     * @param a
     * @return
     */
    public abstract boolean member(A a);

    /**
     * 10.2 实现二叉搜索树 - 练习10.4 编写计算树的size和height的方法。
     *
     * @return
     */
    public abstract int size();

    public abstract int height();

    /**
     * 10.2 实现二叉搜索树 - 练习10.5 编写max和min方法来计算树中包含的最大值和最小值。
     *
     * @return
     */
    public abstract Result<A> max();

    public abstract Result<A> min();

    /**
     * 10.3 从树中删除元素 - 练习10.6 编写一个从树中删除元素的remove方法。该方法以一个元素为参数。
     * 如果树中存在该元素则会将其删除，并返回一棵没有该元素的新树。
     * 当然，这棵新树将遵守左分支上所有元素都小于根并且右分支上的所有元素都大于根的要求。
     * 如果该元素并不在树中，则方法将返回未变化的树。
     *
     * @return
     */
    public abstract Tree<A> remove(A a);

    public abstract Tree<A> removeMerge(Tree<A> ta);

    public abstract boolean isEmpty();

    /**
     * 10.4 合并任意树 - 练习10.7（难） 到目前为止，你只合并了一棵树中所有元素都大于另一棵树中所有元素的树。
     * 编写一个合并任意树的merge方法。
     *
     * @param a
     * @return
     */
    public abstract Tree<A> merge(Tree<A> a);

    /**
     * 10.5.1 用两个函数折叠 - 练习10.8 给定刚才描述的两个函数，编写一个折叠树的foldLeft方法。
     *
     * @param identity
     * @param f
     * @param g
     * @param <B>
     * @return
     */
    public abstract <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g);

    public abstract <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g);

    /**
     * 10.5.2 用一个函数折叠 - 练习10.9 编写三个折叠树的方法：foldInOrder、foldPreOrder和foldPostOrder。
     *
     * @param identity
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f);

    public abstract <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f);

    public abstract <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f);

    /**
     * 10.6 映射树 - 练习10.11 定义一个树的map方法。尽量保留树的结构。
     * 例如，通过对值求平方来映射整型树可能会生成具有不同结构的树，但是通过加上常量来映射就不会。
     *
     * @param f
     * @param <B>
     * @return
     */
    public abstract <B extends Comparable<B>> Tree<B> map(Function<A, B> f);

    /**
     * 10.7.1 旋转树 - 练习10.12 编写rotateRight和rotateLeft方法以在两个方向上旋转树。小心维持分支顺序。
     * 左元素必须总是小于根，而右元素必须总是大于根。
     *
     * @return
     */
    protected abstract Tree<A> rotateLeft();

    protected abstract Tree<A> rotateRight();

    /**
     * 10.7.1 旋转树 - 练习10.13 为了平衡树，你还需要将树转换为有序列表的方法。编写一个方法将树改变为从右到左的已排序列表（就是降序）。
     *
     * @return
     */
    public abstract List<A> toListInOrderRight();

    private static class Empty<A extends Comparable<A>> extends Tree<A> {

        @Override
        public A value() {
            throw new IllegalStateException("value() called on empty");
        }

        @Override
        Tree<A> left() {
            throw new IllegalStateException("left() called on empty");
        }

        @Override
        Tree<A> right() {
            throw new IllegalStateException("right() called on empty");
        }

        @Override
        public Tree<A> insert(A a) {
            return new T<>(empty(), a, empty());
        }

        @Override
        public boolean member(A a) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public int height() {
            return -1;
        }

        @Override
        public Result<A> max() {
            return Result.empty();
        }

        @Override
        public Result<A> min() {
            return Result.empty();
        }

        @Override
        public Tree<A> remove(A a) {
            return this;
        }

        @Override
        public Tree<A> removeMerge(Tree<A> ta) {
            return ta;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Tree<A> merge(Tree<A> a) {
            return a;
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
        public <B extends Comparable<B>> Tree<B> map(Function<A, B> f) {
            return empty();
        }

        @Override
        protected Tree<A> rotateLeft() {
            return this;
        }

        @Override
        protected Tree<A> rotateRight() {
            return this;
        }

        @Override
        public List<A> toListInOrderRight() {
            return List.list();
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

        public T(Tree<A> left, A value, Tree<A> right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        @Override
        public A value() {
            return value;
        }

        @Override
        Tree<A> left() {
            return left;
        }

        @Override
        Tree<A> right() {
            return right;
        }

        /**
         * 10.7.3 自动平衡树 - 练习10.15 转换你已开发的树，使其在插入、合并和删除时自动平衡。
         *
         * @param a
         * @return
         */
        @Override
        public Tree<A> insert(A a) {
            // 这样做适用于小的树(实际上，不需要平衡),但是对于大的树来说，这样不行，因为它太慢了。有一个方案是并不总平衡树。
            // 例如，只有当高度达到完全平衡树理想高度的 20 倍时，才运行平衡方法
            Tree<A> t = ins(a);
            return t.height() > log2nlz(t.size()) * 20 ? balance(t) : t;
        }

        protected Tree<A> ins(A a) {
            return a.compareTo(this.value) < 0 ?
                    new T<>(left.insert(a), this.value, right) :
                    a.compareTo(this.value) > 0 ?
                            new T<>(left, this.value, right.insert(a)) :
                            new T<>(this.left, a, this.right);
        }

        @Override
        public boolean member(A value) {
            return value.compareTo(this.value) < 0 ?
                    left.member(value) :
                    value.compareTo(this.value) == 0 || right.member(value);
        }

        @Override
        public int size() {
            return 1 + left.size() + right.size();
        }

        @Override
        public int height() {
            return 1 + Math.max(left.height(), right.height());
        }

        @Override
        public Result<A> max() {
            return right.max().orElse(() -> Result.success(value));
        }

        @Override
        public Result<A> min() {
            return left.min().orElse(() -> Result.success(value));
        }

        @Override
        public Tree<A> remove(A a) {
            if (a.compareTo(this.value) < 0) {
                return new T<>(left.remove(a), value, right);
            } else if (a.compareTo(this.value) > 0) {
                return new T<>(left, value, right.remove(a));
            } else {
                return left.removeMerge(right);
            }
        }

        @Override
        public Tree<A> removeMerge(Tree<A> ta) {
            if (ta.isEmpty()) {
                return this;
            }
            if (ta.value().compareTo(value) < 0) {
                return new T<>(left.removeMerge(ta), value, right);
            } else if (ta.value().compareTo(value) > 0) {
                return new T<>(left, value, right.removeMerge(ta));
            }
            throw new IllegalStateException("We shouldn't be here");
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Tree<A> merge(Tree<A> a) {
            if (a.isEmpty()) {
                return this;
            }
            if (a.value().compareTo(this.value) > 0) {
                return new T<>(left, value, right.merge(new T<>(empty(), a.value(), a.right()))).merge(a.left());
            }
            if (a.value().compareTo(this.value) < 0) {
                return new T<>(left.merge(new T<>(a.left(), a.value(), empty())), value, right).merge(a.right());
            }
            return new T<>(left.merge(a.left()), value, right.merge(a.right()));
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
        public <B extends Comparable<B>> Tree<B> map(Function<A, B> f) {
            return foldInOrder(Tree.<B>empty(), t1 -> i -> t2 -> Tree.tree(t1, f.apply(i), t2));
        }

        @Override
        protected Tree<A> rotateLeft() {
            return right.isEmpty() ?
                    this :
                    new T<>(new T<>(left, value, right.left()), right.value(), right.right());
        }

        @Override
        protected Tree<A> rotateRight() {
            return left.isEmpty() ?
                    this :
                    new T<>(left.left(), left.value(), new T<>(left.right(), value, right));
        }

        @Override
        public List<A> toListInOrderRight() {
            return unBalanceRight(List.list(), this).eval();
        }

        private TailCall<List<A>> unBalanceRight(List<A> acc, Tree<A> tree) {
            return tree.isEmpty() ?
                    TailCall.ret(acc) :
                    tree.left().isEmpty() ?
                            TailCall.sus(() -> unBalanceRight(acc.cons(tree.value()), tree.right())) :
                            TailCall.sus(() -> unBalanceRight(acc, tree.rotateRight()));
        }

        @Override
        public String toString() {
            return String.format("(T %s %s %s)", left, value, right);
        }
    }

    @SuppressWarnings("unchecked")
    public static <A extends Comparable<A>> Tree<A> empty() {
        return EMPTY;
    }

    /**
     * 10.2 实现二叉搜索树 - 练习10.3 为了简化树的创建，请编写一个静态方法，它接收一个变长参数，并将所有元素插入一棵空树中。
     *
     * @param as
     * @param <A>
     * @return
     */
    @SafeVarargs
    public static <A extends Comparable<A>> Tree<A> tree(A... as) {
        return tree(List.list(as));
    }

    public static <A extends Comparable<A>> Tree<A> tree(List<A> list) {
        return list.foldLeft(empty(), t -> t::insert);
    }

    /**
     * 10.5.3 选择哪种折叠的实现 - 练习10.10 创建一个通过组合两棵树和一个根来创建一棵新树的方法。
     * 这个方法应该允许你在以下三种折叠方法中任选其一来重新构造与原来的树相同的树：foldPreOrder、foldInOrder和foldPostOrder。
     *
     * @param t1
     * @param a
     * @param t2
     * @param <A>
     * @return
     */
    public static <A extends Comparable<A>> Tree<A> tree(Tree<A> t1, A a, Tree<A> t2) {
        return ordered(t1, a, t2) ?
                new T<>(t1, a, t2) :
                ordered(t2, a, t1) ?
                        new T<>(t2, a, t1) :
                        Tree.<A>empty().insert(a).merge(t1).merge(t2);
    }

    public static <A extends Comparable<A>> boolean ordered(Tree<A> left, A a, Tree<A> right) {
        return left.max().flatMap(lMax -> right.min().map(rMin -> lt(lMax, a, rMin)))
                .getOrElse(left.isEmpty() && right.isEmpty())
                ||
                left.min().mapEmpty().flatMap(ignore -> right.min().map(rMin -> lt(a, rMin)))
                        .getOrElse(false)
                ||
                right.min().mapEmpty().flatMap(ignore -> left.max().map(lMax -> lt(lMax, a)))
                        .getOrElse(false);
    }

    public static <A extends Comparable<A>> boolean lt(A first, A second) {
        return first.compareTo(second) < 0;
    }

    public static <A extends Comparable<A>> boolean lt(A first, A second, A third) {
        return lt(first, second) && lt(second, third);
    }

    /**
     * 10.7.2 使用DSW算法平衡树 - 实现balance方法用于完全平衡树。它是一个静态方法，以待平衡的树为参数。
     *
     * @param tree
     * @param <A>
     * @return
     */
    public static <A extends Comparable<A>> Tree<A> balance(Tree<A> tree) {
        return balance_(tree.toListInOrderRight().foldLeft(Tree.<A>empty(), t -> a -> new T<>(empty(), a, t)));
    }

    public static <A extends Comparable<A>> Tree<A> balance_(Tree<A> tree) {
        return !tree.isEmpty() && tree.height() > log2nlz(tree.size()) ?
                (Math.abs(tree.left().height() - tree.right().height()) > 1 ?
                        balance_(balanceFirstLevel(tree)) :
                        new T<>(balance_(tree.left()), tree.value(), balance_(tree.right()))) :
                tree;
    }

    public static int log2nlz(int n) {
        return n == 0 ?
                0 :
                31 - Integer.numberOfLeadingZeros(n);
    }

    private static <A extends Comparable<A>> Tree<A> balanceFirstLevel(Tree<A> tree) {
        return unfold(tree, t -> isUnBalanced(t) ?
                tree.right().height() > tree.left().height() ?
                        Result.success(t.rotateLeft()) :
                        Result.success(t.rotateRight()) :
                Result.empty());
    }

    public static <A extends Comparable<A>> boolean isUnBalanced(Tree<A> tree) {
        return Math.abs(tree.left().height() - tree.right().height()) > (tree.size() - 1) % 2;
    }

    public static <A> A unfold(A a, Function<A, Result<A>> f) {
        Result<A> ra = Result.success(a);
        return unfold_(new Tuple<>(ra, ra), f).eval()._2.getOrElse(a);
    }

    private static <A> TailCall<Tuple<Result<A>, Result<A>>> unfold_(Tuple<Result<A>, Result<A>> a,
                                                                     Function<A, Result<A>> f) {
        Result<A> x = a._2.flatMap(f::apply);
        return x.isSuccess() ?
                TailCall.sus(() -> unfold_(new Tuple<>(a._2, x), f)) :
                TailCall.ret(a);
    }
}
