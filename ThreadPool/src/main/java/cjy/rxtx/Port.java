package cjy.rxtx;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;

public class Port extends Thread {
    InputStream inputStream; // 从串口来的输入流
    OutputStream outputStream;// 向串口输出的流
    SerialPort serialPort; // 串口的引用
    CommPortIdentifier portId;
    long start = 0;
    List<String> telList;
    String content;

    public Port() {}

    public Port(List<String> telList, String content) {
        this.telList = telList;
        this.content = content;
    }

    public void run() {}

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        Port port = new Port();
        if (port.getPort()) {
            try {
                port.openPort();
                String[] names = new String[] {"18615183726" };
                for (String s : names) {
                    long sr = System.currentTimeMillis();
                    port.sendAtMes(s, "故" + System.currentTimeMillis());
                    long e = System.currentTimeMillis();
                    System.out.println("用时：" + (e - sr));
                    Thread.sleep(3000);
                }
                port.ClosePort();
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            } catch (PortInUseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void openPort() throws UnsupportedCommOperationException, PortInUseException {
        serialPort = (SerialPort) portId.open("COM5", 6000);
        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, // 设置串口读写参数
                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
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

    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 发送指令
     * @param phone
     * @param mes
     * @throws IOException
     * @throws InterruptedException
     */
    public void sendAtMes(String phone, String mes) throws IOException, InterruptedException {
        inputStream = serialPort.getInputStream();
        // outputStream = serialPort.getOutputStream();
        OutputStream os = new BufferedOutputStream(serialPort.getOutputStream());
        // outputStream
        // mes = toHexString(mes);
        String atContent = "AT+CISMSSEND=" + phone + ",3," + mes + "\r\n";
        os.write(atContent.getBytes("gb2312"));
        os.flush();
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
            System.out.println("成功" + mes);
        } else if (returnStr.trim().equalsIgnoreCase("busy")) {
            System.out.println("繁忙，重新发送");
            Thread.sleep(8000);
            sendAtMes(phone, mes + System.currentTimeMillis());
        } else {
            System.out.println("发送失败");
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public void ClosePort() {
        if (serialPort != null) {
            serialPort.close();
        }
    }
}
