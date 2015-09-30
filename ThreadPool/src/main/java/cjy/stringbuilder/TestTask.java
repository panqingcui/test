package cjy.stringbuilder;

import java.util.TimerTask;

public class TestTask extends TimerTask {
    StringBuilderUtil sbd = new StringBuilderUtil();

    @Override
    public void run() {
        sbd.add();
    }
}
