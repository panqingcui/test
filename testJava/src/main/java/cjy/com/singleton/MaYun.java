package cjy.com.singleton;

public class MaYun {
    private static MaYun instance = null;

    private MaYun() {
        System.out.println("MaYun Create ... ");
    }

    public static MaYun getInstance() {
        if (instance == null) {
            instance = new MaYun();
        }
        return instance;
    }

    public void splitAlipay() {
        System.out.println("Alipay是我的啦！看你丫Yahoo绿眉绿眼的望着。。。");
    }
}
