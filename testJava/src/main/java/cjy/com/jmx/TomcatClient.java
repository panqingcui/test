package cjy.com.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 远程链接Tomcat的MBeanServer
 * @author Administrator
 */
public class TomcatClient {
    // 日志
    private static final Log logger = LogFactory.getLog(TomcatClient.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        String jmxUrl = "service:jmx:rmi:///jndi/rmi://192.168.1.15:1099/jmxrmi";
        try {
            service: jmx: rmi: // /jndi/rmi://192.168.1.15:1099/jmxrmi
            logger.info("start jmx ... ");
            JMXServiceURL address = new JMXServiceURL(jmxUrl);
            JMXConnector connector = JMXConnectorFactory.connect(address, null);
            if (connector == null) {
                logger.warn("不能获取JMX=" + jmxUrl + "连接");
            } else {
                logger.info("连接JMX=" + jmxUrl + "成功");
            }
            // it is a trivial matter to get a reference for the MBean server
            // connection to the remote agent
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();
            System.out.println("MBean count=" + mbsc.getMBeanCount());
            connector.connect();
            logger.info("Connection  jmx ... ");
            // for (ObjectInstance object : mbsc.queryMBeans(null, null))
            // {//服务器上的所有MBean
            // System.out.println("object.getObjectName="+object.getObjectName());
            // }
            ObjectName on = new ObjectName("Catalina:type=Manager,path=/,host=localhost");
            MBeanInfo mbeanInfo = mbsc.getMBeanInfo(on);
            MBeanOperationInfo[] operationInfo = mbeanInfo.getOperations();
            if (operationInfo != null) {// 获取方法的一些东西
                for (MBeanOperationInfo opration : operationInfo) {
                    System.out.println("MBeanOperationInfo=" + opration.getName());
                    MBeanParameterInfo[] paramsInfo = opration.getSignature();
                    for (MBeanParameterInfo param : paramsInfo) {
                        System.out.println("param=" + param.getName());
                        // param.getType();
                        // param.getName();
                    }
                    // mbsc.invoke(on, opration.getName(), params,
                    // opration.getSignature());
                }
            }
            MBeanAttributeInfo[] attributeInfo = mbeanInfo.getAttributes();
            if (attributeInfo != null) {// 获取属性的一些东西
                for (MBeanAttributeInfo attr : attributeInfo) {
                    System.out.println("MBeanAttributeInfo=" + attr.getName() + ";type=" + attr.getType());
                    // 获取属性值
                    System.out.println(mbsc.getAttribute(on, attr.getName()));
                }
            }
            // Get the names of all the Memory Manager MXBeans in the server
            Set<?> srvMemMgrNames = mbsc.queryNames(new ObjectName(ManagementFactory.MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE
                    + ",*"), null);
            // Get a MXBean Proxy for each name returned
            for (Object memMgrName : srvMemMgrNames) {
                // Cast Object to an ObjectName
                ObjectName memMgr = (ObjectName) memMgrName;
                // Call newPlatformMXBeanProxy with the complete object name
                // for the specific MXBean
                MemoryManagerMXBean memMgrBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, memMgr.toString(),
                        MemoryManagerMXBean.class);
                // memMgrBean is a proxy to the remote MXBean. We can use it
                // just as if it was a reference to a local MXBean.
                System.out.println("Memory Manager Name = " + memMgrBean.getName());
            }
        } catch (Exception e) {
            logger.error("不能获取JMX=" + jmxUrl + "连接", e);
        }
    }

    public static String toCapital(String str) {
        char[] cs = str.toCharArray();
        char c = (char) (str.charAt(0) - 32);
        cs[0] = c;
        // System.out.println(String.valueOf(cs));
        return String.valueOf(cs);
    }
}
