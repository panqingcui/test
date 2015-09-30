package cjy.timer.test;

import java.util.TimerTask;

public class newTask extends TimerTask {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {}
    }

    /**
     * 生成随机数字 1~10
     * @return
     */
    public int createRundomNumber() {
        int a = (int) Math.random() * 10 + 1;
        return a;
    }
}
