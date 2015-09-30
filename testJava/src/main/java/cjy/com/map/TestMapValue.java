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
package cjy.com.map;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>测试map赋值取值。<p>
 * 
 * 创建日期 2013-7-5<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestMapValue {
    public static void main(String[] args) {
        Map<Integer, Map<String, Integer>> measStatusMap = new HashMap<Integer, Map<String, Integer>>();
        // Map<String, Integer> testMap = new HashMap<String, Integer>();
        // testMap.put("FaultNum", 1);
        // measStatusMap.put(1, testMap);
        // 资源故障告警事件数量+1
        Integer resourceId = 1;
        Map<String, Integer> measMap = measStatusMap.get(resourceId);
        if (measMap == null) {
            measMap = new HashMap<String, Integer>();
            measStatusMap.put(resourceId, measMap);
        }
        System.out.println("measMap:" + measMap);
        System.out.println("measStatusMap:" + measStatusMap);
        Integer resourceErrorNum = measMap.get("FaultNum");
        if (resourceErrorNum == null) {
            resourceErrorNum = 1;
        } else {
            resourceErrorNum++;
        }
        measMap.put("FaultNum", resourceErrorNum);
        System.out.println("measMap:" + measMap);
        // measStatusMap.put(resourceId, measMap);
        System.out.println("measStatusMap:" + measStatusMap);
    }
}
