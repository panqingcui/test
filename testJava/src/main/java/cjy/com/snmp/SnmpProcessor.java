/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.snmp;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.MessageException;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.mp.StateReference;
import org.snmp4j.mp.StatusInformation;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivAES192;
import org.snmp4j.security.PrivAES256;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

/**
 * <p>JAVA实现的SNMP代理部分。<p>
 * 
 * 创建日期 2013-2-7<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class SnmpProcessor implements CommandResponder {
    private MultiThreadedMessageDispatcher dispatcher;
    private Address listenAddress;
    private ThreadPool threadPool;
    private OID authProtocol;
    private OID privProtocol;
    private OctetString privPassphrase;
    private OctetString authPassphrase;
    private OctetString securityName = new OctetString();
    private int numDispatcherThreads = 2;
    private int version;
    private String host;

    public SnmpProcessor(String host, String user, String authProtocol, String authPasshrase, String privProtocol,
            String privPassphrase, int version) {
        this.authPassphrase = new OctetString(authPasshrase);
        this.securityName = new OctetString(user);
        this.privPassphrase = new OctetString(privPassphrase);
        this.version = version;
        this.host = host;
        if (authProtocol.equals("MD5")) {
            this.authProtocol = AuthMD5.ID;
        } else if (authProtocol.equals("SHA")) {
            this.authProtocol = AuthSHA.ID;
        }
        if (privProtocol.equals("DES")) {
            this.privProtocol = PrivDES.ID;
        } else if ((privProtocol.equals("AES128")) || (privProtocol.equals("AES"))) {
            this.privProtocol = PrivAES128.ID;
        } else if (privProtocol.equals("AES192")) {
            this.privProtocol = PrivAES192.ID;
        } else if (privProtocol.equals("AES256")) {
            this.privProtocol = PrivAES256.ID;
        }
    }

    public synchronized void run() {
        try {
            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void init() throws Exception {
        try {
            threadPool = ThreadPool.create("Trap", numDispatcherThreads);
            dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
            listenAddress = GenericAddress.parse(host);
            TransportMapping<?> transport;
            if (listenAddress instanceof UdpAddress) {
                transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
            } else {
                transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
            }
            dispatcher.addMessageProcessingModel(new MPv1());
            dispatcher.addMessageProcessingModel(new MPv2c());
            dispatcher.addMessageProcessingModel(new MPv3());
            SecurityProtocols.getInstance().addDefaultProtocols();
            Snmp snmp = new Snmp(dispatcher, transport);
            if (version == SnmpConstants.version3) {
                USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
                SecurityModels.getInstance().addSecurityModel(usm);
                // Add the configured user to the USM
                addUsmUser(snmp);
            }
            snmp.addCommandResponder(this);
            transport.listen();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addUsmUser(Snmp snmp) {
        snmp.getUSM().addUser(securityName,
                new UsmUser(securityName, authProtocol, authPassphrase, privProtocol, privPassphrase));
    }

    public String getResponse(String cmdType, String oid, String value) {
        return "lkj";
    }

    public synchronized void processPdu(CommandResponderEvent aEvent) {
        PDU pdu = aEvent.getPDU();
        if (pdu == null) {
            return;
        }
        String cmdType = "";
        OID oid = pdu.get(0).getOid();
        String oidStr = oid.toString();
        System.out.println(oidStr);
        String value = null == pdu.get(0).getVariable().toString() ? "" : pdu.get(0).getVariable().toString();
        System.out.println(value);
        switch (pdu.getType()) {
        case PDU.GET:
            cmdType = "0";
            break;
        case PDU.GETNEXT:
            cmdType = "1";
            break;
        case PDU.SET:
            cmdType = "3";
            break;
        default:
            break;
        }
        StatusInformation statusInformation = new StatusInformation();
        StateReference stateRef = aEvent.getStateReference();
        try {
            pdu.setType(PDU.RESPONSE);
            String response = getResponse(cmdType, oidStr, value);
            Variable rariable = null;
            rariable = new OctetString(response);
            pdu.set(0, new VariableBinding(oid, rariable));
            aEvent.getMessageDispatcher().returnResponsePdu(aEvent.getMessageProcessingModel(),
                    aEvent.getSecurityModel(), aEvent.getSecurityName(), aEvent.getSecurityLevel(), pdu,
                    aEvent.getMaxSizeResponsePDU(), stateRef, statusInformation);
        } catch (MessageException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SnmpProcessor a = new SnmpProcessor("udp:192.168.1.201/161", "lkj", "MD5", "mysnmplkj", "DES", "mysnmplkj",
                SnmpConstants.version3);
        a.run();
    }
}
