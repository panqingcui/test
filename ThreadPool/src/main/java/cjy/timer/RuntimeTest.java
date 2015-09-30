package cjy.timer;

public class RuntimeTest {
    public static boolean flag = false;

    public static void main(String[] args) {
        System.out.println("调用方法processReport");
        processReport("张三");
    }

    public static void processReport(String name) {
        try {
            if (new RuntimeTest().getFlag()) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("i:" + i + name);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getFlag() throws InterruptedException {
        if (flag) {
            return true;
        } else {
            Thread.sleep(5000);
            flag = true;
            getFlag();
        }
        return true;
    }
}
