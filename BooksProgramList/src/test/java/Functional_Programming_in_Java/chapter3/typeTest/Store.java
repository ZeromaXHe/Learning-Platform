package Functional_Programming_in_Java.chapter3.typeTest;

import Functional_Programming_in_Java.chapter3.CollectionUtilities;

import java.util.List;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 16:34
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Store {
    public static void main(String[] args) {
        Product toothPaste = new Product("Tooth paste", Price.price(1.5), Weight.weight(0.5));
        Product toothBrush = new Product("Tooth brush", Price.price(3.5), Weight.weight(0.3));
        List<OrderLine> order = CollectionUtilities.list(
                new OrderLine(toothPaste, 2),
                new OrderLine(toothBrush, 3));

        Price price = CollectionUtilities.foldLeft(order, Price.ZERO, Price.sum);
        Weight weight = CollectionUtilities.foldLeft(order, Weight.ZERO, Weight.sum);
        System.out.println(String.format("Total price: %s", price));
        System.out.println(String.format("Total weight: %s", weight));
    }
}
