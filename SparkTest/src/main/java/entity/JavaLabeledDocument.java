package entity;

import java.io.Serializable;

/**
 * @Author: zhuxi
 * @Time: 2021/8/11 14:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
@SuppressWarnings("serial")
public class JavaLabeledDocument extends JavaDocument implements Serializable {

    private double label;

    public JavaLabeledDocument(long id, String text, double label) {
        super(id, text);
        this.label = label;
    }

    public double getLabel() {
        return this.label;
    }
}
