package cjy.gc;

import org.apache.log4j.Logger;

public class FinalizeEscapseGc {
    private static Logger logger = Logger.getLogger(FinalizeEscapseGc.class);
    public static FinalizeEscapseGc SAVE_HOOK = null;

    protected void finalize() throws Throwable {
        super.finalize();
        FinalizeEscapseGc.SAVE_HOOK = this;
    }

    public static void mian(String args[]) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapseGc();
        // 对象第一次自救
        SAVE_HOOK = null;
        System.gc();
        //
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            System.out.println("对象还存在");
        } else {
            System.out.println("对象已经回收");
        }
    }
}
