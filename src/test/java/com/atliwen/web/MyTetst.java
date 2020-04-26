package com.atliwen.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atliwen.DemonstrationApplication;
import com.atliwen.entity.ServiceHealthMonitoringLog;
import com.atliwen.service.IServiceHealthMonitoringLogService;
import com.atliwen.service.impl.ServiceHealthMonitoringLogServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author 李文
 * @create 2019-04-23 13:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemonstrationApplication.class)
public class MyTetst
{
    @Autowired
    private IServiceHealthMonitoringLogService service;

    @Autowired
    ServiceHealthMonitoringLogServiceImpl sImpl;

    @Test
    public void test3() {


        IPage<ServiceHealthMonitoringLog> ps = sImpl.getQuestionStudent(
                new Page<>(1, 3),
                "李文", "测试服务1", null);

        System.out.println(ps);

        //System.out.println("1111111" + sImpl.getQuestionStudent(new Page<>(1, 3),
        //        "李文", "测试服务1", null));
    }
    //@Test
    //public void test3() {
    //    System.out.println(sImpl.getTestList("李文","测试服务1"));
    //}

    @Test
    public void test2() {
        System.out.println(service.list());
    }

    @Test
    public void test1() {
        List<ServiceHealthMonitoringLog> list = new ArrayList<>();
        Date date = new Date();
        Random random = new Random();

        for (int i = 1; i < 1001; i++) {
            ServiceHealthMonitoringLog sLog = new ServiceHealthMonitoringLog();
            sLog.setData("{a:'a'}");
            //1 成功 2 down 3 访问异常 4 发送通知异常
            int number = random.nextInt(4 - 1 + 1) + 1;
            sLog.setState(number);

            long b = random.nextInt(5000 - 10 + 1) + 10;
            if (number > 1) {
                b = -b;
            }
            sLog.setCalltime(b);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, -i);
            sLog.setCreatedate(calendar.getTime());
            sLog.setIp("10.10.12.22");
            sLog.setNotice("http://baidu.com.cn");
            sLog.setUser("李文");
            sLog.setService("测试服务");
            list.add(sLog);
        }
        service.saveBatch(list);

    }


}
