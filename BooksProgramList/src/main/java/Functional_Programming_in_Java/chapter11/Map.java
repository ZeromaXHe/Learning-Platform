package Functional_Programming_in_Java.chapter11;

import Functional_Programming_in_Java.chapter2.Function;
import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/12 16:19
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Map<K extends Comparable<K>, V> {
    protected final Tree<MapEntry<Integer, List<Tuple<K, V>>>> delegate;

    private Map() {
        this.delegate = Tree.empty();
    }

    private Map(Tree<MapEntry<Integer, List<Tuple<K, V>>>> delegate) {
        this.delegate = delegate;
    }

    /**
     * 11.2.1 实现map - 练习 11.2 实现所有方法以完成Map类
     * 11.2.3 使用键不可比较的map - 练习11.4 实现一个Map的版本，其键不可比较
     *
     * @param key
     * @param value
     * @return
     */
    public Map<K, V> add(K key, V value) {
//        return new Map<>(delegate.insert(MapEntry.mapEntry(key, value)));
        Tuple<K, V> tuple = new Tuple<>(key, value);
        List<Tuple<K, V>> ltkv = getAll(key).map(lt -> lt.foldLeft(List.list(tuple),
                l -> t -> t._1.equals(key) ?
                        l :
                        l.cons(t))).getOrElse(() -> List.list(tuple));
        return new Map<>(delegate.insert(MapEntry.mapEntry(key.hashCode(), ltkv)));
    }

    public boolean contains(K key) {
//        return delegate.member(MapEntry.mapEntry(key));
        return getAll(key).map(lt -> lt.exists(t -> t._1.equals(key))).getOrElse(false);
    }

    /// 缺少相关的方法，先注释掉
//    public Map<K, V> remove(K key) {
////        return new Map<>(delegate.delete(MapEntry.mapEntry(key)));
//        List<Tuple<K, V>> ltkv = getAll(key).map(lt -> lt.foldLeft(List.<Tuple<K, V>>list(),
//                l -> t -> t._1.equals(key) ?
//                        l :
//                        l.cons(t))).getOrElse(List::list);
//        return ltkv.isEmpty() ?
//                new Map<>(delegate.delete(MapEntry.mapEntry(key.hashCode()))) :
//                new Map<>(delegate.insert(MapEntry.mapEntry(key.hashCode(), ltkv)));
//    }

    /// 键不可比较的Map不需要max和min
//    public MapEntry<K, V> max() {
//        return delegate.max();
//    }
//
//    public MapEntry<K, V> min() {
//        return delegate.min();
//    }

    /// 缺少相关的方法，先注释掉
//    public Result<MapEntry<K, V>> get(K key) {
////        return delegate.get(MapEntry.mapEntry(key));
//        return getAll(key).flatMap(lt -> lt.first(t -> t._1.equals(key)));
//    }

    private Result<List<Tuple<K, V>>> getAll(K key) {
        return delegate.get(MapEntry.mapEntry(key.hashCode())).flatMap(x -> x.value.map(lt -> lt.map(t -> t)));
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public static <K extends Comparable<K>, V> Map<K, V> empty() {
        return new Map<>();
    }

//    public <B> B foldLeft(B identity, Function<B, Function<MapEntry<K, V>, B>> f, Function<B, Function<B, B>> g) {
//        return delegate.foldLeft(identity, b -> me -> f.apply(b).apply(me), g);
//    }

    /**
     * 11.2.2 扩展map - 练习11.3 在Map类中编写一个values方法，按键的升序返回map中包含的值列表。
     *
     * @return
     */
//    public List<V> values() {
//        return List.sequence(
//                delegate.foldInReverseOrder(
//                        List.<Result<V>>list(),
//                        lst1 -> me -> lst2 -> List.concat(lst2, lst1.cons(me.value))
//                )
//        ).getOrElse(List.list());
//    }

    // 如果有类型的问题，你可以用显式类型来编写这个函数
    Function<List<Result<V>>, Function<MapEntry<K, V>, Function<List<Result<V>>, List<Result<V>>>>> f =
            lst1 -> me -> lst2 -> List.concat(lst2, lst1.cons(me.value));
}
