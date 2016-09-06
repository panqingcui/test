/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package test;

import java.util.List;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年5月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class GMapInfo {
    // 默认拓扑图的ID号
    public static final String DEFAULT_GMAP = "AutoDiscoveryGmap";
    // 图唯一标示
    private String id;
    // 图是否自动布局
    private String autoLayout = "true";
    // 节点列表
    private List gNodeList;

    public GMapInfo(String id, String autoLayout, List gNodeInfoList) {
        this.id = id;
        this.autoLayout = autoLayout;
        this.gNodeList = gNodeInfoList;
    }

    // 边列表
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getgNodeList() {
        return gNodeList;
    }

    public void setgNodeList(List gNodeList) {
        this.gNodeList = gNodeList;
    }

    public String getAutoLayout() {
        return autoLayout;
    }

    public void setAutoLayout(String autoLayout) {
        this.autoLayout = autoLayout;
    }
}
