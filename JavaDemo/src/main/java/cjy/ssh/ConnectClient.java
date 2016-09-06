package cjy.ssh;

public class ConnectClient {
   /* private static Map<String, Connection> sessionCache = null;

    public Connection getConnection(String hostip, String username, String password) {
        Connection connect = null;
        if (sessionCache == null)
            sessionCache = new HashMap<String, Connection>();
        synchronized (sessionCache) {
            connect = (Connection) sessionCache.get(hostip);
        }
        if (connect != null) {
            return connect;
        }
        connect = connectSshServer(hostip, username, password);
        synchronized (sessionCache) {
            sessionCache.put(hostip, connect);
        }
        return connect;
    }

    private Connection connectSshServer(String hostip, String username, String password) {
        Connection conn = new Connection(hostip);
        boolean isAuthenticated = false;
        try {
            conn.connect();
            isAuthenticated = conn.authenticateWithPassword(username, password);
        } catch (IOException e) {}
        if (isAuthenticated) {
            return conn;
        }
        return null;
    }*/
}
