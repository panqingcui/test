package cjy.com.sjhs;

import java.text.DecimalFormat;

public class Sin {
    public static void main(String[] args) {
        // 保留2位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double pi = Math.PI;
        int degree = 30;
        double sin30 = Math.sin(degree * pi / 180);
        double cos30 = Math.cos(degree * pi / 180);
        System.out.println("sin30:" + decimalFormat.format(sin30));
        System.out.println("cos30:" + decimalFormat.format(cos30));
        degree = 90;
        double sin90 = Math.sin(degree * pi / 180);
        double cos90 = Math.cos(degree * pi / 180);
        System.out.println("sin90:" + decimalFormat.format(sin90));
        System.out.println("cos90:" + decimalFormat.format(cos90));
        degree = 120;
        double sin120 = Math.sin(degree * pi / 180);
        double cos120 = Math.cos(degree * pi / 180);
        System.out.println("sin120:" + decimalFormat.format(sin120));
        System.out.println("cos120:" + decimalFormat.format(cos120));
        degree = 150;
        double sin150 = Math.sin(degree * pi / 180);
        double cos150 = Math.cos(degree * pi / 180);
        System.out.println("sin150:" + decimalFormat.format(sin150));
        System.out.println("cos150:" + decimalFormat.format(cos150));
    }
}
