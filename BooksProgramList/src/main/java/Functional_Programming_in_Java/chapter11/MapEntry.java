package Functional_Programming_in_Java.chapter11;

import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/12 16:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class MapEntry<K, V> implements Comparable<MapEntry<K, V>> {
    public final K key;
    public final Result<V> value;

    private MapEntry(K key, Result<V> value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 11.2.3 使用键不可比较的map - 练习11.4 实现一个Map的版本，其键不可比较
     *
     * @param that
     * @return
     */
    @Override
    public int compareTo(MapEntry<K, V> that) {
        int thisHashCode = this.hashCode();
        int thatHashCode = that.hashCode();

//        return thisHashCode < thatHashCode ?
//                -1 :
//                thisHashCode > thatHashCode ?
//                        1 :
//                        0;
        return Integer.compare(thisHashCode, thatHashCode);
    }

    @Override
    public String toString() {
        return String.format("MapEntry(%s, %s)", key, value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MapEntry && this.key.equals(((MapEntry) o).key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public static <K extends Comparable<K>, V> MapEntry<K, V> mapEntry(K key, V value) {
        return new MapEntry<>(key, Result.success(value));
    }

    public static <K extends Comparable<K>, V> MapEntry<K, V> mapEntry(K key) {
        return new MapEntry<>(key, Result.empty());
    }
}
