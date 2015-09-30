/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月29日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Server {
    // 服务器编号
    private Integer serverId;
    // 设备
    // private Device zyDevice;
    // 是否需要运行期自动发现
    private boolean runtimeAutodiscovery;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    // //
    // public Device getZyDevice() {
    // return zyDevice;
    // }
    //
    // public void setZyDevice(Device zyDevice) {
    // this.zyDevice = zyDevice;
    // }
    public boolean isRuntimeAutodiscovery() {
        return runtimeAutodiscovery;
    }

    public void setRuntimeAutodiscovery(boolean runtimeAutodiscovery) {
        this.runtimeAutodiscovery = runtimeAutodiscovery;
    }
}
