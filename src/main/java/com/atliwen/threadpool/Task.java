package com.atliwen.threadpool;

import com.atliwen.news.WeChatNews;
import com.atliwen.entity.ServiceHealthMonitoring;
import com.atliwen.entity.ServiceHealthMonitoringLog;
import com.atliwen.service.IServiceHealthMonitoringLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Slf4j
@Component
public class Task
{

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private WeChatNews watchable;

    @Autowired
    private IServiceHealthMonitoringLogService logService;

    @Async("taskExecutor")
    public Future<String> run(ServiceHealthMonitoring data) throws Exception {

        ServiceHealthMonitoringLog slog = new ServiceHealthMonitoringLog(data);
        String s = "";

        if (StringUtils.isEmpty(data.getUrl())) {
            return new AsyncResult<>("");
        }
        //1 down 2 访问异常 3 发送通知异常  4 成功
        long start = System.currentTimeMillis();
        long st;
        try {
            s = restTemplate.getForObject(data.getUrl(), String.class);
            slog.setState(4);
            slog.setData(s);

        } catch (Exception e) {
            slog.setState(2);
            slog.setData(e.toString());
        }
        st = (System.currentTimeMillis() - start);
        log.info("开始任务，需耗时：" + st + "毫秒" + " " + s);
        try {
            if (!StringUtils.isEmpty(s) && (s.contains("down") || s.contains("DOWN"))) {
                slog.setState(1);
                watchable.postForEntity(data.getNotice(), data.getService(), data.getIp(), s);
            }
        } catch (Exception e) {
            //     设置 记录信息 为服务错误  记录错误信息
            if (slog.getState() == 4) {
                slog.setData(e.toString());
                slog.setState(3);
            }
        }
        if (slog.getState() > 3) {
            slog.setCalltime(st);
        } else {
            slog.setCalltime(-st);
        }
        //  记录信息DB
        try {
            logService.save(slog);
        } catch (Exception e) {
            log.error("保存数据库异常", e);
        }

        log.info("完成任务");
        return new AsyncResult<>("完成任务");
    }

}
