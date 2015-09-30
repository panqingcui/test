package cjy.rxtx;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        Port port = new Port();
        if (port.getPort()) {
            try {
                port.openPort();
                String[] names = new String[] {"10010", "18615183726", "10010" };
                for (String st : names) {
                    long s = System.currentTimeMillis();
                    char[] c = "您好".toCharArray();
                    port.sendAtMes(st, "故障告警：working上的MySQL_3306在10:24无法访问");
                    long e = System.currentTimeMillis();
                    System.out.println("用时：" + (e - s));
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
}
