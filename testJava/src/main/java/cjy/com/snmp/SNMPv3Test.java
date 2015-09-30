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

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * <p>使用SNMP4J进行SNMPv3编程。<p>
 * 
 * 创建日期 2013-2-7<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class SNMPv3Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.listen();
        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        // Add User
        UsmUser user = new UsmUser(new OctetString("enocsnmpv3"), AuthMD5.ID, new OctetString("enocsnmpv3pw"),
                PrivDES.ID, new OctetString("enocsnmpv3pk"));
        // If the specified SNMP engine id is specified, this user can only be
        // used with the specified engine ID
        // So if it's not correct, will get an error that can't find a user from
        // the user table.
        // snmp.getUSM().addUser(new OctetString("nmsAdmin"), new
        // OctetString("0002651100"), user);
        snmp.getUSM().addUser(new OctetString("enocsnmpv3"), user);
        UserTarget target = new UserTarget();
        target.setVersion(SnmpConstants.version3);
        target.setAddress(new UdpAddress("192.168.1.202/161"));
        target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
        target.setSecurityName(new OctetString("enocsnmpv3"));
        // 3s
        target.setTimeout(3000);
        target.setRetries(2);
        OctetString contextEngineId = new OctetString("0002651100[02]");
        sendRequest(snmp, createGetPdu(contextEngineId), target);
        // snmpWalk(snmp, target, contextEngineId);
    }

    private static PDU createGetPdu(OctetString contextEngineId) {
        ScopedPDU pdu = new ScopedPDU();
        pdu.setType(PDU.GET);
        // if not set, will be SNMP engine id
        // pdu.setContextEngineID(contextEngineId);
        // must be same as SNMP agent
        // pdu.setContextName(contextName);
        // sysUpTime
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
        // sysName
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0")));
        // expect an no_such_instance error
        // pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));
        return pdu;
    }

    private static void sendRequest(Snmp snmp, PDU pdu, UserTarget target) throws IOException {
        ResponseEvent responseEvent = snmp.send(pdu, target);
        PDU response = responseEvent.getResponse();
        if (response == null) {
            System.out.println("TimeOut...");
        } else {
            if (response.getErrorStatus() == PDU.noError) {
                Vector<? extends VariableBinding> vbs = response.getVariableBindings();
                for (VariableBinding vb : vbs) {
                    String vbType = vb.getVariable().getSyntaxString();
                    System.out.println(vb + " ," + vbType);
                }
            } else {
                System.out.println("Error:" + response.getErrorStatusText());
            }
        }
    }

    private static void snmpWalk(Snmp snmp, UserTarget target, OctetString contextEngineId) {
        TableUtils utils = new TableUtils(snmp, new MyDefaultPDUFactory(PDU.GETNEXT, // GETNEXT
                                                                                     // or
                                                                                     // GETBULK)
                contextEngineId));
        utils.setMaxNumRowsPerPDU(5); // only for GETBULK, set max-repetitions,
                                      // default is 10
        OID[] columnOids = new OID[] {new OID("1.3.6.1.2.1.1.9.1.2"), // sysORID
                new OID("1.3.6.1.2.1.1.9.1.3") // sysORDescr
        };
        // If not null, all returned rows have an index in a range
        // (lowerBoundIndex, upperBoundIndex]
        List<TableEvent> l = utils.getTable(target, columnOids, new OID("3"), new OID("10"));
        for (TableEvent e : l) {
            System.out.println(e);
        }
    }

    private static class MyDefaultPDUFactory extends DefaultPDUFactory {
        private OctetString contextEngineId = null;

        public MyDefaultPDUFactory(int pduType, OctetString contextEngineId) {
            super(pduType);
            this.contextEngineId = contextEngineId;
        }

        @Override
        public PDU createPDU(Target target) {
            PDU pdu = super.createPDU(target);
            if (target.getVersion() == SnmpConstants.version3) {
                ((ScopedPDU) pdu).setContextEngineID(contextEngineId);
            }
            return pdu;
        }
    }
}
