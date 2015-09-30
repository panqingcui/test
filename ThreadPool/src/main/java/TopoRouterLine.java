/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */


/**
 * <p>agent解析hynetd结果文件中subnet中node节点封装边的对象。<p>
 * 
 * 创建日期 2012-10-17<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TopoRouterLine extends TopoLine {
    // 路由对应的子网
    private String net = "";

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
}
