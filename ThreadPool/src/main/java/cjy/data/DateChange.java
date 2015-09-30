package cjy.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChange {
    public static void main(String[] args) {
        long l = 1389745680000l;
        Date date = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
