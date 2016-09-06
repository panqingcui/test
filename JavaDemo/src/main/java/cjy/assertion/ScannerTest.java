package cjy.assertion;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************");
        while (true) {
            System.out.println("请输入数字进行选择：");
            int id = sc.nextInt();
            switch (id) {
            case 2:
                System.out.println("执行购物结算");
                break;
            case 5:
                System.out.println("输入错误，请重新输入");
                continue;
            default:
                break;
            }
            // if (id != 2) {
            // System.out.println("输入错误，请重新输入");
            // continue;
            // } else if (id == 2) {
            // System.out.println("执行购物结算");
            // break;
            // }
        }
    }
}
