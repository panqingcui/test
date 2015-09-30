package cjy.com.snmp;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

/**
 * 本类用于监听代理进程的Trap信息
 * 
 * @author YJS
 * 
 */
public class TrapServer {// implements CommandResponder, extends Service
    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;

    public TrapServer() {
        // BasicConfigurator.configure();
    }

    private void init() throws UnknownHostException, IOException {
        threadPool = ThreadPool.create("Trap", 10);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
        // 本地IP与监听端口
        listenAddress = GenericAddress.parse(System.getProperty("snmp4j.listenAddress", "udp:192.168.1.247/161"));
        TransportMapping transport;
        // 对TCP与UDP协议进行处理
        if (listenAddress instanceof UdpAddress) {
            transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
        } else {
            transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
        }
        snmp = new Snmp(dispatcher, transport);
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        snmp.listen();
    }

    public void run() {
        try {
            init();
            snmp.addCommandResponder((CommandResponder) this);
            System.out.println("TrapServer服务准备就绪");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 实现CommandResponder的processPdu方法, 用于处理传入的请求、PDU等信息 当接收到trap时，会自动进入这个方法
     * 
     * @param respEvnt
     */
    public void processPdu(CommandResponderEvent respEvnt) {
        // 解析Response
        if (respEvnt != null && respEvnt.getPDU() != null) {
            System.out
                    .println("****************************************************************************************************");
            PDU pdu = respEvnt.getPDU();
            Date date = new Date();
            SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(from.format(date));
            System.out.println("respEvnt:" + respEvnt.toString());
            System.out.println("PDU:" + pdu.toString());
            System.out.println("type:" + pdu.getType());
            System.out.println("PeerAddress" + respEvnt.getPeerAddress().toString());
            System.out.println("ErrorStatusText" + pdu.getErrorStatusText());
            System.out.println("TypeString(0):" + pdu.getTypeString(-92));
            System.out.println("");
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) pdu.getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.elementAt(i);
                System.out.println(recVB.getOid() + " : " + recVB.getVariable());
            }
        }
    }

    public static void main(String[] args) {
        TrapServer multithreadedtrapreceiver = new TrapServer();
        multithreadedtrapreceiver.run();
    }
}
