package cjy.comm;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class Test1 {
    public static void main(String[] args) {
        showCommPorts();
    }

    public static void showCommPorts() {
        CommPortIdentifier portID = null;
        List port1Vector = new Vector(32);
        List port2Vector = new Vector(32);
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            portID = (CommPortIdentifier) ports.nextElement();
            // debug("CommPort : "+portID.getName()+"/type:"+portID.getPortType());
            switch (portID.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                port1Vector.add(portID.getName());
                break;
            case CommPortIdentifier.PORT_PARALLEL:
                port2Vector.add(portID.getName());
                break;
            default:
                break;
            }
        }
        System.out.println("PORT_SERIAL   = " + port1Vector);
        System.out.println("PORT_PARALLEL = " + port2Vector);
    }
}
