import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sound.midi.Patch;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        while (true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100000000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 将long型的IP地址转换为String型
     * @param ipaddress
     * @return
     */
    public static String longToString(long ipaddress) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((ipaddress >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x000000FF)));
        return sb.toString();
    }

    public static long stringToLong(String ipaddress) {
        long[] ip = new long[4];
        int position1 = ipaddress.indexOf(".");
        int position2 = ipaddress.indexOf(".", position1 + 1);
        int position3 = ipaddress.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(ipaddress.substring(0, position1));
        ip[1] = Long.parseLong(ipaddress.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(ipaddress.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(ipaddress.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static Long getTwoHoursAgoTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, Calendar.HOUR - 2); // 把时间设置为当前时间-1小时，同理，也可以设置其他时间
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取到完整的时间
        // sdf.format(cal.getTime());
        return cal.getTime().getTime();
    }

    public static List<Patch> getInstallPatch(String list) {
        List<Patch> patchList = new ArrayList<Patch>();
        // 补丁一行的信息
        String patchLine = list;
        int len;
        // 字符中包含@，需要截串
        if ((len = patchLine.indexOf("@")) != -1) {
            patchLine = patchLine.substring(0, len - 1);
        }
        // 根据空格分组
        String[] patchs = patchLine.split("\\.");
        String version = "";
        if (patchs.length == 2) {
            // 补丁名称
            String patchName = patchs[0];
            // 扩展名
            String[] extension = patchs[1].split(" ");
            // String version = extension[extension.length - 1];
            for (int j = 1; j < extension.length - 1; j++) {
                version += extension[j];
            }
        }
        // 补丁名称
        // String patchName = patchs[0];
        // // 扩展名
        // String[] extension = patchs[1].split(" ");
        // String version = extension[extension.length - 1] + ".";
        // // 获取版本信息
        // for (int j = 2; j < patchs.length - 1; j++) {
        // version += patchs[j] + ".";
        // }
        // // 最后一个字符
        // String[] last = patchs[patchs.length - 1].split(" ");
        // int l;
        // if ((l = last[0].indexOf("@")) != -1) {
        // last[0] = last[0].substring(0, l - 1);
        // }
        // version = version + last[0];
        System.out.println(version);
        return patchList;
    }
}
