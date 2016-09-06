/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.annotation;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年1月23日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Test {
    private static int count = 0;

    public static void main(String[] args) {
        // System.out.println(stringToLong("192.168.1.121"));
        // Random random = new Random();
        // System.out.println(random.nextInt(11) + 1);
        // Map<String, String> map = new HashMap<String, String>();
        // map.put("1", "李四");
        // map.put("2", "张三");
        // if (map != null) {
        // System.out.println(map);
        // }
    }

    public int set(int sum) {
        count++;
        if (count >= 2) {
            sum = count;
            return sum;
        } else {
            set(sum);
        }
        return sum;
    }
    // public static long stringToLong(String ipaddress) {
    // long[] ip = new long[4];
    // int position1 = ipaddress.indexOf(".");
    // int position2 = ipaddress.indexOf(".", position1 + 1);
    // int position3 = ipaddress.indexOf(".", position2 + 1);
    // ip[0] = Long.parseLong(ipaddress.substring(0, position1));
    // ip[1] = Long.parseLong(ipaddress.substring(position1 + 1, position2));
    // ip[2] = Long.parseLong(ipaddress.substring(position2 + 1, position3));
    // ip[3] = Long.parseLong(ipaddress.substring(position3 + 1));
    // return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    // }
}
