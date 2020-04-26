package com.atliwen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atliwen.entity.ServiceHealthMonitoringLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
public interface ServiceHealthMonitoringLogMapper extends BaseMapper<ServiceHealthMonitoringLog> {
    @Select("<script>" +
            "" +
            "SELECT a.* from  service_health_monitoring_log  as a ," +
            " (" +
            "SELECT max(createdate) as ct ,service from  service_health_monitoring_log " +
            "where   1=1 " +
            "<if test='user != null'>" +
            "and  user = #{user}" +
            "</if>" +
            "<if test='service != null'>" +
            "and  service= #{service}  " +
            "</if>" +
            "<if test='ip != null'>" +
            "and  ip = #{ip}" +
            "</if>" +
            "GROUP BY  service " +
            ") as b " +
            "where  " +
            "a.service= b.service and a.createdate=b.ct  ORDER BY  state " +
            "" +
            "</script>")
    IPage<ServiceHealthMonitoringLog> getQuestionStudent(Page page, @Param("user") String user,
                                                         @Param("service") String service,
                                                         @Param("ip") String ip);
}
