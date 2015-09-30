package cjy.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class TimerTaskDemo extends TimerTask {
    private static Logger logger = Logger.getLogger(TimerTaskDemo.class);

    @Override
    public void run() {
        // TODO Auto-generated method stub
        logger.info("任务2..." + System.currentTimeMillis());
    }
}
