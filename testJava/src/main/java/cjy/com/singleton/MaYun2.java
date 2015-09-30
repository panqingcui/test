package cjy.com.singleton;

public class MaYun2 {
    private static MaYun2 instance = new MaYun2();

    public static MaYun2 getInstance() {
        return instance;
    }

    private MaYun2() {
        System.out.println("MaYun2 Create ... ");
    }

    public void splitAlipay() {
        System.out.println("Alipay是我的啦！看你丫Yahoo绿眉绿眼的望着。。。");
    }
}
