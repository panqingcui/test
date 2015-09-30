/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-10-17<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TopoScan_args extends AgentRemoteValue {
    /* 扫描目标，多个ip或者多个网段之间使用空格，例如：192.168.0.0/8 10.0.0，1，3-7.0-255 */
    private String target = null;

    /**
     * 获取扫描目标
     * @return
     */
    public String getTarget() {
        return super.getValue("target");
    }

    /**
     * 设置扫描目标
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
        super.setValue("target", this.target);
    }
}
