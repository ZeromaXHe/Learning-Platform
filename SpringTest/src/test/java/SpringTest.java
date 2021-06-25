import com.zerox.task.LimitMinuteLogTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zhuxi
 * @Time: 2021/6/25 13:33
 * @Description:
 * @Modified By: zhuxi
 */

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
//@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//------------
public class SpringTest {

    @Autowired
    private LimitMinuteLogTask limitMinuteLogTask;

    @Test
    public void localDateTest() {
        // MessageFormat.format 里的格式为：21-6-25 下午3:16
        System.out.println(MessageFormat.format("{0}", new Date()));
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
        System.out.println(getYMdHmsTimeString());
    }

    private String getYMdHmsTimeString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    }

    @Test
    public void limitMinuteLogTest() {
        IntStream.rangeClosed(1, 4).forEach(i -> {
            String start = MessageFormat.format("{0}|[{1}]start... minuteOfDay: {2}",
                    getYMdHmsTimeString(), i, LimitMinuteLogTask.getCurrentMinuteOfDay());
            System.out.println(start);
            IntStream.rangeClosed(1, 100).forEach(this::asyncLog);
            String end = MessageFormat.format("{0}|[{1}]end... minuteOfDay: {2}",
                    getYMdHmsTimeString(), i, LimitMinuteLogTask.getCurrentMinuteOfDay());
            System.out.println(end);
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void asyncLog(int total) {
        String identityStr = "limitMinuteTest";
        String logStr = MessageFormat.format("limitMinuteTest|{0}|100", total);
        System.out.println(getYMdHmsTimeString() + "|" + logStr);
        limitMinuteLogTask.addLogToMinuteToLimitLogMap(LimitMinuteLogTask.getCurrentMinuteOfDay(), identityStr,
                System.currentTimeMillis(), logStr);
    }
}
