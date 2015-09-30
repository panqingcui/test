import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        // Process process = Runtime.getRuntime().exec("yum list installed ");
        // // 检查NMap扫描结果
        // InputStream is = process.getInputStream();
        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // boolean flag = false;
        // String buffer = null;
        // List list = new ArrayList();
        // while ((buffer = br.readLine()) != null) {
        // // NMap扫描结果包含Nmap done:时，表示本次命令执行完毕
        // list.add(buffer);
        // }
        // System.out.println(list);
        String str = "告警信息：count在16:27 192.168.2.255无法访问,17:31恢复正常访问";
        System.out.println(str.replace("count", "最近"));
    }
}
