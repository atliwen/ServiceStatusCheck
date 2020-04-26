package com.atliwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atliwen.domain.MonitoringLog;
import com.atliwen.entity.ServiceHealthMonitoringLog;
import com.atliwen.mapper.ServiceHealthMonitoringLogMapper;
import com.atliwen.service.IServiceHealthMonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
@Service
public class ServiceHealthMonitoringLogServiceImpl extends ServiceImpl<ServiceHealthMonitoringLogMapper, ServiceHealthMonitoringLog> implements IServiceHealthMonitoringLogService
{

    @Autowired
    ServiceHealthMonitoringLogMapper mapper;

   public IPage<ServiceHealthMonitoringLog> getQuestionStudent(Page page, String user,
                                                         String service,
                                                         String ip) {
        return mapper.getQuestionStudent(page, user, service, ip);
    }


    public List<ServiceHealthMonitoringLog> getLeGeList(MonitoringLog m) {
        QueryWrapper<ServiceHealthMonitoringLog> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<ServiceHealthMonitoringLog> wrapper = queryWrapper.lambda();
        if (!StringUtils.isEmpty(m.getUser())) {
            wrapper.eq(ServiceHealthMonitoringLog::getUser, m.getUser());
        }
        if (!StringUtils.isEmpty(m.getService())) {
            wrapper.eq(ServiceHealthMonitoringLog::getService, m.getService());
        }
        if (!StringUtils.isEmpty(m.getIp())) {
            wrapper.eq(ServiceHealthMonitoringLog::getIp, m.getIp());
        }

        if (m.getLe() != null) {
            wrapper.le(ServiceHealthMonitoringLog::getCreatedate, m.getLe());
        }
        if (m.getGe() != null) {
            wrapper.ge(ServiceHealthMonitoringLog::getCreatedate, m.getGe());
        }
        return list(queryWrapper);

    }


}
