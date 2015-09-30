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
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2013-4-16<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestMapMaP {
    // 故障告警策略数据记录MAP中记录状态为不可用连续发生次数的KEY名称
    private static final String FAULT_RETRY_NUM = "unavailableNum";
    // 故障告警策略数据记录MAP中记录状态为可用连续发生次数的KEY名称
    private static final String FAULT_RESTORE_NUM = "availableNum";
    // 判断为故障，连续连接不上的次数
    private static final int faultCheckCount = 3;

    public static void main(String[] args) {
        Integer metricId = 1;
        // 设置map内容
        Map<Integer, Map<String, Integer>> faultPolicyMap = new HashMap<Integer, Map<String, Integer>>();
        Map<String, Integer> faultMap = new HashMap<String, Integer>();
        faultMap.put(FAULT_RETRY_NUM, 2);
        faultMap.put(FAULT_RESTORE_NUM, 2);
        faultPolicyMap.put(metricId, faultMap);
        // 取map中的内容测试
        Integer unavailableNum = 1;
        Integer availableNum = 0;
        Map<String, Integer> faultMapTest = faultPolicyMap.get(metricId);
        if (faultMapTest == null) {
            faultMapTest = new HashMap<String, Integer>();
            faultMapTest.put(FAULT_RETRY_NUM, unavailableNum);
            faultMapTest.put(FAULT_RESTORE_NUM, availableNum);
            faultPolicyMap.put(metricId, faultMapTest);
        } else {
            availableNum = faultMapTest.get(FAULT_RESTORE_NUM);
            if (availableNum != null && availableNum > 0) {
                faultMapTest.put(FAULT_RESTORE_NUM, 0);
            }
            unavailableNum = faultMapTest.get(FAULT_RETRY_NUM);
            if (unavailableNum >= faultCheckCount) {
                faultMapTest.put(FAULT_RETRY_NUM, 0);
            } else {
                unavailableNum++;
                faultMapTest.put(FAULT_RETRY_NUM, unavailableNum);
            }
        }
        System.out.println("unavailableNum = " + unavailableNum);
        System.out.println("availableNum = " + availableNum);
        // 测试条件判断
        if (unavailableNum >= faultCheckCount) {
            System.out.println("测试条件判断成立");
        }
        // 删除KEY测试
        faultPolicyMap.remove(metricId);
        System.out.println("删除KEY测试完成");
    }
}
