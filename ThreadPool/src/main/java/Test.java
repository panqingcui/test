import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sound.midi.Patch;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        // System.out.println(sdf.format(new Date()));
        // System.out.println(new Date().getTime());
        // TopoStartScanCommand tsc = new TopoStartScanCommand();
        // boolean isconfig = !("Win32_Remote_Terminal".contains("Terminal"));
        // System.out.println(isconfig);
        // }
        // System.out.println(23 % 12);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // long l = 1409241600000l;
        // Date date = new Date(l);
        // System.out.println(sdf.format(date));
        // List list = new ArrayList();
        // User user = new User();
        // user.setId("1");
        // user.setName("1");
        // list.add(user);
        // user.setId("2");
        // user.setName("2");
        // list.add(user);
        // user.setId("3");
        // user.setName("3");
        // list.add(user);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // long l = 1420460700000l;
        // Date date = new Date(l);
        // System.out.println(sdf.format(date));
        // // System.out.println("172.19.104.254_switch_25".contains("Switch"));
        // Pattern pattern = Pattern.compile(".+@[^.@]+(\\.[^.@]+)+$");
        // String str = "pan-qing-cui@163.com";
        // System.out.println(str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]w+)*$"));
        // String from = "蚁巡<chenjunying@antrol.co>";
        // int leftSign = (from = from.trim()).charAt(from.length() - 1) == '>' ?
        // from.lastIndexOf('<') : -1;
        // System.out.println(leftSign);
        // long ipaddress = 2886952714l;
        // System.out.println(longToString(ipaddress));
        // String s = "";
        // if ("".equals(s.trim())) {
        // System.out.println("s");
        // }
        long ip = 2886953231l;
        System.out.println(longToString(ip));
        ;
        // long ll = 1404144000000l;
        // System.out.println(longToString(ll));
        // // // String s1 = null;
        // // // System.out.println(s1.trim());
        // // System.out.println(stringToLong("10.0.0.2"));
        // String str = "192.168.83.201";
        // System.out.println(stringToLong(str));
        // String s = "192.168.2.255无法访问safadfasdfdsaf192.168.2.255无法访问";
        // int i = s.lastIndexOf("192.168.2.255无法访问");
        // s = s.substring(0, i);
        // System.out.println(s);
        // long s = System.currentTimeMillis();
        // for (int i = 0; i < 6000; i++) {
        // for (int j = 0; j < 6000; j++) {}
        // }
        // long e = System.currentTimeMillis() - s;
        // System.out.println(e);
        // List<String> list1 = new ArrayList<String>();
        // List<String> list2 = new ArrayList<String>();
        // list1.add("1");
        // list1.add("2");
        // list1.add("VMware Virtual Ethernet Adapter for VMnet8 ");
        // list2.add("1");
        // list2.add("VMware Virtual Ethernet Adapter for VMnet8");
        // for (int i = 0; i < list1.size(); i++) {
        // if (!list2.contains(list1.get(i))) {
        // System.out.println(list1.get(i));
        // }
        // }
        // String str = "内存利用率超出阈值范围,当前值：75.15%,正常范围：0%, - 58.1%,发生时间：18:19";
        // System.out.println(str.getBytes("utf-8").length);
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
