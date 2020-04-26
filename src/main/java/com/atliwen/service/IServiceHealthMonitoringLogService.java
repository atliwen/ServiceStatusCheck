package com.atliwen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atliwen.domain.MonitoringLog;
import com.atliwen.entity.ServiceHealthMonitoringLog;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
public interface IServiceHealthMonitoringLogService extends IService<ServiceHealthMonitoringLog>
{

    List<ServiceHealthMonitoringLog> getLeGeList(MonitoringLog m);


    IPage<ServiceHealthMonitoringLog> getQuestionStudent(Page page, String user,
                                                                String service,
                                                                String ip);

}
