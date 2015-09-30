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
package cjy.com.list.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>测试类。<p>
 * 
 * 创建日期 2012-11-23<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class SortTest {
    public static void main(String[] args) {
        List<User> userlist = new ArrayList<User>();
        userlist.add(new User("dd", "4"));
        userlist.add(new User("aa", "1"));
        userlist.add(new User("ee", "5"));
        userlist.add(new User("bb", "2"));
        userlist.add(new User("ff", "5"));
        userlist.add(new User("cc", "3"));
        userlist.add(new User("gg", "6"));
        ComparatorUser comparator = new ComparatorUser();
        Collections.sort(userlist, comparator);
        for (int i = 0; i < userlist.size(); i++) {
            User user_temp = (User) userlist.get(i);
            System.out.println(user_temp.getAge() + ", " + user_temp.getName());
        }
    }
}
