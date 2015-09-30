package cjy.stringbuilder;

import java.util.Timer;

class Test {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TestTask t = new TestTask();
        timer.schedule(t, 0, 2000);
    }
}
