package entity;

import java.io.Serializable;

/**
 * @Author: zhuxi
 * @Time: 2021/8/11 14:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
@SuppressWarnings("serial")
public class JavaDocument implements Serializable {

    private long id;
    private String text;

    public JavaDocument(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }
}