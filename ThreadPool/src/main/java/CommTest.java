import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class CommTest {
    /**
     * @param args
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String port = "COM3";
        for (Enumeration<CommPortIdentifier> e = CommPortIdentifier.getPortIdentifiers(); e.hasMoreElements();) {
            CommPortIdentifier portId = e.nextElement();
            if (portId.getName().equals(port)) {
                System.out.println("\r");
                System.out.println("找到端口： " + port);
                sendAtTest(portId);
            }
        }
    }

    private static void sendAtTest(CommPortIdentifier portId) throws TooManyListenersException, IOException,
            InterruptedException, UnsupportedCommOperationException, PortInUseException {
        System.out.println("打开端口 …");
        SerialPort serialPort = (SerialPort) portId.open("COM3", 9600);
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
        serialPort.setSerialPortParams(9600,// 波特率
                SerialPort.DATABITS_8,// 数据位数
                SerialPort.STOPBITS_1, // 停止位
                SerialPort.PARITY_NONE);// 奇偶位
        System.out.println("端口已打开。\n发送AT指令 …");
        InputStream inputStream = serialPort.getInputStream();
        OutputStream outputStream = serialPort.getOutputStream();
        // AT%SSMS=10086
        String s = "AT%SSMS=10010" + "\r 101";
        // s += "101";
        outputStream.write(s.getBytes());
        byte[] data = new byte[1024];
        for (int i = inputStream.read(data);; i = inputStream.read(data)) {
            if (i > 0) {
                System.out.println(new String(data, 0, i));
                System.out.println("成功收到指令返回值。");
                break;
            }
        }
        serialPort.close();
        System.out.println("关闭端口。");
    }
}
