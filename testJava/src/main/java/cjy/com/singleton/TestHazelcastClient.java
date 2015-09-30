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
package cjy.com.singleton;

/**
 * <p>内部类单例模式测试。<p>
 * 
 * 创建日期 2013-7-23<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestHazelcastClient {
    public static void main(String[] args) {
        System.out.println("TestHazelcastClient main start ...");
        // 同时启动1000个线程，看看实际结果
        Thread threads[] = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 内部类单例模式
                    // HazelcastClient hazelcastClient =
                    // HazelcastClient.getInstance();
                    // hazelcastClient.getClient();
                    // 懒汉式单例模式
                    // MaYun maYun = MaYun.getInstance();
                    // maYun.splitAlipay();
                    // 饿汉式单例模式
                    // MaYun2 maYun2 = MaYun2.getInstance();
                    // maYun2.splitAlipay();
                    // 枚举单例模式
                    Elvis.INSTANCE.leaveTheBuilding();
                }
            });
            threads[i].start();
        }
        System.out.println("TestHazelcastClient main thread start end ...");
        // 等待所有线程运行完成
        for (int i = 0; i < 1000; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("TestHazelcastClient main end ...");
    }
}
