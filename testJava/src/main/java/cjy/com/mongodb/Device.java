/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月29日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Device implements java.io.Serializable {
    //
    private static final long serialVersionUID = 1L;
    // 设备编号
    private Integer deviceId;
    // 设备名称
    private String deviceName;
    // 设备别名
    private String alias;
    // 正式域名
    private String deviceFqdn;
    // 备注说明
    private String FComment;
    // 设备中的服务器
    private Set<Server> zyServers = new HashSet<Server>(0);

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDeviceFqdn() {
        return deviceFqdn;
    }

    public void setDeviceFqdn(String deviceFqdn) {
        this.deviceFqdn = deviceFqdn;
    }

    public String getFComment() {
        return FComment;
    }

    public void setFComment(String fComment) {
        FComment = fComment;
    }

    public Set<Server> getZyServers() {
        return zyServers;
    }

    public void setZyServers(Set<Server> zyServers) {
        this.zyServers = zyServers;
    }
}
