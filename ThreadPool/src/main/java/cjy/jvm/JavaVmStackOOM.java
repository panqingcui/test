package cjy.jvm;

import org.apache.log4j.Logger;

public class JavaVmStackOOM {
    private static Logger logger = Logger.getLogger(JavaVmStackOOM.class);

    private void dontStop() {
        while (true) {
            logger.info("开启线程：" + Thread.currentThread().getName());
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thead = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    dontStop();
                }
            });
            thead.start();
        }
    }

    public static void main(String[] args) {
        JavaVmStackOOM OOM = new JavaVmStackOOM();
        OOM.stackLeakByThread();
    }
}
