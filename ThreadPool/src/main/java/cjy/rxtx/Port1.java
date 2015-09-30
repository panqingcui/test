package cjy.rxtx;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class Port1 {
    InputStream inputStream; // 从串口来的输入流
    OutputStream outputStream;// 向串口输出的流
    SerialPort serialPort; // 串口的引用
    CommPortIdentifier portId;
    long start = 0;
    DataOutputStream dops;

    public static void main(String[] args) throws UnsupportedCommOperationException, PortInUseException {
        System.out.println(System.getProperty("java.library.path"));
        Port1 port = new Port1();
        if (port.getPort()) {
            try {
                port.openPort();
                String[] names = new String[] {"18615183726" };
                for (String s : names) {
                    long sr = System.currentTimeMillis();
                    port.sendAtMes(s, "故障告警:working上的MySQL_3306在无法访问");
                    long e = System.currentTimeMillis();
                    System.out.println("用时：" + (e - sr));
                    Thread.sleep(3000);
                }
                port.ClosePort();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void openPort() throws UnsupportedCommOperationException, PortInUseException, IOException {
        serialPort = (SerialPort) portId.open("COM3", 6000);
        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, // 设置串口读写参数
                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        inputStream = serialPort.getInputStream();
        outputStream = serialPort.getOutputStream();
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }

    /**
     * 端口是否打开
     * @return
     */
    public boolean getPort() {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers(); // 得到当前连接上的端口
        while (portList.hasMoreElements()) {
            CommPortIdentifier temp = (CommPortIdentifier) portList.nextElement();
            if (temp.getPortType() == CommPortIdentifier.PORT_SERIAL) {// 判断如果端口类型是串口
                System.out.println(temp.getName());
                if (temp.getName().equals("COM5")) { // 判断如果端口已经启动就连接
                    portId = temp;
                    System.out.println("端口打开");
                    return true;
                }
            }
        }
        System.out.println("端口未开");
        return false;
    }

    /**
     * 发送指令
     * @param phone
     * @param mes
     * @throws IOException
     * @throws InterruptedException
     */
    public void sendAtMes(String phone, String mes) throws IOException, InterruptedException {
        // String atContent = "AT%SSMS=" + phone + "\r" + mes;
        String atContent = "AT+CISMSSEND=" + phone + ",3," + mes + "\r\n";
        outputStream.write(atContent.getBytes("GB2312"));
        outputStream.flush();
        start = System.currentTimeMillis();
        String returnStr = "";
        byte[] data = new byte[1024];
        Thread.sleep(1000);
        for (int i = inputStream.read(data);; i = inputStream.read(data)) {
            returnStr = new String(data, 0, i);
            break;
        }
        System.out.println("" + returnStr);
        if (returnStr.trim().equalsIgnoreCase("OK")) {
            System.out.println("成功");
        } else if (returnStr.trim().equalsIgnoreCase("busy")) {
            System.out.println("繁忙，重新发送");
            Thread.sleep(5000);
            sendAtMes(phone, mes);
        } else {
            System.out.println("发送失败");
        }
    }

    public void ClosePort() {
        if (serialPort != null) {
            serialPort.close();
        }
    }
}
