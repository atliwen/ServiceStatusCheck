package com.atliwen.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 李文
 * @create 2020-04-20 12:23
 **/
@Data
public class MonitoringLog
{
    String user;
    String service;
    String ip;
    Date le;
    Date ge;
}
