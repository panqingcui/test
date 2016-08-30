/**
 * 
 * <p>获取ssh连接。<p>
 * 
 * 创建日期 2014年3月17日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ConnectClient {
   /* private static Map<String, Connection> sessionCache = null;

    public Connection getConnection() {
        // FileInputStream is;
        Connection connect = null;
        // is = new FileInputStream(new File("ssh.properties"));
        // Properties pro = new Properties();
        // pro.load(is);
        // String hostip = pro.getProperty("ip");
        // String username = pro.getProperty("uname");
        // String password = pro.getProperty("password");
        if (sessionCache == null)
            sessionCache = new HashMap<String, Connection>();
        synchronized (sessionCache) {
            connect = (Connection) sessionCache.get(null);
        }
        if (connect != null) {
            return connect;
        }
        connect = connectSshServer(null, null, null);
        synchronized (sessionCache) {
            sessionCache.put(null, connect);
        }
        return connect;
    }

    private Connection connectSshServer(String hostip, String username, String password) {
        Connection conn = new Connection("127.0.0.1");
        boolean isAuthenticated = false;
        try {
            conn.connect();
            isAuthenticated = conn.authenticateWithPassword("root", "antsoldier1234");
        } catch (IOException e) {}
        if (isAuthenticated) {
            return conn;
        }
        return null;
    }

    public static void main(String[] args) {
        ConnectClient client = new ConnectClient();
        Connection conn = client.getConnection();
        List listbuf = runCommand(conn, "ls -t");
        System.out.println(listbuf);
    }

    public static List runCommand(Connection conn, String cmd) {
        List values = new ArrayList();
        try {
            Session sess = conn.openSession();
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                // System.out.println(line);
                values.add(line);
            }
            sess.close();
        } catch (IOException e) {
            // e.printStackTrace(System.err);
            // System.exit(2);
        }
        return values;
    }*/
}
