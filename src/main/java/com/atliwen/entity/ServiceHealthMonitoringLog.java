package com.atliwen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author atliwen
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceHealthMonitoringLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String service;

    private String ip;

    private String notice;
    @TableField(value = "`user`")
    private String user;

    @TableField(value = "`update`")
    private Date update;

    private Date createdate;

    /**
     *  1 down 2 访问异常 3 发送通知异常  4 成功
     */
    private Integer state;

    private Long calltime;
    @TableField(value = "`data`")
    private String data;

    private String url;

    public ServiceHealthMonitoringLog() {
    }
    public ServiceHealthMonitoringLog(ServiceHealthMonitoring s) {
        this.id = s.getId();
        this.service = s.getService();
        this.ip = s.getIp();
        this.notice = s.getNotice();
        this.user = s.getUser();
        this.createdate = new Date();
        this.state = 0;
        this.url=s.getUrl();
    }

}
