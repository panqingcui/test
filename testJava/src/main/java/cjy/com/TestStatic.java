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

/**
 * <p>测试静态变量。<p>
 * 
 * 创建日期 2012-12-3<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestStatic {
    private static boolean fSound = true;
    private static boolean fWindow = true;
    private String name;

    public static void main(String[] args) {
        TestStatic.setfSound(false);
        TestStatic.setfWindow(false);
        TestStatic testStatic = new TestStatic();
        testStatic.setName("TestStatic name");
        System.out.println("TestStatic: fSound=" + TestStatic.isfSound() + ", fWindow=" + TestStatic.isfWindow()
                + ", name=" + testStatic.getName());
    }

    /**
     * @return the fSound
     */
    public static boolean isfSound() {
        return fSound;
    }

    /**
     * @param fSound the fSound to set
     */
    public static void setfSound(boolean fSound) {
        TestStatic.fSound = fSound;
    }

    /**
     * @return the fWindow
     */
    public static boolean isfWindow() {
        return fWindow;
    }

    /**
     * @param fWindow the fWindow to set
     */
    public static void setfWindow(boolean fWindow) {
        TestStatic.fWindow = fWindow;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
