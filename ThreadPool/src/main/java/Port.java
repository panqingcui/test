import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Port {
    private CommPortIdentifier portId;
    private SerialPort serialPort;
    private OutputStreamWriter out;
    private InputStreamReader in;
    private String COMname;
    private static char symbol1 = 13;

    public String getCOMname() {
        return COMname;
    }

    public void setCOMname(String mname) {
        COMname = mname;
    }

    public CommPortIdentifier getPortId() {
        return portId;
    }

    public void setPortId(CommPortIdentifier portId) {
        this.portId = portId;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public OutputStreamWriter getOut() {
        return out;
    }

    public void setOut(OutputStreamWriter out) {
        this.out = out;
    }

    public InputStreamReader getIn() {
        return in;
    }

    public void setIn(InputStreamReader in) {
        this.in = in;
    }

    public boolean isused = true;

    public boolean isIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    /**
     * 打开com口
     * @param portName
     * @return
     */
    public Port(String portName) {
        try {
            portId = CommPortIdentifier.getPortIdentifier(portName);
            if (portId == null) {
                System.out.println("port is null");
            }
            try {
                serialPort = (SerialPort) portId.open(portName, 100000);
            } catch (PortInUseException e) {
                System.gc();
                e.printStackTrace();
            }
            // 下面是得到用于和COM口通讯的输进、输出流。
            try {
                in = new InputStreamReader(serialPort.getInputStream());
                out = new OutputStreamWriter(serialPort.getOutputStream());
            } catch (IOException e) {
                System.gc();
                System.out.println("IOException");
            }
            // 下面是初始化COM口的传输参数，如传输速率：9600等。
            try {
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                setCOMname(portId.getName());
                setIsused(true);
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
                System.gc();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
            System.gc();
        }
    }

    /**
     * 检查SIM是否存在
     * @return
     */
    public boolean chakanPort() {
        try {
            String atCommand = "AT+ccid";
            String strReturn = sendAT(atCommand);
            if (strReturn.indexOf("OK", 0) != -1) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.gc();
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 封闭COM口
     * @return boolean
     */
    public void close() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialPort.close();
        System.gc();
        setIsused(false);
    }

    /**
     * 向串口中写进字符串命令
     * @param s 字符串命令
     * @throws Exception 异常
     */
    public void writeln(String s) throws Exception {
        out.write(s);
        out.write('\r');
        out.flush();
    }

    /**
     * 读取COM命令的返回字符串
     * @return 结果字符串
     * @throws Exception
     */
    public String read() throws Exception {
        int n, i;
        char c;
        String answer = "";
        for (i = 0; i < 100; i++) {
            while (in.ready()) {
                n = in.read();
                if (n != -1) {
                    c = (char) n;
                    answer = answer + c;
                    Thread.sleep(1);
                } else
                    break;
            }
            if (answer.indexOf("OK") != -1) {
                break;
            }
            Thread.sleep(100);
        }
        return answer;
    }

    /**
     * 向串口发送AT指令
     * @param atcommand 指令内容
     * @return 指令返回结果
     * @throws java.rmi.RemoteException
     */
    public String sendAT(String atcommand) throws java.rmi.RemoteException {
        String s = "";
        try {
            Thread.sleep(100);
            writeln(atcommand);
            Thread.sleep(80);
            s = read();
            Thread.sleep(100);
        } catch (Exception e) {
            System.gc();
            System.out.println("ERROR: send AT command failed; " + "Command: " + atcommand + "; Answer: " + s + "  "
                    + e);
        }
        return s;
    }
}
