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
 * <p>拓扑图数据。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class GMap {
    // 默认拓扑图的ID号
    public static final String DEFAULT_GMAP = "AutoDiscoveryGmap";
    // 图唯一标示
    private String id;
    // 图是否自动布局
    private String autoLayout = "true";
    // 节点列表
    private List<GNode> gNodeList;

    public GMap() {}

    public GMap(String id, String autoLayout, List gNodeInfoList) {
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

    public List<GNode> getGNodeList() {
        return gNodeList;
    }

    public void setGNodeList(List<GNode> gNlist) {
        this.gNodeList = gNlist;
    }

    public String getAutoLayout() {
        return autoLayout;
    }

    public void setAutoLayout(String autoLayout) {
        this.autoLayout = autoLayout;
    }
}
