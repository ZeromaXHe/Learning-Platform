package Functional_Programming_in_Java.chapter11;

import Functional_Programming_in_Java.chapter7.Result;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 11:52
 * @Description:
 * @ModifiedBy: zhuxi
 */
public abstract class Heap<A> {

    protected abstract Result<Heap<A>> left();

    protected abstract Result<Heap<A>> right();

    protected abstract int rank();

    public abstract Result<A> head();

    public abstract int length();

    public abstract boolean isEmpty();

    /**
     * 11.3.6 实现像队列一样的接口 - 练习11.6 定义一个tail方法，返回删除head后的剩余元素。
     * 这个方法就像head方法一样，返回一个Result，以便能够在空队列上安全地调用它。
     *
     * @return
     */
    public abstract Result<Heap<A>> tail();

    /**
     * 11.3.6 实现像队列一样的接口 - 练习11.7 实现一个接收int参数的get方法，并按优先顺序返回第n个元素。
     * 这个方法将返回一个Result以处理找不到元素的情况。
     *
     * @param index
     * @return
     */
    public abstract Result<A> get(int index);

    /**
     * 11.4 元素不可比较的优先队列 - 练习11.8 修改Heap类以使用Comparable元素或单独的Comparator。
     *
     * @return
     */
    protected abstract Result<Comparator<A>> comparator();

    /**
     * 11.4 元素不可比较的优先队列 - 练习11.9 到目前为止，你只能通过merge方法将一个元素添加到一个Heap中。
     * 实现一个insert方法，它不需要merge就可以添加一个元素。
     *
     * @param a
     * @return
     */
    public abstract Heap<A> insert(A a);

    public static class Empty<A> extends Heap<A> {
        /**
         * 11.4 元素不可比较的优先队列 - 练习11.8 修改Heap类以使用Comparable元素或单独的Comparator。
         */
        private final Result<Comparator<A>> comparator;

        private Empty(Result<Comparator<A>> comparator) {
            this.comparator = comparator;
        }

        @Override
        protected Result<Heap<A>> left() {
            return Result.success(empty(this.comparator));
        }

        @Override
        protected Result<Heap<A>> right() {
            return Result.success(empty(this.comparator));
        }

        @Override
        protected int rank() {
            return 0;
        }

        @Override
        public Result<A> head() {
            return Result.failure(new NoSuchElementException("head() called on empty heap"));
        }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Result<Heap<A>> tail() {
            return Result.failure(new NoSuchElementException("tail() called on empty heap"));
        }

        @Override
        public Result<A> get(int index) {
            return Result.failure(new NoSuchElementException("Index out of range"));
        }

        @Override
        protected Result<Comparator<A>> comparator() {
            return this.comparator;
        }

        @Override
        public Heap<A> insert(A a) {
            return heap(a, this, this);
        }
    }

    public static class H<A> extends Heap<A> {
        private final int length;
        private final int rank;
        private final A head;
        private final Heap<A> left;
        private final Heap<A> right;
        /**
         * 11.4 元素不可比较的优先队列 - 练习11.8 修改Heap类以使用Comparable元素或单独的Comparator。
         */
        private final Result<Comparator<A>> comparator;

        public H(int length, int rank, Heap<A> left, A head, Heap<A> right, Result<Comparator<A>> comparator) {
            this.length = length;
            this.rank = rank;
            this.head = head;
            this.left = left;
            this.right = right;
            this.comparator = comparator;
        }

        @Override
        protected Result<Heap<A>> left() {
            return Result.success(this.left);
        }

        @Override
        protected Result<Heap<A>> right() {
            return Result.success(this.right);
        }

        @Override
        protected int rank() {
            return this.rank;
        }

        @Override
        public Result<A> head() {
            return Result.success(this.head);
        }

        @Override
        public int length() {
            return this.length;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Result<Heap<A>> tail() {
            return Result.success(Heap.merge(left, right));
        }

        @Override
        public Result<A> get(int index) {
            return index == 0 ?
                    head() :
                    tail().flatMap(x -> x.get(index - 1));
        }

        @Override
        protected Result<Comparator<A>> comparator() {
            return this.comparator;
        }

        @Override
        public Heap<A> insert(A a) {
            int comp = compare(head, a, comparator);
            return heap(
                    comp < 0 ? head : a,
                    left,
                    right.insert(comp >= 0 ? head : a)
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static <A> Heap<A> empty() {
        return empty(Result.empty());
    }

    public static <A> Heap<A> empty(Comparator<A> comparator) {
        return empty(Result.success(comparator));
    }

    public static <A> Heap<A> empty(Result<Comparator<A>> comparator) {
        return new Empty<>(comparator);
    }

    /**
     * 11.3.5 实现左倾堆 - 练习11.5 要添加到Heap实现的第一个功能是添加一个元素，为此定义一个add方法。
     *
     * @param element
     * @return
     */
    public Heap<A> add(A element) {
        return merge(this, heap(element, this.comparator()));
    }

    public static <A> Heap<A> merge(Heap<A> first, Heap<A> second) {
        Result<Comparator<A>> comparator = first.comparator().orElse(second::comparator);
        return merge(first, second, comparator);
    }

    public static <A> Heap<A> merge(Heap<A> first, Heap<A> second, Result<Comparator<A>> comparator) {
        return first.head().flatMap(
                fh -> second.head().flatMap(
                        sh -> compare(fh, sh, comparator) <= 0 ?
                                first.left().flatMap(
                                        fl -> first.right().map(
                                                fr -> heap(fh, fl, merge(fr, second, comparator))
                                        )
                                ) :
                                second.left().flatMap(
                                        sl -> second.right().map(
                                                sr -> heap(sh, sl, merge(first, sr, comparator))
                                        )
                                )
                )
        ).getOrElse(first.isEmpty() ? second : first);
    }

    /**
     * 11.4 元素不可比较的优先队列 - 练习11.8 修改Heap类以使用Comparable元素或单独的Comparator。
     *
     * @param first
     * @param second
     * @param comparator
     * @param <A>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <A> int compare(A first, A second, Result<Comparator<A>> comparator) {
        return comparator.map(comp -> comp.compare(first, second))
                .getOrElse(() -> ((Comparable<A>) first).compareTo(second));
    }

    public static <A extends Comparable<A>> Heap<A> heap(A element) {
        return heap(element, Result.empty());
    }

    public static <A> Heap<A> heap(A element, Result<Comparator<A>> comparator) {
        Heap<A> empty = empty(comparator);
        return new H<>(1, 1, empty, element, empty, comparator);
    }

    public static <A> Heap<A> heap(A element, Comparator<A> comparator) {
        Heap<A> empty = empty(comparator);
        return new H<>(1, 1, empty, element, empty, Result.success(comparator));
    }

    public static <A> Heap<A> heap(A head, Heap<A> first, Heap<A> second) {
        Result<Comparator<A>> comparator = first.comparator().orElse(second::comparator);
        return first.rank() >= second.rank() ?
                new H<>(first.length() + second.length() + 1, second.rank() + 1, first, head, second, comparator) :
                new H<>(first.length() + second.length() + 1, first.rank() + 1, second, head, first, comparator);
    }
}
