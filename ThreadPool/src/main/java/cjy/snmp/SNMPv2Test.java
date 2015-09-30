package cjy.snmp;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class SNMPv2Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.listen();
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setVersion(SnmpConstants.version2c);
        target.setAddress(new UdpAddress("192.168.1.121/161"));
        target.setTimeout(3000); // 3s
        target.setRetries(1);
        // MIBNode node = new MIBNode();
        // OID oid = getOID("hrStorageTypes");
        // sendRequest(snmp, createGetPdu(), target);
        // sendRequest(snmp, createGetNextPdu(), target);
        sendRequest(snmp, createGetBulkPdu(), target);
        // snmpWalk(snmp, target);
        // target.setCommunity(new OctetString("private"));
        // sendRequest(snmp, createSetPdu(), target);
        // CommunityTarget broadcastTarget = new CommunityTarget();
        // broadcastTarget.setCommunity(new OctetString("public"));
        // broadcastTarget.setVersion(SnmpConstants.version2c);
        // broadcastTarget.setAddress(new UdpAddress("192.168.1.103/161"));
        // broadcastTarget.setTimeout(5000); // 5s
        // sendAsyncRequest(snmp, createGetNextPdu(), broadcastTarget);
        // Thread.sleep(6000); // main thread wait 6s for the completion of asynchronous request
    }

    protected static OID getOID(String name) {
        MIBTree mibTree = MIBTree.getInstance();
        int[] oid = mibTree.getOID(name);
        if (oid == null) {
            String msg = "Failed to lookup OID for name=" + name;
            String unfound = mibTree.getLastLookupFailure();
            if (!name.equals(unfound)) {
                msg += " (last lookup failure=" + unfound + ")";
            }
        }
        return new OID(oid);
    }

    private static PDU createGetPdu() {
        PDU pdu = new PDU();
        pdu.setType(PDU.GET);
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"))); // sysUpTime
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"))); // sysName
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); // expect an no_such_instance error
        return pdu;
    }

    private static PDU createGetNextPdu() {
        PDU pdu = new PDU();
        pdu.setType(PDU.GETNEXT);
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3"))); // sysUpTime
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); // sysName
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.2021.9.1.8.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1..77.1.2.3.1.1.5.77.121.83.81.77")));
        return pdu;
    }

    private static PDU createGetBulkPdu() {
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);
        pdu.setMaxRepetitions(10); // must set it, default is 0
        pdu.setNonRepeaters(0);
        // pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1"))); // system1.3.6.1.4.1
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.2.1.1")));
        return pdu;
    }

    private static PDU createSetPdu() {
        PDU pdu = new PDU();
        pdu.setType(PDU.SET);
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"), new OctetString("sysname"))); // sysName
        return pdu;
    }

    private static void sendRequest(Snmp snmp, PDU pdu, CommunityTarget target) throws IOException {
        ResponseEvent responseEvent = snmp.send(pdu, target);
        PDU response = responseEvent.getResponse();
        if (response == null) {
            System.out.println("TimeOut...");
        } else {
            if (response.getErrorStatus() == PDU.noError) {
                Vector<? extends VariableBinding> vbs = response.getVariableBindings();
                for (VariableBinding vb : vbs) {
                    System.out.println(vb + " ," + vb.getVariable().getSyntaxString());
                }
            } else {
                System.out.println("Error:" + response.getErrorStatusText());
            }
        }
    }

    private static void sendAsyncRequest(Snmp snmp, PDU pdu, CommunityTarget target) throws IOException {
        snmp.send(pdu, target, null, new ResponseListener() {
            @Override
            public void onResponse(ResponseEvent event) {
                PDU response = event.getResponse();
                System.out.println("Got response from " + event.getPeerAddress());
                if (response == null) {
                    System.out.println("TimeOut...");
                } else {
                    if (response.getErrorStatus() == PDU.noError) {
                        Vector<? extends VariableBinding> vbs = response.getVariableBindings();
                        for (VariableBinding vb : vbs) {
                            System.out.println(vb + " ," + vb.getVariable().getSyntaxString());
                        }
                    } else {
                        System.out.println("Error:" + response.getErrorStatusText());
                    }
                }
            }
        });
    }

    private static void snmpWalk(Snmp snmp, CommunityTarget target) {
        TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));// GETNEXT or GETBULK
        utils.setMaxNumRowsPerPDU(5); // only for GETBULK, set max-repetitions, default is 10
        OID[] columnOids = new OID[] {new OID("1.3.6.1.2.1.1.9.1.2"), // sysORID
                new OID("1.3.6.1.2.1.1.9.1.3"), // sysORDescr
                new OID("1.3.6.1.2.1.1.9.1.5") // wrong OID, expect an null in in VariableBinding array
        };
        // If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]
        List<TableEvent> l = utils.getTable(target, columnOids, new OID("3"), new OID("10"));
        for (TableEvent e : l) {
            System.out.println(e);
        }
    }
}
