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
package cjy.com.string;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>java开发技术调用rendom函数，随机生成32位不重复的字符。<p>
 * 
 * 创建日期 2012-11-28<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class GetRendomString {
    // 循环输出50个32位随机数
    public static void main(String[] args) {
        // 定义随机字符种类
        StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        // buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        // buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
        buf.append(",1,2,3,4,5,6,7,8,9,0");
        String[] arr = buf.toString().split(",");
        List<String> pswdList = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            pswdList.add(getPswd(arr));
        }
        for (int i = 0; i < pswdList.size(); i++) {
            System.out.println("生成的32位随机码是：  " + pswdList.get(i));
        }
    }

    /*
     * 随机生成32位字符
     */
    public static String getPswd(String[] arr) {
        StringBuffer b = new StringBuffer();
        java.util.Random r;
        int k;
        for (int i = 0; i < 32; i++) {
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(arr[Math.abs(k % 36)]));
        }
        return b.toString();
    }
}
