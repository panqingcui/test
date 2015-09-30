/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package neo4j;

/**
 * <p>节点属性键值。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date: 2012-04-28 17:48:53 +0800 (周六, 28 四月 2012) $
 * @since 3.0.0
 */
public class PropertyKey {
    // //图
    /* 图的唯一标示 */
    public static final String MAP_ID = "mapid";
    public static final String MAP_AUTO = "autoLayout";
    /* 是否是根节点 */
    public static final String NODE_ISROOT = "isroot";
    // //节点
    /* 节点唯一标示key */
    public static final String NODE_ID = "nodeid";
    /* x轴坐标key */
    public static final String NODE_X = "nodex";
    /* y轴坐标key */
    public static final String NODE_Y = "nodey";
    /* 子网范围key */
    public static final String NODE_NET = "nodeNet";
    // //边
    /* 边唯一标示 */
    public static final String LINE_ID = "lineid";
    /* 开始节点id */
    public static final String LINE_SNODEID = "sNodeId";
    /* 结束节点id */
    public static final String LINE_ENODEID = "eNodeId";
    // 边的类型
    public static final String LINE_TYPE = "lineType";
    // //索引
    /* 节点索引 */
    public static final String NODES_INDEXNAME = "nodes";
    /* 边索引 */
    public static final String LINES_INDEXNAME = "lines";
}
