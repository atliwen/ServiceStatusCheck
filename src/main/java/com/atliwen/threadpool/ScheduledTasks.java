package com.atliwen.threadpool;

import com.atliwen.entity.ServiceHealthMonitoring;
import com.atliwen.service.IServiceHealthMonitoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
@Slf4j
public class ScheduledTasks
{

    @Autowired
    IServiceHealthMonitoringService service;
    @Autowired
    Task task;

    /**
     * 一分钟执行一次
     */
    @Scheduled(fixedDelay = 60000)
    public void reportCurrentTime() {
        List<Future<String>> futureResult = new ArrayList<>();
        List<ServiceHealthMonitoring> list = service.list();
        log.info("开始执行服务检查，需要检查的服务数量{}", list.size());
        for (ServiceHealthMonitoring s : list) {
            log.info("开始执行 {} 服务检查", s.getService());
            try {
                futureResult.add(task.run(s));
            } catch (Exception e) {
                log.error(" 执行 {} 服务检查失败 ", s.getService(), e);
            }
        }
        if (futureResult.size() == 0) {
            return;
        }
        boolean tr;
        do {
            boolean d = false;
            for (Future<String> s : futureResult) {
                if (!s.isDone()) {
                    d = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error(" 等待全部执行完毕 睡眠10秒异常", e);
                    }
                }
            }
            tr = d;
        } while (tr);
        log.info("完成执行服务检查");
    }

}
