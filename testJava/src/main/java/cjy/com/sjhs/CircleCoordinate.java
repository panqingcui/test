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
package cjy.com.sjhs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>计算圆上一定角度的点的坐标。<p>
 * 
 * 创建日期 2013-2-19<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class CircleCoordinate {
    public static void main(String[] args) {
        int centerX = 20;
        int centerY = 20;
        int radius = 10;
        int degree = 30;
        List<String> coordinateList = calCoordinate(centerX, centerY, radius, degree);
        if (coordinateList == null) {
            System.err.println("间隔度数不能为0");
        } else {
            for (int i = 1; i <= coordinateList.size(); i++) {
                System.out.println("coordinate" + i + ":" + coordinateList.get(i - 1));
            }
        }
    }

    /**
     * 计算一定角度间隔的圆上点的坐标，顺时针计算
     * @param centerX 圆中心X坐标
     * @param centerY 圆中心Y坐标
     * @param radius 圆半径
     * @param degree 间隔度数
     * @return
     */
    public static List<String> calCoordinate(int centerX, int centerY, int radius, int degree) {
        List<String> coordinateList = new ArrayList<String>();
        if (degree == 0) {
            return null;
        }
        if (degree < 0) {
            degree = Math.abs(degree);
        }
        // 保留2位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int circleDegree = 0;
        while (circleDegree < 360) {
            double x = centerX + radius * Math.sin(circleDegree * Math.PI / 180);
            double y = centerY - radius * Math.cos(circleDegree * Math.PI / 180);
            String coordinate = decimalFormat.format(x) + "," + decimalFormat.format(y);
            coordinateList.add(coordinate);
            circleDegree += degree;
        }
        return coordinateList;
    }
}
