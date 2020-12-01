# Spring 事务嵌套

来源：https://www.cnblogs.com/duanxz/p/4746892.html

## 一、基本概念
 事务的隔离级别，事务传播行为见《事务之二：spring事务（事务管理方式，事务5隔离级别，7个事务传播行为，spring事务回滚条件） 》 https://www.cnblogs.com/duanxz/p/3439387.html

## 二、 嵌套事务示例

调用顺序：
~~~
ServiceA-1      A       外层方法        1
ServiceB        B       内层方法        2
ServiceA-2      A       外层方法        3
~~~

### 2.1、Propagation.REQUIRED+Propagation.REQUIRES_NEW
~~~java
package dxz.demo1;


@Service
public class ServiceAImpl implements ServiceA {

    @Autowired
    private ServiceB serviceB;
    
    @Autowired
    private VcSettleMainMapper vcSettleMainMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodA() {
        String id = IdGenerator.generatePayId("A");
        VcSettleMain vc = buildModel(id);
        vcSettleMainMapper.insertVcSettleMain(vc);
        System.out.println("ServiceAImpl VcSettleMain111:" + vc);
        
        serviceB.methodB();  
        
        VcSettleMain vc2 = buildModel(id);
        vcSettleMainMapper.insertVcSettleMain(vc2);
        System.out.println("ServiceAImpl VcSettleMain22222:" + vc2);
    }

    private VcSettleMain buildModel(String id) {
        VcSettleMain vc = new VcSettleMain();
        vc.setBatchNo(id);
        vc.setCreateBy("dxz");
        vc.setCreateTime(LocalDateTime.now());
        vc.setTotalCount(11L);
        vc.setTotalMoney(BigDecimal.ZERO);
        vc.setState("5");
        return vc;
    }

}
~~~

ServiceB

~~~java
package dxz.demo1;


@Service
public class ServiceBImpl implements ServiceB {

    @Autowired
    private VcSettleMainMapper vcSettleMainMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void methodB() {
        String id = IdGenerator.generatePayId("B");
        VcSettleMain vc = buildModel(id);
        vcSettleMainMapper.insertVcSettleMain(vc);
        System.out.println("---ServiceBImpl VcSettleMain:" + vc);
    }
}
~~~

controller

~~~java
package dxz.demo1;

@RestController
@RequestMapping("/dxzdemo1")
@Api(value = "Demo1", description="Demo1")
public class Demo1 {
    @Autowired
    private ServiceA serviceA;

    /**
     * 嵌套事务测试
     */
    @PostMapping(value = "/test1")
    public String methodA() throws Exception {
        serviceA.methodA();
        return "ok";
    }
}
~~~
结果：

Response Body
"exception":"org.springframework.dao.DuplicateKeyException"

看数据库表记录：

存在B中插入的数据
 

这种情况下, 因为 ServiceB#methodB 的事务属性为 PROPAGATION_REQUIRES_NEW，ServiceB是一个独立的事务，与外层事务没有任何关系。如果ServiceB执行失败（上面示例中让ServiceB的id为已经存在的值），ServiceA的调用出会抛出异常，导致ServiceA的事务回滚。

并且， 在 ServiceB#methodB 执行时 ServiceA#methodA 的事务已经挂起了 (关于事务挂起的内容已经超出了本文的讨论范围）。

### 2.2、Propagation.REQUIRED+Propagation.REQUIRED
~~~java
//ServiceA
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodA() {

//ServiceB
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodB(String id) {
//...
~~~
--“1”可插入，“2”可插入，“3”不可插入：

结果是“1”，“2”，“3”都不能插入，“1”，“2”被回滚。

--“1”可插入，“2”不可插入，“3”可插入：

结果是“1”，“2”，“3”都不能插入，“1”，“2”被回滚。

### 2.3、Propagation.REQUIRED+无事务注解
~~~java
//ServiceA
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodA() {

//ServiceB
//...
    @Override
    //没有加事务注解
    public void methodB(String id) {
//...
~~~
--“1”可插入，“2”可插入，“3”不可插入：

结果是“1”，“2”，“3”都不能插入，“1”，“2”被回滚。

### 2.4、内层事务被try-catch：

2.4.1、trycatch+Propagation.REQUIRED+Propagation.REQUIRED
~~~java
//ServiceA
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodA() {
        try {
            serviceB.methodB(id);
        } catch (Exception e) {
            System.out.println("内层事务出错啦。");
        }
    }

//ServiceB
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodB(String id) {
//...
~~~
--“1”可插入，“2”不可插入，“3”可插入：

结果是“1”，“2”，“3”都不能插入，“1”被回滚。

事务设置为Propagation.REQUIRED时，如果内层方法抛出Exception，外层方法中捕获Exception但是并没有继续向外抛出，最后出现“Transaction rolled back because it has been marked as rollback-only”的错误。外层的方法也将会回滚。

其原因是：内层方法抛异常返回时，transacation被设置为rollback-only了，但是外层方法将异常消化掉，没有继续向外抛，那么外层方法正常结束时，transaction会执行commit操作，但是transaction已经被设置为rollback-only了。所以，出现“Transaction rolled back because it has been marked as rollback-only”错误。

2.4.2、trycatch+Propagation.REQUIRED+Propagation.NESTED
~~~java
//ServiceA
//...
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void methodA() {
        try {
            serviceB.methodB(id);
        } catch (Exception e) {
            System.out.println("内层事务出错啦。");
        }
    }

//ServiceB
//...
    @Override
    @Transactional(propagation = Propagation.NESTED, readOnly = false)
    public void methodB(String id) {
//...
~~~
--“1”可插入，“2”不可插入，“3”可插入：

结果是“1”，“3"记录插入成功，“2”记录插入失败。

说明：

当内层配置成 PROPAGATION_NESTED, 此时两者之间又将如何协作呢? 从 Juergen Hoeller 的原话中我们可以找到答案, ServiceB#methodB 如果 rollback, 那么内部事务(即 ServiceB#methodB) 将回滚到它执行前的 SavePoint(注意, 这是本文中第一次提到它, 潜套事务中最核心的概念), 而外部事务(即 ServiceA#methodA) 可以有以下两种处理方式：

1、内层失败，外层调用其它分支，代码如下

~~~java
ServiceA {  
      
    /** 
     * 事务属性配置为 PROPAGATION_REQUIRED 
     */  
    void methodA() {  
        try {  
            ServiceB.methodB();  
        } catch (SomeException) {  
            // 执行其他业务, 如 ServiceC.methodC();  
        }  
    }  
  
}  
~~~
这种方式也是潜套事务最有价值的地方, 它起到了分支执行的效果, 如果 ServiceB.methodB 失败, 那么执行 ServiceC.methodC(), 而 ServiceB.methodB 已经回滚到它执行之前的 SavePoint, 所以不会产生脏数据(相当于此方法从未执行过), 这种特性可以用在某些特殊的业务中, 而 PROPAGATION_REQUIRED 和 PROPAGATION_REQUIRES_NEW 都没有办法做到这一点。

2.代码不做任何修改, 那么如果内部事务(即 ServiceB#methodB) rollback, 那么首先 ServiceB.methodB 回滚到它执行之前的 SavePoint(在任何情况下都会如此), 外部事务(即 ServiceA#methodA) 将根据具体的配置决定自己是 commit 还是 rollback。

## 三、嵌套事务总结

使用嵌套事务的场景有两点需求：

需要事务BC与事务AD一起commit，即：作为事务AD的子事务，事务BC只有在事务AD成功commit时（阶段3成功）才commit。这个需求简单称之为“联合成功”。这一点PROPAGATION_NESTED和PROPAGATION_REQUIRED可以做到。

需要事务BC的rollback不（无条件的）影响事务AD的commit。这个需求简单称之为“隔离失败”。这一点PROPAGATION_NESTED和PROPAGATION_REQUIRES_NEW可以做到。
分解下，可知PROPAGATION_NESTED的特殊性有：

- 1、使用PROPAGATION_REQUIRED满足需求1，但子事务BC的rollback会无条件地使父事务AD也rollback，不能满足需求2。即使对子事务进行了try-catch，父事务AD也不能commit。示例见2.4.1、trycatch+Propagation.REQUIRED+Propagation.REQUIRED

- 2、使用PROPAGATION_REQUIRES_NEW满足需求2，但子事务（这时不应该称之为子事务）BC是完全新的事务上下文，父事务（这时也不应该称之为父事务）AD的成功与否完全不影响BC的提交，不能满足需求1。

同时满足上述两条需求就要用到PROPAGATION_NESTED了。PROPAGATION_NESTED在事务AD执行到B点时，设置了savePoint（关键）。

当BC事务成功commit时，PROPAGATION_NESTED的行为与PROPAGATION_REQUIRED一样。只有当事务AD在D点成功commit时，事务BC才真正commit，如果阶段3执行异常，导致事务AD rollback，事务BC也将一起rollback ，从而满足了“联合成功”。

 当阶段2执行异常，导致BC事务rollback时，因为设置了savePoint，AD事务可以选择与BC一起rollback或继续阶段3的执行并保留阶段1的执行结果，从而满足了“隔离失败”。

当然，要明确一点，事务传播策略的定义是在声明或事务管理范围内的（首先是在EJB CMT规范中定义，Spring事务框架补充了PROPAGATION_NESTED），编程式的事务管理不存在事务传播的问题。

## 四、PROPAGATION_NESTED的必要条件

上面大致讲述了嵌套事务的使用场景, 下面我们来看如何在 spring 中使用 PROPAGATION_NESTED, 首先来看 AbstractPlatformTransactionManager 

~~~java
/** 
 * Create a TransactionStatus for an existing transaction. 
 */  
private TransactionStatus handleExistingTransaction(  
        TransactionDefinition definition, Object transaction, boolean debugEnabled)  
        throws TransactionException {  
  
   ... 省略  
  
    if (definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_NESTED) {  
        if (!isNestedTransactionAllowed()) {  
            throw new NestedTransactionNotSupportedException(  
                    "Transaction manager does not allow nested transactions by default - " +  
                    "specify 'nestedTransactionAllowed' property with value 'true'");  
        }  
        if (debugEnabled) {  
            logger.debug("Creating nested transaction with name [" + definition.getName() + "]");  
        }  
        if (useSavepointForNestedTransaction()) {  
            // Create savepoint within existing Spring-managed transaction,  
            // through the SavepointManager API implemented by TransactionStatus.  
            // Usually uses JDBC 3.0 savepoints. Never activates Spring synchronization.  
            DefaultTransactionStatus status =  
                    newTransactionStatus(definition, transaction, false, false, debugEnabled, null);  
            status.createAndHoldSavepoint();  
            return status;  
        }  
        else {  
            // Nested transaction through nested begin and commit/rollback calls.  
            // Usually only for JTA: Spring synchronization might get activated here  
            // in case of a pre-existing JTA transaction.  
            doBegin(transaction, definition);  
            boolean newSynchronization = (this.transactionSynchronization != SYNCHRONIZATION_NEVER);  
            return newTransactionStatus(definition, transaction, true, newSynchronization, debugEnabled, null);  
        }  
    }  
} 
~~~

1、 我们要设置 transactionManager 的 nestedTransactionAllowed 属性为 true, 注意, 此属性默认为 false!!! 

再看 AbstractTransactionStatus#createAndHoldSavepoint() 方法 

~~~java
/** 
 * Create a savepoint and hold it for the transaction. 
 * @throws org.springframework.transaction.NestedTransactionNotSupportedException 
 * if the underlying transaction does not support savepoints 
 */  
public void createAndHoldSavepoint() throws TransactionException {  
    setSavepoint(getSavepointManager().createSavepoint());  
}  
~~~
  可以看到 Savepoint 是 SavepointManager.createSavepoint 实现的, 再看 SavepointManager 的层次结构, 发现其 Template 实现是 JdbcTransactionObjectSupport, 常用的 DatasourceTransactionManager, HibernateTransactionManager 中的 TransactonObject 都是它的子类 : 
  
  JdbcTransactionObjectSupport 告诉我们必须要满足两个条件才能 createSavepoint : 
  
 2、 java.sql.Savepoint 必须存在, 即 jdk 版本要 1.4+ 
 
 3、 Connection.getMetaData().supportsSavepoints() 必须为 true, 即 jdbc drive 必须支持 JDBC 3.0 确保以上条件都满足后, 你就可以尝试使用 PROPAGATION_NESTED 了。
