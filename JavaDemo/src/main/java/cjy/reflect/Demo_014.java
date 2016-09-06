/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.reflect;

/**
 * <p>类加载器。<p>
 * 
 * 创建日期 2014年1月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
interface fruit {
    public void eat();
}

class Apple implements fruit {
    @Override
    public void eat() {
        System.out.println("eat apple!");
    }
}

class Orange implements fruit {
    @Override
    public void eat() {
        System.out.println("eat orange!");
    }
}

class FactoryManager {
    public static fruit getInstance(String fruitName) {
        fruit f = null;
        if ("Apple".equals(fruitName)) {
            f = new Apple();
        }
        if ("Orange".equals(fruitName)) {
            f = new Orange();
        }
        return f;
    }
}

public class Demo_014 {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        FactoryManager factory = new FactoryManager();
        fruit f = factory.getInstance("Orange");
        f.eat();
    }
}
