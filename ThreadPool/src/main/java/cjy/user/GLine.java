/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.user;

/**
 * <p>拓扑图中边数据bean。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class GLine {
    // 唯一标示
    private String id;
    // 开始节点id
    private String nodeFrom;
    // 结束节点id
    private String nodeTo;
    // 类型
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(String nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public String getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(String nodeTo) {
        this.nodeTo = nodeTo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
