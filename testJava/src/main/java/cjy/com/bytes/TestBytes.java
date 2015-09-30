/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.com.bytes;

import java.io.ByteArrayInputStream;

/**
 * <p>测试byte数组。<p>
 * 
 * 创建日期 2013年12月24日<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestBytes {
    public static void main(String[] args) {
        byte[] deviceCustomProperties = null;
        System.out.println("deviceCustomProperties=" + deviceCustomProperties);
        byte[] byteArray = new byte[0];
        ByteArrayInputStream byteStream = new ByteArrayInputStream(byteArray);
        System.out.println("byteStream=" + byteStream);
    }
}
