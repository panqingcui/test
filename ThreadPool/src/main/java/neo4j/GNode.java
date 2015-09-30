/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package neo4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>拓扑图中节点数据bean。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date: 2012-04-28 17:48:53 +0800 (周六, 28 四月 2012) $
 * @since 3.0.0
 */
public class GNode {
    /* 唯一标示 */
    private String id;
    /* x轴坐标 */
    private Double x = 0.0;
    /* y轴坐标 */
    private Double y = 0.0;
    // 子网范围
    private String net = "";
    // 节点对应的边关系
    private List<GLine> adjacencies = new ArrayList<GLine>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public List<GLine> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(List<GLine> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public void addAdjacency(GLine line) {
        this.adjacencies.add(line);
    }

    /**
     * @return the net
     */
    public String getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(String net) {
        this.net = net;
    }
}
