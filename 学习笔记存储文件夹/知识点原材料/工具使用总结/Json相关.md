# 解决fastjson、Jackson、Gson解析Json数据时，key为Java中关键字无法解析的问题

参考：https://wuyongshi.top/articles/2017/01/03/1483428139532.html

无论我们在使用fastjson、Jackson还是Gson，我们在用json转换为实体类时，都是根据json数据建立对应实体类，但比较恶心的是，有时，有些服务商返回的json报文中，key值为java中的关键字，我们没法用关键字，当做一个类的成员变量，不过不代表我们就没有其他的办法解决了；

先给个测试实体类：
~~~java
public class ClientInfoEntity  {
    private Long id;

    // 客户编号
    @SerializedName("abstract")
    @JSONField(name="abstract")
    private String abstract_;

    @Override
    public String toString() {
        return "ClientInfoEntity [id=" + id + ", abstract_=" + abstract_ + "]";
    }
    
    public String getAbstract_() {
        return abstract_;
    }
    
    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
~~~
解决方案如下：

①使用fastjson：
则在实体类中的对应成员变量中加上以下注解：
~~~java
@JSONField(name="abstract")
	private String abstract_;
~~~

②使用gson
则在实体类中的对应成员变量中加上以下注解：
~~~java
@SerializedName("abstract")
private String abstract_;
~~~
③使用jackson
则在实体类中的对应成员变量中加上以下注解：
~~~java
@JsonProperty("abstract")
private String abstract_;
~~~
当然了，三种注解是不冲突的，如果项目中使用多种方式解析，可以将对应的注解都加上，如给的测试实体类，我就加了fastjson和gson的两种注解

单元测试方法：
~~~java
@org.junit.Test
 public void testGson(){
	 String json = "{id:1,abstract:231}";
	 ClientInfoEntity clientInfoEntity =  new Gson().fromJson(json, ClientInfoEntity.class);
	 System.out.println(clientInfoEntity);
	 System.out.println(JSON.parseObject(json, ClientInfoEntity.class));
 }
~~~

# gson 存在 int自动变成double的问题

需要注意转换Integer需要特殊处理