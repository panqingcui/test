/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>类加载器。<p>
 * 
 * 创建日期 2014年1月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
interface Subject {
    public String say(String name, int age);
}

class MySubject implements Subject {
    @Override
    public String say(String name, int age) {
        return name + " " + age;
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = method.invoke(this.obj, args);
        return o;
    }

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}

public class Demo_013 {
    public static void main(String[] args) {
        MyInvocationHandler invocation = new MyInvocationHandler();
        Subject subject = (Subject) invocation.bind(new MySubject());
        String info = subject.say("李四", 20);
        System.out.println(info);
        // System.out.println("类加载器 :" + new Demo_013().getClass().getClassLoader().getClass().getName());
    }
}
