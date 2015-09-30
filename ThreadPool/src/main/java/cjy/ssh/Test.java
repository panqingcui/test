package cjy.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Test {
    public static void main(String[] args) {
        ConnectClient client = new ConnectClient();
        Connection conn = client.getConnection("192.168.2.248", "root", "antsoldier1234");
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
    }
}
