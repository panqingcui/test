/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */


import java.io.Serializable;
import java.util.List;

/**
 * <p>命令接口，封装命令公共接口。<p>
 * 
 * 创建日期 2012-4-16<br>
 * @author $Author$<br>
 * @version $Revision$ $Date: 2012-05-02 15:18:22 +0800 (周三, 02 五月 2012) $
 * @since 3.0.0
 */
public interface Command extends Serializable {
    /**
     * 获得命令名。
     * @return 命令的名字，不包括组名。
     */
    public String getName();

    /**
     * 获得命令所在的组。
     * @return 命令所在的组的名字。
     */
    public String getGroup();

    /**
     * 设置命令执行的AgentID。
     * @param agentID
     * @return
     */
    public void addAgent(String agentID);

    /**
     * 获取可执行命令的Agent集合字符串。
     * @return
     */
    public List getAgents();

    /**
     * 设置命令执行的serverID。
     * @param serverID
     * @return
     */
    public void addServer(String serverID);

    /**
     * 获取可执行命令的Server集合字符串。
     * @return
     */
    public List getServers();

    /**
     * 获得命令的简短描述。
     * @return 命令的简短描述。
     */
    public String getShortDescription();

    /**
     * 获取命令参数。
     * @return 命令参数对象
     */
    public Serializable getCommandArgs();

    /**
     * 返回sensortoken
     * @return
     */
    public String getSensorToken();

    /**
     * 设置sensortoken
     * @param sensorToken sensor唯一标识
     */
    public void setSensorToken(String sensorToken);
}
