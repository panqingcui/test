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
package cjy.com.wzjc;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-12-26<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class UrlPingTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Process process =
            // Runtime.getRuntime().exec("ping http://www.google.com.hk/ -t -n 10");
            Process process = Runtime.getRuntime().exec("ping http://www.ddd.ggg -t -n 10");
            InputStreamReader result = new InputStreamReader(process.getInputStream());
            LineNumberReader returnData = new LineNumberReader(result);
            String line = "";
            int average = 0;
            while ((line = returnData.readLine()) != null) {
                if (!line.trim().equals("")) {
                    System.out.println(line);
                    if (line.contains("Average")) {
                        String[] lineStr = line.split(",");
                        for (String str : lineStr) {
                            if (str.contains("Average")) {
                                String[] avgStrArr = str.split("=");
                                String avgStr = avgStrArr[1].replace("ms", "").trim();
                                average = Integer.valueOf(avgStr);
                            }
                        }
                    }
                }
            }
            System.out.println("average = " + average + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
