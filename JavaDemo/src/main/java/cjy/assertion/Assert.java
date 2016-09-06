package cjy.assertion;

public class Assert {
    public static void main(String[] args) {
        boolean isOk = 1 < 2;
        assert isOk;
        System.out.println("程序正常");
    }
}
