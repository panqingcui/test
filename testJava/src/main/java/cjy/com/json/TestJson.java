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

import java.util.HashSet;
import java.util.Set;

/**
 * <p>测试度量告警JSON序列化。<p>
 * 
 * 创建日期 2013-7-4<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestJson {
    public static void main(String[] args) {
        MeasAlarm measAlarm = new MeasAlarm();
        measAlarm.setResourceID(1);
        measAlarm.setState(2);
        measAlarm.setFaultNum(3);
        measAlarm.setWarnNum(3);
        // 添加故障告警
        Set<Integer> faultAlarmSet = new HashSet<Integer>();
        faultAlarmSet.add(666);
        faultAlarmSet.add(667);
        faultAlarmSet.add(668);
        faultAlarmSet.add(666);
        measAlarm.setFaultAlarmSet(faultAlarmSet);
        // 添加阈值告警
        Set<Integer> thresholdAlarmSet = new HashSet<Integer>();
        thresholdAlarmSet.add(888);
        thresholdAlarmSet.add(988);
        thresholdAlarmSet.add(999);
        thresholdAlarmSet.add(999);
        measAlarm.setThresholdAlarmSet(thresholdAlarmSet);
        // 使用JSON将度量告警对象序列化
        String measAlarmStr = JsonUtil.convertObjToString(measAlarm);
        System.out.println(measAlarmStr);
        // 将JSON字符串转化为度量告警对象
        MeasAlarm measAlarmJson = JsonUtil.convertStringToObj(measAlarmStr, MeasAlarm.class);
        System.out.println("resourceId:" + measAlarmJson.getResourceID());
        Set<Integer> faultAlarmSetJson = measAlarmJson.getFaultAlarmSet();
        System.out.println("faultAlarmSetJson:" + faultAlarmSetJson);
        Integer measId = 666;
        boolean hasMeas = faultAlarmSetJson.contains(measId);
        if (!hasMeas) {
            System.out.println("故障度量元ID不包含" + measId);
        } else {
            System.out.println("故障度量元ID为" + measId);
            faultAlarmSetJson.remove(measId);
            System.out.println("删除度量元ID为" + measId + "的度量元后，faultAlarmSetJson:" + faultAlarmSetJson);
        }
    }
}
