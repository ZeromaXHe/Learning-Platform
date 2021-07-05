package Functional_Programming_in_Java.chapter3.typeTest;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 16:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Product {
    private final String name;
    private final Price price;
    private final Weight weight;

    public Product(String name, Price price, Weight weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Weight getWeight() {
        return weight;
    }
}
