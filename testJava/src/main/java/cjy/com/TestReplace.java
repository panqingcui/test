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
package cjy.com;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-8-8<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestReplace {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> groupIDList = new ArrayList<String>();
        groupIDList.add("1");
        groupIDList.add("2");
        groupIDList.add("3");
        List<String> groupIDRedisList = new ArrayList<String>();
        for (String groupID : groupIDList) {
            groupIDRedisList.add(groupID + "groupID");
        }
        for (String groupID : groupIDRedisList) {
            System.out.println("groupID: " + groupID);
        }
    }
}
