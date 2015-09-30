/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.json;

import java.util.Set;

/**
 * <p>度量告警对象。<p>
 * 
 * 创建日期 2013-7-4<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class MeasAlarm {
    // 资源ID
    private Integer resourceID;
    // 状态
    private Integer state;
    // 告警数量
    private Integer warnNum;
    // 故障数量
    private Integer faultNum;
    // 故障告警列表
    private Set<Integer> faultAlarmSet;
    // 阈值告警列表
    private Set<Integer> thresholdAlarmSet;

    public MeasAlarm() {}

    public MeasAlarm(Integer resourceID, Integer state, Integer warnNum, Integer faultNum, Set<Integer> faultAlarmSet,
            Set<Integer> thresholdAlarmSet) {
        this.resourceID = resourceID;
        this.state = state;
        this.warnNum = warnNum;
        this.faultNum = faultNum;
        this.faultAlarmSet = faultAlarmSet;
        this.thresholdAlarmSet = thresholdAlarmSet;
    }

    /**
     * @return the resourceID
     */
    public Integer getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID the resourceID to set
     */
    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the warnNum
     */
    public Integer getWarnNum() {
        return warnNum;
    }

    /**
     * @param warnNum the warnNum to set
     */
    public void setWarnNum(Integer warnNum) {
        this.warnNum = warnNum;
    }

    /**
     * @return the faultNum
     */
    public Integer getFaultNum() {
        return faultNum;
    }

    /**
     * @param faultNum the faultNum to set
     */
    public void setFaultNum(Integer faultNum) {
        this.faultNum = faultNum;
    }

    /**
     * @return the faultAlarmSet
     */
    public Set<Integer> getFaultAlarmSet() {
        return faultAlarmSet;
    }

    /**
     * @param faultAlarmSet the faultAlarmSet to set
     */
    public void setFaultAlarmSet(Set<Integer> faultAlarmSet) {
        this.faultAlarmSet = faultAlarmSet;
    }

    /**
     * @return the thresholdAlarmSet
     */
    public Set<Integer> getThresholdAlarmSet() {
        return thresholdAlarmSet;
    }

    /**
     * @param thresholdAlarmSet the thresholdAlarmSet to set
     */
    public void setThresholdAlarmSet(Set<Integer> thresholdAlarmSet) {
        this.thresholdAlarmSet = thresholdAlarmSet;
    }
}
