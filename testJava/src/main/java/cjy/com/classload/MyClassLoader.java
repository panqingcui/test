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
package cjy.com.classload;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-11-15<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class MyClassLoader {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("file:/E:\\projects\\testScanner\\out\\production\\testScanner");
            ClassLoader myloader = new URLClassLoader(new URL[] {url });
            Class c = myloader.loadClass("test.Test3");
            System.out.println("----------");
            Test3 t3 = (Test3) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
