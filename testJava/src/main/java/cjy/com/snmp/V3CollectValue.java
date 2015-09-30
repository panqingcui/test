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

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2013-2-19<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class V3CollectValue {
    // private static String oid = "1.3.6.1.2.1.1.4.0";
    // sysUpTime
    private static String oid = "1.3.6.1.2.1.1.3.0";

    public static void main(String[] args) {
        get(oid);
    }

    public static void get(String oid) {
        TransportMapping<?> transportMapping = null;
        Snmp snmp = null;
        try {
            transportMapping = new DefaultUdpTransportMapping();
            snmp = new Snmp(transportMapping);
            transportMapping.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Target agent = createV3Target(snmp, "192.168.1.202", 161);// Agent
        ScopedPDU pduV3 = new ScopedPDU();// V3独有的类型
        pduV3.setType(PDU.GET);
        /*
         * ContextName用于标识一个管理信息集合， 如果agent本地有多块网卡，那么一般来说就有多个context
         */
        // pduV3.setContextName(new OctetString("priv"));
        pduV3.add(new VariableBinding(new OID(oid)));
        try {
            ResponseEvent response = snmp.send(pduV3, agent);
            if (response == null || response.getResponse() == null) {
                System.out.println("SNMP request timed out");
            }
            if (response != null) {
                PDU responsePDU = response.getResponse();
                if (responsePDU.getErrorStatus() == PDU.noError) {
                    for (int k = 0; k < responsePDU.size(); k++) {
                        VariableBinding vb = responsePDU.get(k);
                        if (vb != null) {
                            System.out.println(vb.getOid() + "-" + vb.getVariable().toString());
                        }
                    }
                } else {
                    System.out.println("SNMP Error:" + responsePDU.getErrorStatusText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    private static Target createV3Target(Snmp snmp, String agentIp, int agentPort) {
        String userName = "enocsnmpv3";
        String securityName = "enocsnmpv3";// 安全模块的名称
        String authPassword = "enocsnmpv3pw";
        String privPassword = "enocsnmpv3pk";
        UserTarget userTarget = new UserTarget();
        userTarget.setVersion(SnmpConstants.version3);
        userTarget.setSecurityLevel(SecurityLevel.AUTH_PRIV);
        userTarget.setSecurityName(new OctetString(securityName));
        // 设置USM-用户安全模型
        MPv3 mpv3 = (MPv3) snmp.getMessageProcessingModel(MessageProcessingModel.MPv3);
        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(mpv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        // 设置用户
        UsmUser usmUser = new UsmUser(new OctetString(userName), AuthMD5.ID, new OctetString(authPassword), PrivDES.ID,
                new OctetString(privPassword));
        snmp.getUSM().addUser(new OctetString(userName), usmUser);
        userTarget.setRetries(3);
        userTarget.setTimeout(5000);// MS
        userTarget.setAddress(GenericAddress.parse("udp:" + agentIp + "/" + agentPort));
        return userTarget;
    }
}
