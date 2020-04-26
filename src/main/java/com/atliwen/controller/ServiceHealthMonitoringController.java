package com.atliwen.controller;

import com.atliwen.controller.base.BaseController;
import com.atliwen.entity.ServiceHealthMonitoring;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
@Controller
@CrossOrigin
@RestController
@RequestMapping("/service-health-monitoring")
public class ServiceHealthMonitoringController extends BaseController<ServiceHealthMonitoring>
{

}
