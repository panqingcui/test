package cjy.assertion;

public class AssertDemo1 {
    public static void main(String[] args) {
        try {
            boolean isOK = 1 > 2;
            assert isOK : "程序异常";
            System.out.println("程序正常");
        } catch (Throwable e) {
            System.err.print(e);
        }
    }
}
