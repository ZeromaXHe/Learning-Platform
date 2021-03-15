package com.zerox.gobang.frame;

import java.awt.*;

/**
 * @Author: zhuxi
 * @Time: 2021/3/15 18:17
 * @Description: GridBagConstraints 类的一个简化工具类
 * @Modified By: zhuxi
 */
public class GBC extends GridBagConstraints {
    /**
     * 使用给定的gridX和gridY构造一个GBC，其他参数设置为默认值
     *
     * @param gridX
     * @param gridY
     */
    public GBC(int gridX, int gridY) {
        this.gridx = gridX;
        this.gridy = gridY;
    }

    /**
     * 使用给定的gridY、gridY、gridWidth和gridHeight构造一个GBC，其他参数设置为默认值
     * <p>
     * 这些约束定义了组件在网格中的位置。
     * gridx 和 gridy 指定了被添加组件左上角的行、列位置。
     * gridwidth 和 gridheight 指定了组件占据的行数和列数。
     * 网格的坐标从 0 开始。gridx=0 和 gridy=0 代表最左上角。
     * 例如，gridx=2, gridy=0意思为这个区起始于 0 行 2 列（即第 3 列 )
     * girdwidth=1, gridheight=4 意为它横跨了 4 行 1 列。
     *
     * @param gridX
     * @param gridY
     * @param gridWidth
     * @param gridHeight
     */
    public GBC(int gridX, int gridY, int gridWidth, int gridHeight) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
    }

    /**
     * 如果组件没有填充整个区域，可以通过设置anchor域在指定其位置。
     * 有效值为GridBagConstraints.CENTER(默认值)、GridBagConstraints.NORTH、GridBagConstraints.NORTHEAST、GridBagConstraints.EAST等。
     *
     * @param anchor
     * @return
     */
    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    /**
     * 如果不希望组件拉伸至整个区域，就需要设置fill约束。
     * 它有四个有效值：GridBagConstraints.NONE、GridBagConstraints.HORIZONTAL、GridBagConstraints.VERTICAL、GridBagConstraints.BOTH
     *
     * @param fill
     * @return
     */
    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }

    /**
     * 在网格布局中，需要为每个区域设置增量域（weightx 和 weighty)。如果将增量设置为0, 则这个区域将永远为初始尺寸。
     * 另一方面， 如果将所有区域的增量都设置为 0, 容器就会集聚在为它分配的区域中间， 而不是通过拉伸来填充它。
     *
     * @param weightX
     * @param weightY
     * @return
     */
    public GBC setWeight(double weightX, double weightY) {
        this.weightx = weightX;
        this.weighty = weightY;
        return this;
    }

    /**
     * 可以通过设置GridBagLayout的insets域在组件周围增加附加的空白区域
     *
     * @param distance
     * @return
     */
    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * 通过设置 Insets 对象的 left、top、right 和 bottom 指定组件周围的空间量。
     * 这被称作外部填充（或外边距 ) ( external padding )。
     *
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @return
     */
    public GBC setInsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * 通过设置 ipadx 和 ipady 指定内部填充（或内外距)( internal padding )。
     *
     * @param ipadX
     * @param ipadY
     * @return
     */
    public GBC setIpad(int ipadX, int ipadY) {
        this.ipadx = ipadX;
        this.ipady = ipadY;
        return this;
    }
}
