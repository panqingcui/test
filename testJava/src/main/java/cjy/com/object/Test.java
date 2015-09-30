package cjy.com.object;

public class Test {
    // String str = new String("cjy");
    Integer i = new Integer(8);
    // Long l = new Long(8);
    // Object o = new Object();
    private String snmpVersion = "";
    private String snmpCommunity = "";

    public Test() {}

    public Test(String snmpVersion, String snmpCommunity) {
        this.snmpVersion = snmpVersion;
        this.snmpCommunity = snmpCommunity;
    }

    public static void main(String[] args) {
        System.out.println("Test snmpCommunity start ...");
        Test t1 = new Test("v1", null);
        Test t2 = new Test("v1", null);
        try {
            if (t1.equals(t2)) {
                System.out.println("t1 equals t2 ...");
            }
        } catch (Throwable t) {
            System.out.println("比较的对象有错误，含有NULL值：" + t);
        }
        System.out.println("Test snmpCommunity end ...");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Test) {
            Test testObj = (Test) obj;
            if (testObj.getSnmpVersion().equals("v3")) {
                if (testObj.getSnmpVersion().equals(snmpVersion)) {
                    return true;
                }
            } else {
                if (testObj.getSnmpVersion().equals(snmpVersion) && testObj.getSnmpCommunity().equals(snmpCommunity)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return "snmpCommunity=" + snmpCommunity;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public void setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
    }

    public String getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(String snmpVersion) {
        this.snmpVersion = snmpVersion;
    }
}
