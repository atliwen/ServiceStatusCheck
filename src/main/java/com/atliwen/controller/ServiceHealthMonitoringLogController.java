package com.atliwen.controller;


import com.atliwen.domain.MonitoringLog;
import com.atliwen.domain.ResponseEntity;
import com.atliwen.domain.groups.IPage;
import com.atliwen.domain.page.QueryPage;
import com.atliwen.entity.ServiceHealthMonitoringLog;
import com.atliwen.service.IServiceHealthMonitoringLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
@Controller
@CrossOrigin
@RestController
@RequestMapping("/service-health-monitoring-log")
@ResponseBody
@Slf4j
public class ServiceHealthMonitoringLogController extends com.atliwen.controller.base.BaseController<ServiceHealthMonitoringLog>
{
    @Autowired
    IServiceHealthMonitoringLogService logService;

    @ApiOperation(value = "时间范围查询")
    @PostMapping("/getLeGeList")
    public ResponseEntity getLeGeList(@RequestBody MonitoringLog m) {
        try {
            List datas = logService.getLeGeList(m);
            return new ResponseEntity<>(datas);
        } catch (Exception e) {
            log.error(" 获取所以信息异常 ", e);
            return new ResponseEntity<>(false, "5001");
        }
    }

    @ApiOperation(value = "带有条件的分页查询")
    @PostMapping("/queryListPage")
    @ResponseBody
    public ResponseEntity queryListPage(@Validated(IPage.class) @RequestBody QueryPage<ServiceHealthMonitoringLog> p) {
        try {

            com.baomidou.mybatisplus.core.metadata.IPage datas= logService.getQuestionStudent(p.getPage(),p.getWhere().getUser(),p.getWhere().getService(),p.getWhere().getService());
            //Page datas = baseService.page(p.getPage(), new QueryWrapper<>(p.getWhere()));
            return new ResponseEntity<>(datas);
        } catch (Exception e) {
            log.error(" 获取所以信息异常 ", e);
            return new ResponseEntity<>(false, "5001");
        }
    }
}
